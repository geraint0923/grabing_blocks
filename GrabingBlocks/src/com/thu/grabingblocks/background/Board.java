package com.thu.grabingblocks.background;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
/**
 * each Game has one GameManager. Main Class in background
 * @author xxk
 *
 */
public class Board {
	/**
	 * construct
	 * @param width the board's width
	 * @param height the board's height
	 * @param playerNum how many player in this game(now only support playerNum=4)
	 */
	public Board(int width,int height,int playerNum){
		this.playerNum=playerNum;
		//init the board
		this.width=width;
		this.height=height;
		board=new int[width][height];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				board[i][j]=-1;
			}
		}
		//init activeBlock[][]
		activeBlockList=new ArrayList<boolean[][]>(playerNum);
		for(int i=0;i<playerNum;i++){
			boolean[][] active=new boolean[width][height];
			activeBlockList.add(active);
		}
		if(playerNum==4){
			activeBlockList.get(0)[0][0]=true;
			activeBlockList.get(1)[width-1][0]=true;
			activeBlockList.get(2)[width-1][height-1]=true;
			activeBlockList.get(3)[0][height-1]=true;
		}else{//playNum==2
			activeBlockList.get(0)[width*3/4][height*1/4]=true;
			activeBlockList.get(0)[width*1/4][height*3/4]=true;			
		}
		//init ShapeManager
		shapeManagerList=new ArrayList<ShapeManager>(playerNum);
		for(int i=0;i<playerNum;i++){
			shapeManagerList.add(new ShapeManager());
		}
	}
	
	/**
	 * check if the Shape can be placed , if true then place the Shape
	 * @param shape
	 * @param dx
	 * @param dy
	 * @param playerID
	 * @return true if Shape can be placed
	 */
	public boolean placeShape(Shape shape,int dx,int dy,int playerID){
		if(canPlace(shape,dx,dy,playerID)){
			for(int i=0;i<shape.p.length;i++){
				board[dx+shape.p[i].x][dy+shape.p[i].y]=playerID;//add to board
				for(int j=0;j<playerNum;j++){//change all players' active[][]
					activeBlockList.get(j)[dx+shape.p[i].x][dy+shape.p[i].y]=false;
				}
			}
			updateActiveBlocks(playerID,dx-1,dx+width,dy-1,dy+height);
			shapeManagerList.get(playerID).shapes.remove(shape);
			return true;
		}
		return false;
	}
	
	/**
	 * check if the Shape can be place
	 * @param shape
	 * @param dx
	 * @param dy
	 * @param playerID
	 * @return true if the Shape can be placed
	 */
	protected boolean canPlace(Shape shape,int dx,int dy,int playerID){
		if(shape.getWidth()-1+dx>=width || shape.getHeight()-1+dy>=height){
			return false;
		}
		Point[] p=shape.p;
		boolean active[][]=activeBlockList.get(playerID);
		boolean flag=false;
		for(int i=0;i<p.length;i++){//at least it should have one block place in an active block
			if(board[dx+p[i].x][dy+p[i].y]>=0 || active[dx+p[i].x][dy+p[i].y]){
				flag=true;
			}
		}
		if(!flag){
			return false;
		}
		for(int i=0;i<p.length;i++){//it cannot place near its other shapes , nor place on other players' block
			if(adjacentOccupied(dx+p[i].x,dy+p[i].y,playerID)){
				return false;
			}

		}
		return true;
	}
	
	/**
	 * calculate whether a player can place in this rectangle area
	 * @param playerID
	 * @param left
	 * @param right
	 * @param top
	 * @param bottom
	 */
	protected void updateActiveBlocks(int playerID,int left,int right,int top,int bottom){
		boolean[][] active=activeBlockList.get(playerID);
		if(left<0){
			left=0;
		}
		if(right>=width){
			right=width-1;
		}
		if(top<0){
			top=0;
		}
		if(bottom>=height){
			bottom=height-1;
		}
		for(int i=left;i<=right;i++){
			for(int j=top;j<=bottom;j++){
				if(board[i][j]>=0 || adjacentOccupied(i,j,playerID) || !diagonalOccupied(i,j,playerID)){
					active[i][j]=false;
				}else{
					active[i][j]=true;
				}
			}
		}
	}
	
	/**
	 * calculate a block whether its left/right/top/bottom nearby blocks belongs to this player
	 * @param ID
	 * @param x
	 * @param y
	 * @return true if at least one of its nearby blocks belongs to this player
	 */
	private boolean adjacentOccupied(int x,int y,int ID){
		if(x>0 && board[x-1][y]==ID){
			return true;
		}
		if(y>0 && board[x][y-1]==ID){
			return true;
		}
		if(x<width-1 && board[x+1][y]==ID){
			return true;
		}
		if(y<height-1 && board[x][y+1]==ID){
			return true;
		}
		return false;
	}
	
	/**
	 * calculate a block whether its top-left/top-right/bottom-left/bottom-right belongs to this player
	 * @param ID
	 * @param x
	 * @param y
	 * @return true if at least one of its nearby blocks belongs to this player
	 */
	private boolean diagonalOccupied(int x,int y,int ID){
		if(x>0 && y>0 && board[x-1][y-1]==ID){
			return true;
		}
		if(x>0 && y<height-1 && board[x-1][y+1]==ID){
			return true;
		}
		if(x<width-1 && y>0 && board[x+1][y-1]==ID){
			return true;
		}
		if(x<width-1 && y<height-1 && board[x+1][y+1]==ID){
			return true;
		}
		return false;
	}
	
	/**
	 * new a List that contains all available blocks on the board
	 * @param playerID
	 * @return List that contains all available blocks on the board
	 */
	public List<Point> getAvailableBlocks(int playerID){
		List<Point> list=new ArrayList<Point>();
		boolean[][] active=activeBlockList.get(playerID);
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(active[i][j]){
					list.add(new Point(i,j));
				}
			}
		}
		return list;
	}
	
	/**
	 * return the player's not used shapes
	 * @param playerID
	 * @return
	 */
	public final List<Shape> getShapes(int playerID){
		return shapeManagerList.get(playerID).shapes;
	}
	
	/**
	 * check whether this player have no shape to place, if true, this player lose the game.
	 * @param playerID
	 * @return true if this player cannot place any shape he has
	 */
	public boolean isLose(int playerID){
		List<Shape> list=this.getShapes(playerID);
		for(int i=0;i<list.size();i++){
			if(getAvailableBlocksForShape(list.get(i),playerID).size()>0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * check whether this player can place this shape into the board
	 * the shape can rotate, reflect, at most 8 conditions
	 * @param shape
	 * @param playerID
	 * @return
	 */
	public List<Point> getAvailableBlocksForShape(Shape shape,int playerID){
		Set<Point> blocks=new HashSet<Point>();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.rotate();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.rotate();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.rotate();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.reflect();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.rotate();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.rotate();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.rotate();
		calAvailableBlocksForShape(shape,playerID,blocks);
		shape.reflect();//change the shape back to this original condition
		return new ArrayList<Point>(blocks);
	}
	
	//only used by void getAvailableBlocksForShape
	private void calAvailableBlocksForShape(Shape shape,int playerID,Set<Point> list){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(this.canPlace(shape, i, j, playerID)){
					List<Point> p=shape.getPoints();
					boolean[][] active=this.activeBlockList.get(playerID);
					for(int k=0;k<p.size();k++){
						int x=p.get(k).x + i;
						int y=p.get(k).y + j;
						if(active[x][y]){
							list.add(new Point(x,y));
						}
					}
				}
			}
		}
	}
	//Debug
	public void printActiveBlocks(int playerID){
		List<Point> list=getAvailableBlocks(playerID);
		for(int i=0;i<list.size();i++){
			System.out.print("("+list.get(i).x+","+list.get(i).y+") ");
		}
		System.out.println();
	}
	
	//Debug
	public void printBoard(){
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if(board[j][i]>=0){
					System.out.print(board[j][i]+" ");
				}else{
					System.out.print(". ");
				}

			}
			System.out.println();
		}
	}
	protected int board[][];
	protected ArrayList<boolean[][]> activeBlockList;
	protected int width;
	protected int height;
	protected int playerNum;
	protected ArrayList<ShapeManager> shapeManagerList;
	
}

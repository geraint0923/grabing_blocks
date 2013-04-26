package com.thu.grabingblocks.background;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xiexinkai
 */
public class Shape{
	public Shape(Point... pp){
		p = new Point[pp.length];
		int maxX=Integer.MIN_VALUE;
		int maxY=Integer.MIN_VALUE;
		for(int i=0;i<pp.length;i++){
			p[i]=pp[i];
			if(p[i].x>maxX){
				maxX=p[i].x;
			}
			if(p[i].y>maxY){
				maxY=p[i].y;
			}
		}
		width=maxX+1;
		height=maxY+1;
	}
	
	/**
	 * @return Shape's height (x-coordinate)
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @return Shape's Point[];
	 */
	public final List<Point> getPoints(){
		List<Point> list=new ArrayList<Point>(p.length);
		for(int i=0;i<p.length;i++){
			list.add(p[i]);
		}
		return list;
	}
	/**
	 * @return Shape's width (y-coordinate)
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * reflect (x coordinate) will change Point[]
	 * . . 1               1 . .
	 * 1 1 1 -> reflect -> 1 1 1
	 * . 1 .               . 1 .
	 */
	public void reflect(){
		//reflection matrix
		//(-1 0)
		//( 0 1)
		for(int i=0;i<p.length;i++){
			p[i].x=width-1-p[i].x;
		}
	}
	
	/**
	 * rotate clockwise, will change Point[],will swap width and height
	 * . 1
	 * . 1 -> rotate -> 1 . .
	 * 1 1              1 1 1
	 */
	public void rotate(){
		//rotation matrix
		//( 0 -1)
		//( 1  0)
		int temp;
		for(int i=0;i<p.length;i++){
			temp=p[i].y;
			p[i].y=p[i].x;
			p[i].x=height-1-temp;
		}
		temp=width;
		width=height;
		height=temp;
	}
	protected Point[] p;
	protected int width;
	protected int height;
	
	//for debug
	public void printXY(){
		for(int i=0;i<p.length;i++){
			System.out.print("("+p[i].x+","+p[i].y+") ");
		}
		System.out.println();
	}
}
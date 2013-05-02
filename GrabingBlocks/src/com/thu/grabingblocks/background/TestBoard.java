package com.thu.grabingblocks.background;

import java.util.List;

public class TestBoard {

	/**
	 * it is a text example
	 * 1-9 : playerID=0
	 * A-I : playerID=3
	 * 
	 * 1 . . . B B
	 * 1 1 . 2 2 B
	 * . 1 1 . 2 B
	 * . . . 2 2 B
	 * A A A A A .
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBoard tb=new TestBoard();
		tb.go();
	}

	public void go(){
		Board gm=new Board(6,5,4);
		Shape shape=gm.getShapes(0).get(6);
		System.out.println(gm.placeShape(shape, 0, 0, 0));
		shape=gm.getShapes(0).get(9);
		System.out.println(gm.placeShape(shape, 0, 0, 0));
		shape=gm.getShapes(0).get(9);
		gm.placeShape(shape, 3, 1, 0);
		gm.printActiveBlocks(0);
		gm.printBoard();
		//
		this.printList(gm.getAvailableBlocksForShape(shape, 3));
		shape=gm.getShapes(3).get(11);
		shape.rotate();
		gm.placeShape(shape, 0, 4, 3);
		shape=gm.getShapes(3).get(11);
		shape.reflect();
		gm.placeShape(shape, 4, 0, 3);
		gm.printActiveBlocks(3);
		gm.printBoard();
		this.printList(gm.getAvailableBlocksForShape(gm.getShapes(0).get(0), 0));
	}
	
	private void printList(List<Point> list){
		System.out.print("All "+list.size()+" points:");
		for(int i=0;i<list.size();i++){
			System.out.print("("+list.get(i).x+","+list.get(i).y+") ");
		}
		System.out.println();
	}
}

package com.thu.grabingblocks.background;

public class TestShape {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape shape=new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(1,2));
		shape.printXY();
		shape.reflect();
		shape.printXY();
		shape.rotate();
		shape.printXY();
		shape.rotate();
		shape.printXY();
		shape.rotate();
		shape.printXY();
		shape.reflect();
		shape.printXY();
	}

}

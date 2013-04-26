package com.thu.grabingblocks.background;

import java.util.ArrayList;
import java.util.List;
/**
 * Manage Shape, each GameManager has one ShapeManager.
 * @author xxk
 *
 */
public class ShapeManager {
	/**
	 * in all 21 different Shapes
	 * 
	 *  0 . .   0 . .   0 . .   0 0 .   0 0 .   0 . .   . 0 .   0 0 .   0 0 .   0 . .   0 0 .
	 *  . . .   0 . .   0 . .   0 . .   0 . .   0 0 .   0 0 .   0 0 .   0 0 .   0 0 .   . 0 .
	 *  . . .   . . .   0 . .   . . .   0 . .   0 . .   0 . .   . . .   0 . .   . 0 0   0 0 .
	 *  
	 *  0 . .   0 0 .   0 . .   0 . .   0 . .   0 . .   . 0 .   0 . .   0 . .   0 . .
	 *  0 . .   0 . .   0 0 .   0 0 .   0 . .   0 0 0   0 0 0   0 0 0   0 . .   0 0 0
	 *  0 . .   0 . .   0 . .   . 0 .   0 . .   0 . .   . 0 .   . . 0   0 0 0   . 0 .
	 *  0 . .   0 . .   0 . .   . 0 .   0 . .   
	 *  0 . .
	 */
	public ShapeManager(){
		shapes=new ArrayList<Shape>(SHAPE_NUM);
		//0-4
		shapes.add(new Shape(new Point(0,0)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(1,0)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(1,0)));
		//5-10
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(1,1)));
		shapes.add(new Shape(new Point(1,0),new Point(0,1),new Point(0,2),new Point(1,1)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(1,0),new Point(1,1)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(1,0),new Point(1,1),new Point(0,2)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(1,1),new Point(1,2),new Point(2,2)));
		shapes.add(new Shape(new Point(0,0),new Point(1,0),new Point(1,1),new Point(1,2),new Point(0,2)));
		//11-16
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(1,0)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(1,1)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(1,1),new Point(1,2),new Point(1,3)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3)));
		//17-21
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(1,1),new Point(2,1)));
		shapes.add(new Shape(new Point(1,0),new Point(0,1),new Point(1,1),new Point(1,2),new Point(2,1)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(1,1),new Point(2,1),new Point(2,2)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(0,2),new Point(1,2),new Point(2,2)));
		shapes.add(new Shape(new Point(0,0),new Point(0,1),new Point(1,1),new Point(1,2),new Point(2,1)));
	}
	
	//protected Shape[] shapes;
	protected List<Shape> shapes;
	protected int SHAPE_NUM = 21;
}

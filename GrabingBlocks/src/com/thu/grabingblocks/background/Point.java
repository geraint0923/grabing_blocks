package com.thu.grabingblocks.background;
public class Point {

	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int x;
	public int y;
	
	@Override
	public int hashCode(){
		return x*1024+y;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof Point){
			if(obj.hashCode()==this.hashCode()){
				return true;
			}
		}
		return false;
	}
}

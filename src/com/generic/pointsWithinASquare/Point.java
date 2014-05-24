package com.generic.pointsWithinASquare;

/**
 * A point on a 2D plane is identified with its x and y coordinates
 */

public class Point {

	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	/**
	 * @param p
	 * @return point minus p
	 */
	public Point minus(Point p) {
		double resultX = this.x - p.x;
		double resultY = this.y - p.y;
		Point result = new Point(resultX, resultY);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point)obj;
			if (p.x == this.x && p.y == this.y)
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
}

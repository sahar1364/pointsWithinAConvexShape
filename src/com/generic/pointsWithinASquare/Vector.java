package com.generic.pointsWithinASquare;

/**
 * A vector is specified by a start point and an end point P0-->P1
 */

public class Vector {
	private final Point p0;
	private final Point p1;
	
	public Vector(Point start, Point end) {
		this.p0 = start;
		this.p1 = end;
	}
	
	public Point getP0() {
		return p0;
	}
	
	public Point getP1() {
		return p1;
	}
	
	public double getVectorLength() {
		return Math.sqrt(Math.pow(p0.getX() - p1.getX(), 2) + Math.pow(p0.getY() - p1.getY(), 2));
	}
	
	@Override
	public String toString() {
		return "Vector: " + p0 + " --> " + p1;
	}
}


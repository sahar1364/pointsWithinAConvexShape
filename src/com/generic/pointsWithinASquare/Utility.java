package com.generic.pointsWithinASquare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A class for doing vector based calculations
 */
public abstract class Utility {
	
	private static double EPS = 0.00001; //Epsilon for doing math calculations which represents the accuracy
	
	/**
	 * @param shape
	 * @param points
	 * @return True if all the points are inside the polygon, False otherwise
	 */
	public static boolean areAllPointsWithinAShape(PolygonalShape shape, List<Point> points) {
		for (Point point: points) {
			if (!isAPointWithinAShape(shape, point))
				return false;
		}
		return true;
	}
	
	/**
	 * @param shape
	 * @param point
	 * @return True if the point is inside the shape, false otherwise 
	 * The points is within a convex polygon if the sum of the angles between vectors point-->Pi and point-->Pi+1
	 * is equal to 2*PI where P0..Pn are the polygon's vertices
	 */
	private static boolean isAPointWithinAShape(PolygonalShape shape, Point point) {
		double angle = 0;
		for (int i = 0; i < shape.getVertices().size(); i++) {
			//check if the point lies on any of the shape vertices
			if (point.equals(shape.getVertices().get(i)))
				return true;
			//check if the point is between two vertices on the edge
			if (isPointInBetweenTwoPoints(point, shape.getVertices().get(i), shape.getVertices().get((i+1)%shape.getVertices().size())))
				return true;
			Vector v1 = new Vector(point, shape.getVertices().get(i));
			Vector v2 = new Vector(point, shape.getVertices().get((i+1)%shape.getVertices().size()));
			angle += Math.abs(angleBetweenTwoVectors(v1, v2));
		}
		if (Math.abs(2*Math.PI - angle) < EPS)
			return true;
		return false;
	}
	
	/**
	 * @param v1
	 * @param v2
	 * @return The angle between two vectors v1 and v2
	 * This method uses the dot product between two vectors in order to find the angle
	 * v1.v2=|v1|*|v2|*cos(theta)=v1.x*v2.x+v1.y*v2.y
	 */
	private static double angleBetweenTwoVectors(Vector v1, Vector v2) {
		double cosTheta = ((v1.getP0().minus(v1.getP1()).getX()*v2.getP0().minus(v2.getP1()).getX()) + 
				(v1.getP0().minus(v1.getP1()).getY()*v2.getP0().minus(v2.getP1()).getY()))/(v1.getVectorLength()*v2.getVectorLength());
		return Math.acos(cosTheta);
		
	}
	
	/**
	 * @param p
	 * @param p1
	 * @param p2
	 * @return True if p is between p1 and p2, False otherwise
	 */
	private static boolean isPointInBetweenTwoPoints(Point p, Point p1, Point p2) {
		double distance_1 = Math.sqrt((p.minus(p1).getX())*(p.minus(p1).getX()) + (p.minus(p1).getY())*(p.minus(p1).getY()));
		double distance_2 = Math.sqrt((p.minus(p2).getX())*(p.minus(p2).getX()) + (p.minus(p2).getY())*(p.minus(p2).getY()));
		double distance_3 = Math.sqrt((p1.minus(p2).getX())*(p1.minus(p2).getX()) + (p1.minus(p2).getY())*(p1.minus(p2).getY()));
		
		if (distance_3 == distance_1 + distance_2)
			return true;
		return false;
	}
	
	/**
	 * @param points
	 * @return sorted points in clockwise order
	 */
	public static List<Point> sortPointsInClockWiseOrder(final List<Point> points) {
		List<Point> sorted = new ArrayList<Point>();
		sorted.add(points.get(0));
		
		List<Point> left = new ArrayList<Point>();
		List<Point> right = new ArrayList<Point>();
		
		final Point center = findCenterOfPolygon(points);
		final Vector v = new Vector(center, sorted.get(0));
		
		//divide the points to the right and left groups with respect to the vector (center->points.get(0))
		for (int i = 1; i < points.size(); i++) {
			if (isP1ToTheLeftP2InRelationsToCenter(points.get(0), points.get(i), center))
				left.add(points.get(i));
			else
				right.add(points.get(i));
		}
		//sort the left group of points
		Collections.sort(left, new Comparator<Point>() {
			@Override
			public int compare(Point p0, Point p1) {
				if (angleBetweenTwoVectors(v, new Vector(center, p0)) > angleBetweenTwoVectors(v, new Vector(center, p1)))
					return -1;
				return 1;
			}
		});
		//sort the right group of points
		Collections.sort(right, new Comparator<Point>() {
			@Override
			public int compare(Point p0, Point p1) {
				if (angleBetweenTwoVectors(v, new Vector(center, p0)) > angleBetweenTwoVectors(v, new Vector(center, p1)))
					return 1;
				return -1;
			}
		});		
		//add all the points to the list
		sorted.addAll(right);
		sorted.addAll(left);
		return sorted;
	}
	
	/**
	 * @param points
	 * @return center of a polygon given its vertices
	 */
	private static Point findCenterOfPolygon(List<Point> points) {
		double x = 0;
		double y = 0;
		for (Point point: points) {
			x += point.getX();
			y += point.getY();
		}
		return new Point(x/points.size(), y/points.size());
	}
	
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param center
	 * @return True of p1 is to the left of p2 in relation to the center
	 */
	private static boolean isP1ToTheLeftP2InRelationsToCenter(Point p1, Point p2, Point center) {
		return findDeterminent(p1, p2, center) > 0;
	}

	/**
	 * @param v1
	 * @param v2
	 * @return 
	 * Calculates |A X B| which is can be used to determine p1 and p2 are clockwise or counter-clockwise with respect to
	 * a reference point (center)
	 */
	private static double findDeterminent(Point p1, Point p2, Point center) {
		return (p1.getX() - center.getX()) * (p2.getY() - center.getY()) - (p2.getX() - center.getX()) * (p1.getY() - center.getY());
	}
	
}

package com.generic.pointsWithinASquare;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.generic.pointsWithinASquare.Point;
import com.generic.pointsWithinASquare.PolygonalShape;
import com.generic.pointsWithinASquare.Utility;

/**
 * Unit tests for Utility class
 */
public class UtilityTest {
	@Test
	public void areAllPointsWithinAShapeTest() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,2);
		
		List<Point> points = new ArrayList<Point>();
		points.add(p1);
		points.add(p2);
		
		Point p3 = new Point(0,0);
		Point p4 = new Point(0, 0.5);
		Point p5 = new Point(1,1);
		Point p6 = new Point(1.5,1);
		Point p7 = new Point(0.75, 0);
		
		List<Point> vertices = new ArrayList<Point>();
		vertices.add(p3);
		vertices.add(p4);
		vertices.add(p5);
		vertices.add(p6);
		vertices.add(p7);
		
		PolygonalShape convexPolygon = new PolygonalShape();
		convexPolygon.setVertices(vertices);
		
		assertEquals(false, Utility.areAllPointsWithinAShape(convexPolygon, points));
	}
	
	@Test
	public void sortPointsInClockWiseOrderTest() {
		Point p1 = new Point(3,3);
		Point p2 = new Point(-1,-1);
		Point p3 = new Point(1,4);
		Point p4 = new Point(-1,4);
		Point p5 = new Point(3,-1);
		Point p6 = new Point(-2,3);
		Point p7 = new Point(-2, 0);
		
		List<Point> points = new ArrayList<Point>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		points.add(p7);
		
		List<Point> sortedPoints = Utility.sortPointsInClockWiseOrder(points);
		
		assertEquals(p1, sortedPoints.get(0));
		assertEquals(p2, sortedPoints.get(2));
		assertEquals(p3, sortedPoints.get(6));
		assertEquals(p4, sortedPoints.get(5));
		assertEquals(p5, sortedPoints.get(1));
		assertEquals(p6, sortedPoints.get(4));
		assertEquals(p7, sortedPoints.get(3));
	}
}

package com.solium.pointsWithinASquare;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit tests for PolygonalShape class
 */
public class PolygonalShapeTest {

	@Test
	public void testSetterAndGetter() {
		PolygonalShape shape = new PolygonalShape();
		List<Point> vertices = new ArrayList<Point>();
		Point p1 = new Point(0,1);
		Point p2 = new Point(1,1);
		Point p3 = new Point(1,0);
		vertices.add(p1);
		vertices.add(p2);
		vertices.add(p3);
		shape.setVertices(vertices);
		assertEquals(shape.getVertices(), vertices);
	}
}

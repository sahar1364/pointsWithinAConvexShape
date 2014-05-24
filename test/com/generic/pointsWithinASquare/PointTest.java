package com.generic.pointsWithinASquare;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.generic.pointsWithinASquare.Point;

/**
 * Unit tests for Point class
 */
public class PointTest {
	@Test
	public void constructorTest() {
		Point p = new Point(0.0, 1.1);
		assertEquals(new Double(p.getX()), new Double(0.0));
		assertEquals(new Double(p.getY()), new Double(1.1));
	}
	
	@Test
	public void minusTest() {
		Point p = new Point(0.0, 1.0);
		Point c = new Point(-0.5, -0.1);
		assertEquals(new Point(0.5, 1.1), p.minus(c));
	}
}

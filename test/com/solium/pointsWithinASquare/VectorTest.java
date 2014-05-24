package com.solium.pointsWithinASquare;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for Vector class
 */
public class VectorTest {
	@Test
	public void testConstructor() {
		Vector v = new Vector(new Point(0.0, 1.0), new Point(1.0, 1.0));
		assertEquals(v.getP0(), new Point(0.0, 1.0));
		assertEquals(v.getP1(), new Point(1.0, 1.0));
	}
}

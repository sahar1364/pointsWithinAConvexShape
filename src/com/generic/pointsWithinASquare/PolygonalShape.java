package com.generic.pointsWithinASquare;

import java.util.List;

/**
 * PolygonalShape is identified by its vertices in clockwise order
 */
public class PolygonalShape {
	//in clockwise order
	List<Point> vertices;
	
	public PolygonalShape() {}
	
	public List<Point> getVertices() {
		return vertices;
	}

	public void setVertices(List<Point> vertices) {
		this.vertices = vertices;
	}
}

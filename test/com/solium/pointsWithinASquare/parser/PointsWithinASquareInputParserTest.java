package com.solium.pointsWithinASquare.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.solium.pointsWithinASquare.Point;
import com.solium.pointsWithinASquare.PolygonalShape;

/**
 * Unit Tests for the PointsWithinASquareInputParser
 */
public class PointsWithinASquareInputParserTest {
	
	  @Test
	  public void createShapeAndPointsForSquareTest() throws IOException, PointsWithinASquareInputParserException {
		  String input = "1,1 2,2 2,1 1,2 1.5,1.5 2,2";
		  PointsWithinASquareInputParser parser = new PointsWithinASquareInputParser();
		  List<Object> returnValue = parser.createShapeAndPoints(new StringReader(input));
		  
		  PolygonalShape shape = (PolygonalShape)returnValue.get(0);
		  @SuppressWarnings("unchecked")
		  ArrayList<Point> points = (ArrayList<Point>)returnValue.get(1);
		  
		  Point p1 = new Point(1,1);
		  Point p2 = new Point(1,2);
		  Point p3 = new Point(2,2);
		  Point p4 = new Point(2,1);
		  
		  Point p5 = new Point(1.5, 1.5);
		  Point p6 = new Point(2,2);
		  
		  assertEquals(true, points.contains(p5));
		  assertEquals(true, points.contains(p6));
		  
		  assertEquals(true, shape.getVertices().contains(p1));
		  assertEquals(true, shape.getVertices().contains(p2));
		  assertEquals(true, shape.getVertices().contains(p3));
		  assertEquals(true, shape.getVertices().contains(p4));

	  }
	  
	  @Test
	  public void createShapeAndPointsForConvexPolygonTest() throws IOException, PointsWithinASquareInputParserException {
		  String input = "6 0,1 2,2 1,3 1,0 2,1 0,2 1,1";
		  PointsWithinASquareInputParser parser = new PointsWithinASquareInputParser();
		  List<Object> returnValue = parser.createShapeAndPoints(new StringReader(input));
		  
		  PolygonalShape shape = (PolygonalShape)returnValue.get(0);
		  @SuppressWarnings("unchecked")
		  ArrayList<Point> points = (ArrayList<Point>)returnValue.get(1);
		  
		  Point p1 = new Point(0,1);
		  Point p2 = new Point(2,2);
		  Point p3 = new Point(1,3);
		  Point p4 = new Point(1,0);
		  Point p5 = new Point(2, 1);
		  Point p6 = new Point(0,2);
		  
		  Point p7 = new Point(1,1);
		  
		  assertEquals(true, points.contains(p7));
		  
		  assertEquals(true, shape.getVertices().contains(p1));
		  assertEquals(true, shape.getVertices().contains(p2));
		  assertEquals(true, shape.getVertices().contains(p3));
		  assertEquals(true, shape.getVertices().contains(p4));
		  assertEquals(true, shape.getVertices().contains(p5));
		  assertEquals(true, shape.getVertices().contains(p6));
	  }
	  
	  @Test(expected = PointsWithinASquareInputParserException.class)
	  public void invalidInputTest1() throws IOException, PointsWithinASquareInputParserException {
		  String input = "6 0,1 2,2 1,3 1,0 2,1";
		  PointsWithinASquareInputParser parser = new PointsWithinASquareInputParser();
		  parser.createShapeAndPoints(new StringReader(input)); 
	  }
	  
	  @Test(expected = PointsWithinASquareInputParserException.class)
	  public void invalidInputTest2() throws IOException, PointsWithinASquareInputParserException {
		  String input = "0,1 2,2 1,3";
		  PointsWithinASquareInputParser parser = new PointsWithinASquareInputParser();
		  parser.createShapeAndPoints(new StringReader(input)); 
	  }
}

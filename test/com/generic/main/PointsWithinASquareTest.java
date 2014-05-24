package com.generic.main;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.generic.pointsWithinASquare.Point;
import com.generic.pointsWithinASquare.PolygonalShape;
import com.generic.pointsWithinASquare.Utility;
import com.generic.pointsWithinASquare.parser.PointsWithinASquareInputParser;
import com.generic.pointsWithinASquare.parser.PointsWithinASquareInputParserException;

/**
 * Unit tests for PointsWithinASquare class
 */
public class PointsWithinASquareTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testMainForSquare() throws IOException, PointsWithinASquareInputParserException {
		  String input1 = "2,3 1,2 3,2 2,1 1.5,2.49";
		  PointsWithinASquareInputParser parser1 = new PointsWithinASquareInputParser();
		  List<Object> returnValue1 = parser1.createShapeAndPoints(new StringReader(input1));
		  
		  PolygonalShape shape1 = (PolygonalShape)returnValue1.get(0);
		  ArrayList<Point> points1 = (ArrayList<Point>)returnValue1.get(1);
		  
		  assertEquals(true, Utility.areAllPointsWithinAShape(shape1, points1));
		  
		  String input2 = "2,3 1,2 3,2 2,1 2.5,1.49";
		  PointsWithinASquareInputParser parser2 = new PointsWithinASquareInputParser();
		  List<Object> returnValue2 = parser2.createShapeAndPoints(new StringReader(input2));
		  
		  PolygonalShape shape2 = (PolygonalShape)returnValue2.get(0);
		  ArrayList<Point> points2 = (ArrayList<Point>)returnValue2.get(1);
		  
		  assertEquals(false, Utility.areAllPointsWithinAShape(shape2, points2));

		  String input3 = "2,3 1,2 3,2 2,1 1.5,1.51";
		  PointsWithinASquareInputParser parser3 = new PointsWithinASquareInputParser();
		  List<Object> returnValue3 = parser3.createShapeAndPoints(new StringReader(input3));
		  
		  PolygonalShape shape3 = (PolygonalShape)returnValue3.get(0);
		  ArrayList<Point> points3 = (ArrayList<Point>)returnValue3.get(1);
		  
		  assertEquals(true, Utility.areAllPointsWithinAShape(shape3, points3));
		  
		  String input4 = "3,2 2,3 1,2 2,1 1.01,2";
		  PointsWithinASquareInputParser parser4 = new PointsWithinASquareInputParser();
		  List<Object> returnValue4 = parser4.createShapeAndPoints(new StringReader(input4));
		  
		  PolygonalShape shape4 = (PolygonalShape)returnValue4.get(0);
		  ArrayList<Point> points4 = (ArrayList<Point>)returnValue4.get(1);
		  
		  assertEquals(true, Utility.areAllPointsWithinAShape(shape4, points4));
		  
		  String input5 = "3,2 2,3 1,2 2,1 3,2";
		  PointsWithinASquareInputParser parser5 = new PointsWithinASquareInputParser();
		  List<Object> returnValue5 = parser5.createShapeAndPoints(new StringReader(input5));
		  
		  PolygonalShape shape5 = (PolygonalShape)returnValue5.get(0);
		  ArrayList<Point> points5 = (ArrayList<Point>)returnValue5.get(1);
		  
		  assertEquals(true, Utility.areAllPointsWithinAShape(shape5, points5));
		  
		  String input6 = "3,2 2,3 1,2 2,1 0.99,2";
		  PointsWithinASquareInputParser parser6 = new PointsWithinASquareInputParser();
		  List<Object> returnValue6 = parser6.createShapeAndPoints(new StringReader(input6));
		  
		  PolygonalShape shape6 = (PolygonalShape)returnValue6.get(0);
		  ArrayList<Point> points6 = (ArrayList<Point>)returnValue6.get(1);
		  
		  assertEquals(false, Utility.areAllPointsWithinAShape(shape6, points6));

	}
	
	//Bonus1
	@SuppressWarnings("unchecked")
	@Test
	public void testMainForConvexPolygon() throws IOException, PointsWithinASquareInputParserException {
		  String input1 = "6 0,1 2,2 1,3 1,0 2,1 0,2 1,1";
		  PointsWithinASquareInputParser parser = new PointsWithinASquareInputParser();
		  List<Object> returnValue = parser.createShapeAndPoints(new StringReader(input1));
		  
		  PolygonalShape shape = (PolygonalShape)returnValue.get(0);
		  ArrayList<Point> points = (ArrayList<Point>)returnValue.get(1);
		  
		  assertEquals(true, Utility.areAllPointsWithinAShape(shape, points));
		  
		  String input2 = "6 1,0 2,1 1,3 0,1 2,2 0,2 2,4 1,1";
		  returnValue = parser.createShapeAndPoints(new StringReader(input2));
		  
		  shape = (PolygonalShape)returnValue.get(0);
		  points = (ArrayList<Point>)returnValue.get(1);
		  
		  assertEquals(false, Utility.areAllPointsWithinAShape(shape, points));

	}
}

package com.solium.pointsWithinASquare.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.solium.pointsWithinASquare.Point;
import com.solium.pointsWithinASquare.PolygonalShape;
import com.solium.pointsWithinASquare.Utility;

/**
 * Class to create a Graph based on a matrix configuration in a text file.  
 * The configuration must consist of the following:
 * Square: A set of 4 points identifying the square followed by the set of points to be determined if they lie in the square or outside 
 * Convex Polygon: Starts with a number specifying the number of vertices for polygon, and then the polygon vertices, and after that
 * 					comes the points
 */
public class PointsWithinASquareInputParser {
	
	List<Point> points = new ArrayList<Point>();
	PolygonalShape shape = new PolygonalShape();

	/**
	 * @param input
	 * @return The polygon and the set of points
	 * @throws IOException
	 * @throws PointsWithinASquareInputParserException
	 */
    public ArrayList<Object> createShapeAndPoints(Reader input) throws IOException, PointsWithinASquareInputParserException {
    	ArrayList<Object> returnValue = new ArrayList<Object>();
    	BufferedReader bin = new BufferedReader(input);
        
        String line = bin.readLine();
        
        if (line == null)
        	throw new PointsWithinASquareInputParserException("File is empty");

        StringTokenizer lineTokenizer = new StringTokenizer(line, " ");
        
        List<Point> polygonPoints = new ArrayList<Point>();
        
        String token = lineTokenizer.nextToken().trim();
        
        //Not a square, number of polygon edges are more than 4
        if (!token.contains(",")) {
        	int numberOfVertices = Integer.parseInt(token);
	        for (int i = 0; i < numberOfVertices; i++) {
	        	if (!lineTokenizer.hasMoreElements())
	        		throw new PointsWithinASquareInputParserException("Invalid number of points");
	        	token = lineTokenizer.nextToken().trim();
	        	StringTokenizer pointTokenizer = new StringTokenizer(token, ",");
	        
	        	Double x = Double.parseDouble(pointTokenizer.nextElement().toString());
	        	
	        	if (!pointTokenizer.hasMoreElements())
	        		throw new PointsWithinASquareInputParserException("Missing y coordinate");
	        	
	        	Double y = Double.parseDouble(pointTokenizer.nextElement().toString());
	        	
	        	Point p = new Point(x, y);
	        	polygonPoints.add(p);
	        }      	
        }
        //A square
        else {
        //square coordinates
	        for (int i = 0; i < 4; i++) {
	        	StringTokenizer pointTokenizer = new StringTokenizer(token, ",");
	        
	        	Double x = Double.parseDouble(pointTokenizer.nextElement().toString());
	        	
	        	if (!pointTokenizer.hasMoreElements())
	        		throw new PointsWithinASquareInputParserException("Missing y coordinate");
	        	
	        	Double y = Double.parseDouble(pointTokenizer.nextElement().toString());
	        	
	        	Point p = new Point(x, y);
	        	polygonPoints.add(p);
	        	
	        	if (i != 3) {
		        	if (!lineTokenizer.hasMoreElements())
		        		throw new PointsWithinASquareInputParserException("Invalid number of points");
	        		token = lineTokenizer.nextToken().trim();
	        	}
	        }
        }
        
        shape.setVertices(Utility.sortPointsInClockWiseOrder(polygonPoints));
        
        if (!lineTokenizer.hasMoreElements())
        	throw new PointsWithinASquareInputParserException("No points specified in the input file");
        
        //get the points
        while (lineTokenizer.hasMoreElements()) {
        	String pointCoordinates = lineTokenizer.nextToken().trim();
        	StringTokenizer pointTokenizer = new StringTokenizer(pointCoordinates, ",");
        
        	Double x = Double.parseDouble(pointTokenizer.nextElement().toString());
        	if (!pointTokenizer.hasMoreElements())
        		throw new PointsWithinASquareInputParserException("Missing y coordinate");
        	Double y = Double.parseDouble(pointTokenizer.nextElement().toString());
        	Point p = new Point(x, y);
        	points.add(p);
        }
        
        returnValue.add(shape);
        returnValue.add(points);
        
		return returnValue;
    }
}
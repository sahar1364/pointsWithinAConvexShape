package com.solium.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.solium.pointsWithinASquare.Point;
import com.solium.pointsWithinASquare.PolygonalShape;
import com.solium.pointsWithinASquare.Utility;
import com.solium.pointsWithinASquare.parser.PointsWithinASquareInputParser;
import com.solium.pointsWithinASquare.parser.PointsWithinASquareInputParserException;

/**
 * Class with main method for getting inputs for the geometric shape(e.g. square) and the coordinates
 * of the points
 * This program finds out whether all the given points lie inside the shape or not 
 */
public class PointsWithinASquare {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
        if (args.length == 0 || args.length > 3) {
            System.err.println();
            System.err.println("PointsWithinASquare requires one argument, the name of the input file.");
            System.err.println();
            System.err.println("Example: java PointsWithinASquare/input/input.txt");
            System.err.println();
            System.exit(1);
        }
        try {
        	File input = new File(args[0]);
        	
        	//construct the shape and get the points from the input file
        	ArrayList<Object> shapeAndPoints = new PointsWithinASquareInputParser().createShapeAndPoints(new FileReader(input));
        	System.out.print(Utility.areAllPointsWithinAShape((PolygonalShape)shapeAndPoints.get(0), (ArrayList<Point>)shapeAndPoints.get(1))?"True":"False");
        }
        catch (IOException e) {
        	System.out.print(e.getMessage());
        }
        catch (PointsWithinASquareInputParserException e) {
        	System.out.print(e.getMessage());
        }
	}
}

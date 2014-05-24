This is a solution to the PointsWithinASquare problem. Given a set of (x,y) coordinates on a Euclidean geometric plane,
and a set of points identifying a convex polygon, determine if all the points are contained within the defined polygon. 
Points that are found on the sides of the polygon or the corners of the polygon count as points contained within the polygon.
 
The input file for the system is located in the input directory and based on the input format mentioned in 
the document.

To build and view the outputs for the provided inputs:

>ant compile create.jar 
>java -jar <jar file> input/input.txt

The solution has been tested and built under OS X 10.9.2 using java version 1.6.0_65

Given a set of points identifying a square or a convex polygon, the program calculates whether all the points
lie within the square or convex polygon or not - If yes then prints True, if not prints False

The code builds a polygon based on the input file and creates a list of points which should be checked against the polygon
to decide if they lie inside or outside of it. There is a Utility class which is responsible for all the vector-based 
calculations. 
First, we sort the polygon vertices in clockwise since they can be in an arbitrary order. Then, to decide if the point p
lies in the polygon, we do vector-based calculations to see if the sum of the angles that the vectors (Pi->p) and (Pi+1->p)
make is 2*PI where P0..Pn are the polygon vertices. If the sum of the angles is equal to 2*PI it means p lies within the
convex polygon

NOTE: There is an Epsilon value defined in Utility class which can be modified to get the desired accuracy 
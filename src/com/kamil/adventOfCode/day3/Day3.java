package com.kamil.adventOfCode.day3;

import com.kamil.adventOfCode.utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {
    private final static String PATH = "src/com/kamil/adventOfCode/day3/InputDay3.txt";

    public static long solveProblemA(String path){

        List<Point> intersections = processDataAndFindIntersections(path);
        if (intersections.isEmpty()) return -1;
        long closestDistance = Long.MAX_VALUE;
        int distance;
        for (Point p : intersections) {
            distance = Math.abs(p.getX()) + Math.abs(p.getY());
            if (distance != 0 && distance < closestDistance)
                closestDistance = distance;
        }

        return closestDistance;
    }

    public static long solveProblemB(String inputPath){
        List<Point> intersections = processDataAndFindIntersections(inputPath);
        if (intersections.isEmpty()) return -1;
        long closestRoutesCombination = Long.MAX_VALUE;
        int distance;
        //in case of intersections the distance from the root represents sum of both distances;
        for (Point p : intersections) {
            distance = p.getDistanceFromTheRoot();
            if (distance != 0 && distance < closestRoutesCombination)
                closestRoutesCombination = distance;
        }

        return closestRoutesCombination;

    }

    public static long solveProblemB(){
        return solveProblemB(PATH);
    }

    public static long solveProblemA(){
        return solveProblemA(PATH);
    }

    private static List<Point> processDataAndFindIntersections(String path){
        List<String> listOfStrings = Utilities.loadInputAsListOfStrings(path);
        String[] instructionsWire1 = listOfStrings.get(0).split(",");
        String[] instructionsWire2 = listOfStrings.get(1).split(",");
        Point[] pathWire1 = findPath(instructionsWire1);
        Point[] pathWire2 = findPath(instructionsWire2);

        List<Point> intersections = findIntersections(pathWire1, pathWire2);
        return intersections;
    }

    private static Point[] findPath(String[] instructions){
        Point[] path = new Point[instructions.length + 1];
        path[0] = new Point(0,0);
        int x;
        int y;
        for (int i =0; i < instructions.length; i++) {
            x = path[i].getX();
            y = path[i].getY();

            char direction = instructions[i].charAt(0);
            int distance = Integer.parseInt(instructions[i].substring(1));

            switch (direction){
                case 'R': {
                    x += distance;;
                    break;
                }
                case 'L': {
                    x -= distance;
                    break;
                }
                case 'U': {
                    y += distance;
                    break;
                }
                case 'D': {
                    y -= distance;
                    break;
                }
            }
            Point p = new Point(x, y);
            p.setDistanceFromTheRoot(distance + path[i].getDistanceFromTheRoot());
            path[i+1] = p;
        }
        return path;
    }

    private static List<Point> findIntersections(Point[] path1, Point[] path2) {
        List<Point> intersections = new ArrayList<>();
        Point intersection;
        for (int i = 1; i < path1.length ; i++) {
            Point section1Start = path1[i-1];
            Point section1End = path1[i];
            for (int j = 1; j < path2.length ; j++) {
                Point section2Start = path2[j-1];
                Point section2End = path2[j];
                intersection = findIntersection(section1Start, section1End, section2Start, section2End);
                if (intersection != null ) {
                    int distance1 = path1[i-1].getDistanceFromTheRoot() + measureDistance(intersection, path1[i-1]);
                    int distance2 = path2[j-1].getDistanceFromTheRoot() + measureDistance(intersection, path2[j-1]);

                    intersection.setDistanceFromTheRoot(distance1 + distance2);
                    intersections.add(intersection);
                }
            }
        }
        return intersections;
    }

    private static int measureDistance(Point pointA, Point pointB){
        if (pointA.getX() == pointB.getX()) return Math.abs(pointA.getY() - pointB.getY());
        else return Math.abs(pointA.getX() - pointB.getX());
    }

    private static Point findIntersection(Point section1Start, Point section1End, Point section2Start, Point section2End) {

        int x1 = section1Start.getX();
        int y1 = section1Start.getY();
        int x2 = section1End.getX();
        int y2 = section1End.getY();

        int x3 = section2Start.getX();
        int y3 = section2Start.getY();
        int x4 = section2End.getX();
        int y4 = section2End.getY();

        if (x1 == x2){
            if (x3 == x4) {
                if (x1 == x3) {
                    Integer possibleIntersectionY = findIntersectingClosestToZero(y1,y2,y3,y4);
                    if (possibleIntersectionY == null) return null;
                    return new Point(x1, possibleIntersectionY);
                }
                return null;
            }
            if (x1 >= Math.min(x3, x4) && x1 <= Math.max(x3, x4) && y3 >= Math.min(y1, y2) && y3 <= Math.max(y1, y2)) return new Point(x1, y3);
            return null;
        } else if (y1 == y2) {
            if (y3 == y4) {
                if (y1 == y3) {
                    Integer possibleIntersectionX = findIntersectingClosestToZero(x1,x2,x3,x4);
                    if (possibleIntersectionX == null) return null;
                    return new Point(possibleIntersectionX, y1);
                }
                return null;
            }
            if (y1 >= Math.min(y3, y4) && y1 <= Math.max(y3, y4) && x3 >= Math.min(x1, x2) && x3 <= Math.max(x1, x2)) return new Point(x3,y1);
            return null;
        }
        return null;
    }

    private static Integer findIntersectingClosestToZero(int startA, int endA, int startB, int endB){
        int intersectingStart = Math.max(Math.min(startA,endA),Math.min(startB, endB));
        int intersectingEnd = Math.min(Math.max(startA,endA),Math.max(startB, endB));
        if (intersectingEnd < intersectingStart) return null;
        if (intersectingStart <= 0 && intersectingEnd >= 0) return 0;
        else if (intersectingEnd <= 0 && intersectingEnd <= 0) return intersectingEnd;
        else if (intersectingEnd >= 0 && intersectingEnd >= 0) return intersectingStart;
        return null;
    }


}

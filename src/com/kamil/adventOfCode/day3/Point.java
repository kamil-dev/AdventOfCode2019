package com.kamil.adventOfCode.day3;

public class Point {
    private int x;
    private int y;
    private int distanceFromTheRoot;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistanceFromTheRoot() {
        return distanceFromTheRoot;
    }

    public void setDistanceFromTheRoot(int distanceFromTheRoot) {
        this.distanceFromTheRoot = distanceFromTheRoot;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")" ;
    }
}

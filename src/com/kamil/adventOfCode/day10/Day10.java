package com.kamil.adventOfCode.day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day10 {
    public final static String PATH = "src/com/kamil/adventOfCode/day10/InputDay10.txt";
    private static Asteroid[][] matrixOfAsteroids;
    private static List<Asteroid> allAsteroids;

    public static void loadData(String path) {
        Object[] objects;
        String[] lines;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            objects = bufferedReader.lines().toArray();
            lines = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                lines[i] = (String)objects[i];
            }
            int xSize = lines[0].length();
            int ySize = lines.length;
            matrixOfAsteroids = new Asteroid[xSize][ySize];
            allAsteroids = new LinkedList<>();
            for (int i = 0; i < lines.length ; i++) {
                for (int j = 0; j < lines.length ; j++) {
                    matrixOfAsteroids[j][i] = lines[i].charAt(j) == '#' ? new Asteroid(j, i) : null ;
                    if (lines[i].charAt(j) == '#') allAsteroids.add(new Asteroid(j,i));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<Asteroid> findConnectionsBetweenAsteroids(){

        for (Asteroid a : allAsteroids) {
            for (Asteroid b : allAsteroids){
                if (!a.equals(b)) {
                    areVisibleToEachOther(a,b);
                }
            }
        }
        return allAsteroids;
    }

    public static int solveProblemA(String path){
        return findAsteroidWithMostVisible(path).getVisibleAsteroids().size();
    }

    private static Asteroid findAsteroidWithMostVisible(String path){
        loadData(path);
        findConnectionsBetweenAsteroids();
        int maxNumOfVisibleAsteroids = 0;
        Asteroid searchedAsteroid = null;
        int numOfConnections;
        for (Asteroid a : allAsteroids){

            numOfConnections = a.getVisibleAsteroids().size();
            if (numOfConnections > maxNumOfVisibleAsteroids) {
                maxNumOfVisibleAsteroids = numOfConnections;
                searchedAsteroid = a;
            }
        }
        return searchedAsteroid;

    }

    public static int solveProblemB(String path, int numOfSearchedAsteroid){
        Asteroid a = findAsteroidWithMostVisible(path);
//        System.out.println("Central asteroid: " + a);
        Set<Asteroid> visibleAsteroids = a.getVisibleAsteroids();
        List<Asteroid> visibleAsteroidsAsList = new ArrayList<>(visibleAsteroids
        );
        Collections.sort(visibleAsteroidsAsList, new Comparator<Asteroid>() {
            @Override
            public int compare(Asteroid a1, Asteroid a2) {
                double angleA1 = calculateAngleInRadians(a,a1);
                double angleA2 = calculateAngleInRadians(a,a2);
                return (int)(1000 * (angleA1 - angleA2));
            }
        });
//        for (int i = 0; i < visibleAsteroidsAsList.size() ; i++) {
//            System.out.println(i+ 1 + ". " + visibleAsteroidsAsList.get(i) + ", angle: " + calculateAngleInRadians(a, visibleAsteroidsAsList.get(i)));
//        }
        return visibleAsteroidsAsList.get(numOfSearchedAsteroid).getX() * 100 + visibleAsteroidsAsList.get(numOfSearchedAsteroid).getY();

    }

    public static double calculateAngleInRadians(Asteroid centralAsteroid, Asteroid visibleAsteroid){
        int x1 = centralAsteroid.getX();
        int y1 = centralAsteroid.getY();
        int x2 = visibleAsteroid.getX();
        int y2 = visibleAsteroid.getY();
        double angle;

        //angle between 0 and pi/2
        if (x2 >= x1 && y2 <= y1 ) {
            if (y2 == y1) {
                angle = Math.PI/2;
            } else {
                angle = Math.atan((double)(x2 - x1) / (y1 - y2));
            }
        }
        //angle between pi/2 and pi
        else if (x2 >= x1 && y2 >= y1){
            if (x2 == x1) {
                angle = Math.PI;
            } else {
                angle = Math.PI/2 + Math.atan((double)(y2 - y1) / (x2 - x1));
            }
        }
        //angle between pi and 3pi/2
        else if (x2 <= x1 && y2 >= y1){
            if (y2 == y1) {
                angle = 3 * Math.PI/2;
            } else {
                angle = Math.PI + Math.atan((double)(x1 - x2) / (y2 - y1));
            }
        }
        //angle between 3pi/2 and 2pi
        else {
            if (x2 == x1) {
                angle = 2 * Math.PI;
            } else {
                angle = 3* Math.PI/2 + Math.atan((double)(y1 - y2) / (x1 - x2));
            }

        }
        return angle;

    }



    private static void areVisibleToEachOther(Asteroid a, Asteroid b){
        if (a.getInvisibleAsteroids().contains(b)) return;
        if (a.getVisibleAsteroids().isEmpty()) {
            a.addNewVisibleAsteroid(b);
            return;
        }
        boolean flagToAdd = false;
        Set<Asteroid> visibleAsteroidsCopy = new HashSet<>(a.getVisibleAsteroids());
        for (Asteroid visibleFromA : visibleAsteroidsCopy) {
            if (!isViewObstructed(a,b, visibleFromA)) {
                flagToAdd = true;
            } else {
                if (isAsteroidCloser(a,b,visibleFromA)) {
                    flagToAdd = true;
                    a.addNewInvisibleAsteroid(visibleFromA);
                    a.removeFromVisibles(visibleFromA);
                } else {
                    flagToAdd = false;
                    break;
                }
            }
        }
        if (flagToAdd) a.addNewVisibleAsteroid(b);

    }

    public static boolean isViewObstructed(Asteroid a, Asteroid b, Asteroid c){
        if (a.getY() == b.getY() || a.getY() == c.getY()) {
            if (b.getY() != c.getY()) return false;
            else if ((a.getX() > b.getX() && c.getX() > a.getX()) || (a.getX() > c.getX() && a.getX() < b.getX())) return false;
            else return true;
        } else if (areOnTheSameLine(a,b,c)) {
            if (isBetween(a, b, c)) return false;
            else return true;
        }
        return false;
    }

    private static boolean isAsteroidCloser(Asteroid a, Asteroid b, Asteroid c){
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) < Math.abs(a.getX() - c.getX()) + Math.abs(a.getY() - c.getY());
    }

    public static boolean areOnTheSameLine(Asteroid a, Asteroid b, Asteroid c){
        return Math.abs(c.getX() - (c.getY() * ((double)(a.getX() - b.getX())/(a.getY()- b.getY())) + a.getX() - ((double)(a.getX() - b.getX())/(a.getY()- b.getY()))* a.getY())) < 0.00001;
    }

    public static boolean isBetween(Asteroid a, Asteroid b, Asteroid c){
        double distanceCB = calculateDistance(c,b);
        double distanceAB = calculateDistance(a,b);
        double distanceAC = calculateDistance(a,c);
        return distanceCB > Math.max(distanceAB, distanceAC);
    }

    private static double calculateDistance(Asteroid a, Asteroid b){
        return Math.sqrt(Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()-b.getY(),2));
    }



    public static class Asteroid {
        private int x;
        private int y;
        private Set<Asteroid> visibleAsteroids = new HashSet<>();
        private Set<Asteroid> invisibleAsteroids = new HashSet<>();

        public Asteroid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void addNewVisibleAsteroid(Asteroid asteroid){
            visibleAsteroids.add(asteroid);
        }

        public Set<Asteroid> getVisibleAsteroids() {
            return visibleAsteroids;
        }

        public void addNewInvisibleAsteroid(Asteroid asteroid){
            invisibleAsteroids.add(asteroid);
        }

        public Set<Asteroid> getInvisibleAsteroids() {
            return invisibleAsteroids;
        }

        public void addInvisibleAsteroids(List<Asteroid> asteroids){
            invisibleAsteroids.addAll(asteroids);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void removeFromVisibles(Asteroid a){
            visibleAsteroids.remove(a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Asteroid asteroid = (Asteroid) o;
            return x == asteroid.x &&
                    y == asteroid.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Asteroid{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }




}

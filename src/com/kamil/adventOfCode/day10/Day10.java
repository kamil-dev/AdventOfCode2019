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
        loadData(path);
        findConnectionsBetweenAsteroids();
        int maxNumOfVisibleAsteroids = 0;
        int numOfConnections;
        for (Asteroid a : allAsteroids){

            numOfConnections = a.getVisibleAsteroids().size();
            if (numOfConnections > maxNumOfVisibleAsteroids) maxNumOfVisibleAsteroids = numOfConnections;
        }
        return maxNumOfVisibleAsteroids;
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

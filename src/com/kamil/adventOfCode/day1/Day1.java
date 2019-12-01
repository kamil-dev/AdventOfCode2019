package com.kamil.adventOfCode.day1;

import com.kamil.adventOfCode.utilities.Utilities;

import java.util.List;

public class Day1 {
    private final static String PATH = "src/com/kamil/adventOfCode/day1/InputDay1part1.txt";

    public static long solveProblemA(String path){
        List<Integer> data = Utilities.loadIntegers(path);
        long result = 0;

        for (Integer value: data){
            result+= value/3 - 2;
        }
        return result;
    }

    public static long solveProblemA(){
        return solveProblemA(PATH);
    }

    public static long solveProblemB(String path){
        List<Integer> data = Utilities.loadIntegers(path);
        long result = 0;

        for (Integer value: data){
            result+= calculateFuel(value);
        }
        return result;
    }

    public static long solveProblemB(){
        return solveProblemB(PATH);
    }

    private static int calculateFuel(int massAtStart){
        if (massAtStart < 9 ){
            return 0;
        }
        int calculatedFuel = massAtStart/3 -2;

        return calculatedFuel + calculateFuel(calculatedFuel);
    }
}

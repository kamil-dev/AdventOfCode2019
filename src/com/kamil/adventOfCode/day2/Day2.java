package com.kamil.adventOfCode.day2;

import com.kamil.adventOfCode.utilities.Utilities;

import java.util.Arrays;

public class Day2 {
    private final static String PATH = "src/com/kamil/adventOfCode/day2/InputDay2.txt";
    private static int[] memory = null;

    private static void initializeData(String path){
        String input = Utilities.loadInputAsString(path);
        String[] arrOfStrings = input.split(",");
        int[] arrOfIntegers = new int[arrOfStrings.length];
        for (int i=0; i < arrOfStrings.length; i++) {
            arrOfIntegers[i] = Integer.parseInt(arrOfStrings[i]);
        }
        memory = arrOfIntegers;
    }

    public static long solveProblemA(String path, boolean initialReplacement, int noun, int verb){

        if (memory == null) initializeData(path);
        int[] arrOfIntegers = memory.clone();

        if (initialReplacement) {
            arrOfIntegers[1] = noun; //replacements
            arrOfIntegers[2] = verb;
        }

        int opcode;
        for (int i = 0; i < arrOfIntegers.length ; i+=4) {
            opcode = arrOfIntegers[i];
            if (opcode == 99) return arrOfIntegers[0];
            else if (opcode == 1) {
                arrOfIntegers[arrOfIntegers[i+3]] = arrOfIntegers[arrOfIntegers[i+1]] + arrOfIntegers[arrOfIntegers[i+2]];
            } else if (opcode == 2) {
                arrOfIntegers[arrOfIntegers[i+3]] = arrOfIntegers[arrOfIntegers[i+1]] * arrOfIntegers[arrOfIntegers[i+2]];
            } else {
                throw new IllegalArgumentException("invalid opcode");
            }
        }

        return arrOfIntegers[0];
    }

    public static long solveProblemA(){
        return solveProblemA(PATH, true, 12, 2);
    }

    public static String solveProblemB(long expectedOutput) {

        long output;

        //value between 0-99
        for (int noun = 0; noun <= 99 ; noun++) {
            for (int verb = 0; verb <= 99; verb++) {

                output = solveProblemA(PATH, true, noun, verb);
                if (expectedOutput == output) return String.valueOf(noun * 100 + verb);
            }
        }

        return null;

    }


}

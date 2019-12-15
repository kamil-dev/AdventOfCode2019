package com.kamil.adventOfCode.day5;

import com.kamil.adventOfCode.utilities.Utilities;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Day5 {
    private final static String PATH = "src/com/kamil/adventOfCode/day5/InputDay5.txt";
    private static int testInputCaseA = 1;
    private static int testInputCaseB = 5;
    public static int[] memory = null;

    private static void initializeData(String path) {
        String input = Utilities.loadInputAsString(path);
        String[] arrOfStrings = input.split(",");
        int[] arrOfIntegers = new int[arrOfStrings.length];
        for (int i = 0; i < arrOfStrings.length; i++) {
            arrOfIntegers[i] = Integer.parseInt(arrOfStrings[i]);
        }
        memory = arrOfIntegers;
    }

    public static Integer solveProblemA() {
        int result = 0;
        if (memory == null) initializeData(PATH);
        for (int i = 0; i < memory.length; i += 2) {
            int optCode = memory[i] % 10;
            if (optCode == 9) return result;
            else if (optCode == 4) {
                if (memory[i] == 104) result = memory[i + 1];
                else result = memory[memory[i + 1]];
            } else if (optCode == 3) {
                if (memory[i] == 103) memory[i + 1] = testInputCaseA;
                else memory[memory[i + 1]] = testInputCaseA;
            } else if (optCode == 2) {
                if (memory[i] == 1102) memory[memory[i + 3]] = memory[i + 1] * memory[i + 2];
                else if (memory[i] == 1002) memory[memory[i + 3]] = memory[memory[i + 1]] * memory[i + 2];
                else if (memory[i] == 102) memory[memory[i + 3]] = memory[i + 1] * memory[memory[i + 2]];
                else memory[memory[i + 3]] = memory[memory[i + 1]] * memory[memory[i + 2]];
                i += 2;
            } else if (optCode == 1) {
                if (memory[i] == 1101) memory[memory[i + 3]] = memory[i + 1] + memory[i + 2];
                else if (memory[i] == 1001) memory[memory[i + 3]] = memory[memory[i + 1]] + memory[i + 2];
                else if (memory[i] == 101) memory[memory[i + 3]] = memory[i + 1] + memory[memory[i + 2]];
                else {
                    memory[memory[i + 3]] = memory[memory[i + 1]] + memory[memory[i + 2]];
                }
                i += 2;
            }
        }
        return null;
    }

    public static Integer solveProblemB(String path, int input) {
        Integer result = null;
        initializeData(path);
        for (int i = 0; i < memory.length; i += 2) {
            int optCode = memory[i] % 10;
            if (optCode == 9) return result;
            else if (optCode == 5) {
                if (memory[i] == 1005) {
                    if (memory[memory[i + 1]] != 0) i = memory[i + 2] - 3;
                } else if (memory[i] == 105) {
                    if (memory[i + 1] != 0) i = memory[memory[i + 2]] - 3;
                } else if (memory[i] == 1105) {
                    if (memory[i + 1] != 0) i = memory[i + 2] - 3;
                } else if (memory[i] == 5) {
                    if (memory[memory[i + 1]] != 0) i = memory[memory[i + 2]] - 3;
                }
                i += 1;
            } else if (optCode == 6) {
                if (memory[i] == 1006) {
                    if (memory[memory[i + 1]] == 0) i = memory[i + 2] - 3;
                } else if (memory[i] == 106) {
                    if (memory[i + 1] == 0) i = memory[memory[i + 2]] - 3;
                } else if (memory[i] == 1106) {
                    if (memory[i + 1] == 0) i = memory[i + 2] - 3;
                } else if (memory[i] == 6) {
                    if (memory[memory[i + 1]] == 0) i = memory[memory[i + 2]] - 3;
                }
                i += 1;
            } else if (optCode == 7) {
                if (memory[i] == 1107){
                    if (memory[i+1] < memory[i+2]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;
                } else if (memory[i] == 1007){
                    if (memory[memory[i+1]] < memory[i+2]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;

                } else if (memory[i] == 107) {
                    if (memory[i+1] < memory[memory[i+2]]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;
                } else {
                    if (memory[memory[i+1]] < memory[memory[i+2]]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;
                }
                i += 2;
            } else if (optCode == 8) {
                if (memory[i] == 1108){
                    if (memory[i+1] == memory[i+2]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;
                } else if (memory[i] == 1008){
                    if (memory[memory[i+1]] == memory[i+2]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;

                } else if (memory[i] == 108) {
                    if (memory[i+1] == memory[memory[i+2]]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;
                } else {
                    if (memory[memory[i+1]] == memory[memory[i+2]]) memory[memory[i+3]] = 1;
                    else memory[memory[i+3]] = 0;
                }
                i += 2;
            } else if (optCode == 4) {
                if (memory[i] == 104) result = memory[i + 1];
                else result = memory[memory[i + 1]];
            } else if (optCode == 3) {
                if (memory[i] == 103) memory[i + 1] = input;
                else memory[memory[i + 1]] = input;
            } else if (optCode == 2) {
                if (memory[i] == 1102) memory[memory[i + 3]] = memory[i + 1] * memory[i + 2];
                else if (memory[i] == 1002) memory[memory[i + 3]] = memory[memory[i + 1]] * memory[i + 2];
                else if (memory[i] == 102) memory[memory[i + 3]] = memory[i + 1] * memory[memory[i + 2]];
                else memory[memory[i + 3]] = memory[memory[i + 1]] * memory[memory[i + 2]];
                i += 2;
            } else if (optCode == 1) {
                if (memory[i] == 1101) memory[memory[i + 3]] = memory[i + 1] + memory[i + 2];
                else if (memory[i] == 1001) memory[memory[i + 3]] = memory[memory[i + 1]] + memory[i + 2];
                else if (memory[i] == 101) memory[memory[i + 3]] = memory[i + 1] + memory[memory[i + 2]];
                else {
                    memory[memory[i + 3]] = memory[memory[i + 1]] + memory[memory[i + 2]];
                }
                i += 2;
            }
        }
        return null;
    }

    public static Integer solveProblemB(){
        return solveProblemB(PATH, testInputCaseB);
    }


}

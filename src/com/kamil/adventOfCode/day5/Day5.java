package com.kamil.adventOfCode.day5;

import com.kamil.adventOfCode.utilities.Utilities;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Day5 {
    private final static String PATH = "src/com/kamil/adventOfCode/day5/InputDay5.txt";
    private static int testInput = 1;
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

    public static void solveProblemA(){
        if (memory == null )initializeData(PATH);
        for (int i=0; i < memory.length; i+=2){
            int optCode = memory[i] % 10;
            if (optCode == 9) return;
            else if (optCode == 4) {
                if (memory[i] == 104) System.out.println(memory[i+1]);
                else System.out.println(memory[memory[i+1]]);
            }
            else if (optCode == 3) {
                if (memory[i] == 103) memory[i+1] = testInput;
                    else memory[memory[i+1]] = testInput;
            }
            else if (optCode == 2) {
                if (memory[i] == 1102) memory[memory[i+3]] = memory[i+1] * memory[i+2];
                else if (memory[i] == 1002) memory[memory[i+3]] = memory[memory[i+1]] * memory[i+2];
                else if (memory[i] == 102) memory[memory[i+3]] = memory[i+1] * memory[memory[i+2]];
                else memory[memory[i+3]] = memory[memory[i+1]] * memory[memory[i+2]];
                i+=2;
            }
            else if (optCode == 1) {
                if (memory[i] == 1101) memory[memory[i+3]] = memory[i+1] + memory[i+2];
                else if (memory[i] == 1001) memory[memory[i+3]] = memory[memory[i+1]] + memory[i+2];
                else if (memory[i] == 101) memory[memory[i+3]] = memory[i+1] + memory[memory[i+2]];
                else memory[memory[i+3]] = memory[memory[i+1]] + memory[memory[i+2]];
                i+=2;
            }
        }
    }


}

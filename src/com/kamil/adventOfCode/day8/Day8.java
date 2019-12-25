package com.kamil.adventOfCode.day8;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day8 {

    private final static String PATH = "src/com/kamil/adventOfCode/day8/InputDay8.txt";

    public static int solveProblemA(String path) {
        char[] buffer = new char[25 * 6];
        char[] searchedLayer = null;
        int minNumOfZeros = Integer.MAX_VALUE;
        int numOfZeros = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

                while (bufferedReader.read(buffer) != -1) {
                    numOfZeros = countDigits('0', buffer);
                    if (numOfZeros < minNumOfZeros) {
                        searchedLayer = buffer.clone();
                        minNumOfZeros = numOfZeros;
                    }
                }
            return countDigits('1', searchedLayer) * countDigits('2', searchedLayer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int solveProblemA() {
        return solveProblemA(PATH);
    }

    public static int countDigits(char digit, char[] buffer){
        int counter = 0;
        for (char d: buffer) {
            if (d == digit) counter++;
        }
        return counter;
    }



}

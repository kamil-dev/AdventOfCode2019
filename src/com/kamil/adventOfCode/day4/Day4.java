package com.kamil.adventOfCode.day4;

import java.util.Stack;

public class Day4 {


    public static int solveProblemA(int lowerBound, int upperBound){
        int counter = 0;
        for (int i = lowerBound; i <= upperBound; i++) {
            if (validateNumberCaseA(i)) counter++;
        }
        return counter;
    }

    public static int solveProblemA(){
        return solveProblemA(347312, 805915);
    }

    public static int solveProblemB(int lowerBound, int upperBound){
        int counter = 0;
        for (int i = lowerBound; i <= upperBound; i++) {
            if (validateNumberCaseB(i)) counter++;
        }
        return counter;
    }

    public static int solveProblemB(){
        return solveProblemB(347312, 805915);
    }

    public static boolean validateNumberCaseA(Integer number){
        String numAsString = String.valueOf(number);
        char previousDigit = numAsString.charAt(0);
        boolean twoAdjacentDigitsTheSame = false;
        for (int i = 1; i < numAsString.length() ; i++) {
            if (previousDigit > numAsString.charAt(i)) return false;
            if (previousDigit == numAsString.charAt(i)) twoAdjacentDigitsTheSame = true;
            previousDigit = numAsString.charAt(i);
        }
        return twoAdjacentDigitsTheSame;
    }

    public static boolean validateNumberCaseB(Integer number){
        String numAsString = String.valueOf(number);
        boolean[] isPreviousDigitTheSameArr = new boolean[numAsString.length()];
        char previousDigit = numAsString.charAt(0);
        for (int i = 1; i < numAsString.length() ; i++) {
            if (previousDigit > numAsString.charAt(i)) return false;
            if (previousDigit == numAsString.charAt(i)) {
                isPreviousDigitTheSameArr[i] = true;
            }
            previousDigit = numAsString.charAt(i);
        }
        for (int i = 1; i < isPreviousDigitTheSameArr.length - 1 ; i++) {
            if (isPreviousDigitTheSameArr[i] == true && isPreviousDigitTheSameArr[i-1] != true && isPreviousDigitTheSameArr[i+1] != true) return true;
        }
        if (isPreviousDigitTheSameArr[isPreviousDigitTheSameArr.length - 2] != true && isPreviousDigitTheSameArr[isPreviousDigitTheSameArr.length - 1] == true) return true;
        return false;
    }

}

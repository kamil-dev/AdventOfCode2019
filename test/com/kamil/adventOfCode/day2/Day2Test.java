package com.kamil.adventOfCode.day2;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day2Test {

    private final static String PATH1 = "Test/com/kamil/adventOfCode/day2/TestInput1Day2";
    private final static String PATH2 = "Test/com/kamil/adventOfCode/day2/TestInput2Day2";

    @Test
    public void shouldAssertAsTrueCase1(){
        long actualValue = Day2.solveProblemA(PATH1, false, 0, 0);
        long expectedValue = 3500;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldAssertAsTrueCase2(){
        long actualValue = Day2.solveProblemA(PATH2, false, 0 , 0);
        long expectedValue = 30;
        assertEquals(expectedValue, actualValue);
    }

}
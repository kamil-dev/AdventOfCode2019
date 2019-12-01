package com.kamil.adventOfCode.day1;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day1Test {

    private final static String PATH = "Test/com/kamil/adventOfCode/day1/TestInputDay1";

    @Test
    public void shouldAssertASTrueCaseA(){
        long actualValue = Day1.solveProblemA(PATH);
        long expectedValue = 2 + 2 + 654 + 33583;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldAssertASTrueCaseB(){
        long actualValue = Day1.solveProblemB(PATH);
        long expectedValue = 2 + 2 + 966 + 50346;
        assertEquals(expectedValue, actualValue);
    }
}
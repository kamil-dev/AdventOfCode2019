package com.kamil.adventOfCode.day10;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day10Test {
    private final static String PATH1 = "Test/com/kamil/adventOfCode/day10/TestInput1Day10.txt";
    private final static String PATH2 = "Test/com/kamil/adventOfCode/day10/TestInput2Day10.txt";
    private final static String PATH3 = "Test/com/kamil/adventOfCode/day10/TestInput3Day10.txt";
    private final static String PATH4 = "Test/com/kamil/adventOfCode/day10/TestInput4Day10.txt";
    private final static String PATH5 = "Test/com/kamil/adventOfCode/day10/TestInput5Day10.txt";

    @Test
    public void shouldFindMaxOutputCase1(){
        int expected = 8;
        int actual = Day10.solveProblemA(PATH1);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase2(){
        int expected = 33;
        int actual = Day10.solveProblemA(PATH2);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase3(){
        int expected = 35;
        int actual = Day10.solveProblemA(PATH3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase4(){
        int expected = 41;
        int actual = Day10.solveProblemA(PATH4);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase5(){
        int expected = 210;
        int actual = Day10.solveProblemA(PATH5);
        assertEquals(expected, actual);
    }

}
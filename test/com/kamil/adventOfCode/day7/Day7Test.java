package com.kamil.adventOfCode.day7;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day7Test {
    private final static String PATH1 = "Test/com/kamil/adventOfCode/day7/TestInput1Day7.txt";
    private final static String PATH2 = "Test/com/kamil/adventOfCode/day7/TestInput2Day7.txt";
    private final static String PATH3 = "Test/com/kamil/adventOfCode/day7/TestInput3Day7.txt";
    private final static String PATH4 = "Test/com/kamil/adventOfCode/day7/TestInput4Day7.txt";
    private final static String PATH5 = "Test/com/kamil/adventOfCode/day7/TestInput5Day7.txt";

    @Test
    public void shouldFindMaxOutputCase1(){
        int expected = 43210;
        int actual = Day7.solveProblemA(PATH1);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase2(){
        int expected = 54321;
        int actual = Day7.solveProblemA(PATH2);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase3(){
        int expected = 65210;
        int actual = Day7.solveProblemA(PATH3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase4(){
        int expected = 139629729;
        int actual = Day7.solveProblemB(PATH4);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxOutputCase5(){

        int expected = 18216;
        int actual = Day7.solveProblemB(PATH5);
        assertEquals(expected, actual);
    }

}
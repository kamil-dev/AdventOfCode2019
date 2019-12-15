package com.kamil.adventOfCode.day5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day5Test {
    private final static String PATH1 = "Test/com/kamil/adventOfCode/day5/TestInput1Day5.txt";
    private final static String PATH2 = "Test/com/kamil/adventOfCode/day5/TestInput2Day5.txt";
    private final static String PATH3 = "Test/com/kamil/adventOfCode/day5/TestInput3Day5.txt";

    @Before
    public void resetData(){
        Day5.memory = null;
    }

    @Test
    public void shouldOutput1forInput8(){
        int expectedValue = 1;
        int actualValue = Day5.solveProblemB(PATH2, 8);
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shouldOutput0forInputNotEqual8(){
        int expectedValue = 0;
        int actualValue = Day5.solveProblemB(PATH2, 12);
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shouldOutput1forInputLessThan8(){
        int expectedValue = 1;
        int actualValue = Day5.solveProblemB(PATH3, 4);
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shouldOutput0forInputLargerOrEqualTo8(){
        int expectedValue = 0;
        int actualValue = Day5.solveProblemB(PATH3, 15);
        assertEquals(expectedValue, actualValue);

    }




    @Test
    public void shoudReturn999forInputBelow8(){
        int expectedValue = 999;
        int actualValue = Day5.solveProblemB(PATH1, 5);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shoudReturn1000forInputEqual8(){
        int expectedValue = 1000;
        int actualValue = Day5.solveProblemB(PATH1, 8);
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shoudReturn1001forInputAbove8(){
        int expectedValue = 1001;
        int actualValue = Day5.solveProblemB(PATH1, 11);
        assertEquals(expectedValue, actualValue);
    }

}
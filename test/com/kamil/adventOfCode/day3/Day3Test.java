package com.kamil.adventOfCode.day3;

import com.kamil.adventOfCode.day2.Day2;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day3Test {
    private final static String PATH1 = "Test/com/kamil/adventOfCode/day3/TestInput1Day3.txt";
    private final static String PATH2 = "Test/com/kamil/adventOfCode/day3/TestInput2Day3.txt";
    private final static String PATH3 = "Test/com/kamil/adventOfCode/day3/TestInput3Day3.txt";

    @Test
    public void shoudAssertAsTrueInput1CaseA(){
        long actualValue = Day3.solveProblemA(PATH1);
        long expectedValue = 6;
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shoudAssertAsTrueInput2CaseA(){
        long actualValue = Day3.solveProblemA(PATH2);
        long expectedValue = 159;
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shoudAssertAsTrueInput3CaseA(){
        long actualValue = Day3.solveProblemA(PATH3);
        long expectedValue = 135;
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shoudAssertAsTrueInput1CaseB(){
        long actualValue = Day3.solveProblemB(PATH1);
        long expectedValue = 30;
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shoudAssertAsTrueInput2CaseB(){
        long actualValue = Day3.solveProblemB(PATH2);
        long expectedValue = 610;
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void shoudAssertAsTrueInput3CaseB(){
        long actualValue = Day3.solveProblemB(PATH3);
        long expectedValue = 410;
        assertEquals(expectedValue, actualValue);

    }

}
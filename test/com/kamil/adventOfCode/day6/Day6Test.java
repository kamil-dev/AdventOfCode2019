package com.kamil.adventOfCode.day6;

import com.kamil.adventOfCode.day5.Day5;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day6Test {
    private final static String PATH1 = "Test/com/kamil/adventOfCode/day6/TestInput1Day6.txt";

    @Test
    public void shouldAssertAsEquals(){
        int expectedValue = 42;
        int actualValue = Day6.solveProblemA(PATH1);
        assertEquals(expectedValue, actualValue);
    }

}
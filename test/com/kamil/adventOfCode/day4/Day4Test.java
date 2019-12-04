package com.kamil.adventOfCode.day4;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day4Test {


    @Test
    public void shouldValidateAsTrueCaseA(){
        assertTrue(Day4.validateNumberCaseA(111111));
    }

    @Test
    public void shouldValidateAsFalseInput1CaseA(){
        assertFalse(Day4.validateNumberCaseA(223450));
    }

    @Test
    public void shouldValidateAsFalseInput2CaseA(){
        assertFalse(Day4.validateNumberCaseA(123789));
    }

    @Test
    public void shouldValidateAsTrueInput1CaseB(){
        assertTrue(Day4.validateNumberCaseB(112233));
    }

    @Test
    public void shouldValidateAsFalseCaseB(){
        assertFalse(Day4.validateNumberCaseB(123444));
    }

    @Test
    public void shouldValidateAsTrueInput2CaseB(){
        assertTrue(Day4.validateNumberCaseB(111122));
    }

}
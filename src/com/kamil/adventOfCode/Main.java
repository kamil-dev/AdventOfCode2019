package com.kamil.adventOfCode;

import com.kamil.adventOfCode.day1.Day1;
import com.kamil.adventOfCode.day2.Day2;
import com.kamil.adventOfCode.day3.Day3;

public class Main {

    public static void main(String[] args) {
        System.out.println("Day 1 (part1) answer is: " + Day1.solveProblemA());
        System.out.println("Day 1 (part2) answer is: " + Day1.solveProblemB());

        System.out.println("Day 2 (part1) answer is: " + Day2.solveProblemA());
        System.out.println("Day 2 (part2) answer is: " + Day2.solveProblemB(19690720));

        System.out.println("Day 3 (part1) answer is: " + Day3.solveProblemA());
        System.out.println("Day 3 (part2) answer is: " + Day3.solveProblemB());
    }
}

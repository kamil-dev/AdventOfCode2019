package com.kamil.adventOfCode.day7;

import com.kamil.adventOfCode.utilities.Utilities;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Day7 {
    private final static String PATH = "src/com/kamil/adventOfCode/day7/InputDay7.txt";
    private static Integer[] amplifiersControllerSoftware;
    private static List<int[]> possibleCombinations;

    private static void initializeData(String path) {
        String input = Utilities.loadInputAsString(path);
        String[] arrOfStrings = input.split(",");
        Integer[] arrOfIntegers = new Integer[arrOfStrings.length];
        for (int i = 0; i < arrOfStrings.length; i++) {
            arrOfIntegers[i] = Integer.parseInt(arrOfStrings[i]);
        }
        amplifiersControllerSoftware = arrOfIntegers;
    }

    private static void initializeCombinations(List<Integer> valueSet){
        possibleCombinations = new ArrayList<>();
        putCombination(new int[valueSet.size()], valueSet);
    }

    private static void putCombination(int[] combination,List<Integer> remainingValues){
        if (remainingValues.isEmpty()){
            int[] copy = combination.clone();
            possibleCombinations.add(copy);
            return;
        }
        List<Integer> copy = new ArrayList<>();
        for (int i = 0; i < remainingValues.size() ; i++) {
            copy.clear();
            copy.addAll(remainingValues);
            combination[remainingValues.size() - 1] = remainingValues.get(i);
            copy.remove(remainingValues.get(i));
            putCombination(combination, copy);
        }
    }

    public static Integer solveProblemA(String path) {
        initializeData(path);
        initializeCombinations(Arrays.asList(0,1,2,3,4));
        int maxOutput = Integer.MIN_VALUE;
        int initialInput;

        for (int j = 0; j < possibleCombinations.size(); j++) {
            initialInput = 0;
            for (int i = 0; i < 5; i++) {
                initialInput = intcodeComputer(possibleCombinations.get(j)[i], initialInput, amplifiersControllerSoftware.clone(), false, null);
            }
            maxOutput = Math.max(initialInput, maxOutput);
        }
        return maxOutput;
    }

    public static Integer solveProblemA() {
        return solveProblemA(PATH);
    }

    public static Integer solveProblemB(String path) {
        initializeData(path);
        initializeCombinations(Arrays.asList(5,6,7,8,9));

        int maxOutput = Integer.MIN_VALUE;
        int initialInput;

        for (int j = 0; j < possibleCombinations.size(); j++) {
            initialInput = 0;
            Amplifier a1 = new Amplifier(amplifiersControllerSoftware.clone(), possibleCombinations.get(j)[0]);
            Amplifier a2 = new Amplifier(amplifiersControllerSoftware.clone(), possibleCombinations.get(j)[1]);
            Amplifier a3 = new Amplifier(amplifiersControllerSoftware.clone(), possibleCombinations.get(j)[2]);
            Amplifier a4 = new Amplifier(amplifiersControllerSoftware.clone(), possibleCombinations.get(j)[3]);
            Amplifier a5 = new Amplifier(amplifiersControllerSoftware.clone(), possibleCombinations.get(j)[4]);
            while (!a1.isOff() && !a2.isOff() && !a3.isOff() && !a4.isOff() && !a5.isOff()) {
                initialInput = a1.generateOutput(initialInput);
                initialInput = a2.generateOutput(initialInput);
                initialInput = a3.generateOutput(initialInput);
                initialInput = a4.generateOutput(initialInput);
                initialInput = a5.generateOutput(initialInput);
            }
            maxOutput = Math.max(initialInput, maxOutput);
        }
        return maxOutput;
    }

    public static Integer solveProblemB() {
        return solveProblemB(PATH);
    }

    private static class Amplifier{
        private Integer[] amplifiersControllerSoftware;
        private int phaseSetting;
        private int lastIndex;
        private boolean isOff;

        public Amplifier(Integer[] amplifiersControllerSoftware, int phaseSetting) {
            this.amplifiersControllerSoftware = amplifiersControllerSoftware;
            this.phaseSetting = phaseSetting;
            lastIndex = 0;
            isOff = false;
        }

        public Integer generateOutput(int input){
            if (isOff) return input;
            return intcodeComputer(phaseSetting, input, amplifiersControllerSoftware, true, this);
        }

        public int getLastIndex() {
            return lastIndex;
        }

        public void setLastIndex(int lastIndex) {
            this.lastIndex = lastIndex;
        }

        public boolean isOff() {
            return isOff;
        }

        public void setOff(boolean off) {
            isOff = off;
        }
    }



    private static int intcodeComputer(int initialInput, int secondInput, Integer[] software, boolean isContinousOutputModeOn, Amplifier amp){
        int output = 0;
        boolean wasFirstInitialized;
        if (amp == null || amp.getLastIndex() == 0) wasFirstInitialized = false;
        else wasFirstInitialized = true;
        int i;
        if (amp == null) i = 0; else i = amp.getLastIndex();
        for (; i < software.length; i += 2) {
            int optCode = software[i] % 10;
            if (optCode == 9) {
                if (isContinousOutputModeOn) {
                    amp.setOff(true);
                    return secondInput;
                }
                return output;
            }
            else if (optCode == 1) {
                if (software[i] == 1101) software[software[i + 3]] = software[i + 1] + software[i + 2];
                else if (software[i] == 1001) software[software[i + 3]] = software[software[i + 1]] + software[i + 2];
                else if (software[i] == 101) software[software[i + 3]] = software[i + 1] + software[software[i + 2]];
                else {
                    software[software[i + 3]] = software[software[i + 1]] + software[software[i + 2]];
                }
                i += 2;
            } else if (optCode == 2) {
                if (software[i] == 1102) software[software[i + 3]] = software[i + 1] * software[i + 2];
                else if (software[i] == 1002) software[software[i + 3]] = software[software[i + 1]] * software[i + 2];
                else if (software[i] == 102) software[software[i + 3]] = software[i + 1] * software[software[i + 2]];
                else software[software[i + 3]] = software[software[i + 1]] * software[software[i + 2]];
                i += 2;
            } else if (optCode == 3) {
                if (software[i] == 103) {
                    if (!wasFirstInitialized) software[i + 1] = initialInput;
                    else software[i + 1] = secondInput;
                    wasFirstInitialized = true;
                }
                else {
                    if (!wasFirstInitialized) software[software[i + 1]] = initialInput;
                    else software[software[i + 1]] = secondInput;
                    wasFirstInitialized = true;
                }
            } else if (optCode == 4) {
                if (software[i] == 104) {
                    output = software[i + 1];
                    if (isContinousOutputModeOn) {
                        amp.setLastIndex(i+2);
                        return software[i + 1];
                    }
                }
                else {
                    output = software[software[i + 1]];
                    if (isContinousOutputModeOn) {
                        amp.setLastIndex(i+2);
                        return software[software[i + 1]];
                    }
                }
            } else if (optCode == 5) {
                if (software[i] == 1005) {
                    if (software[software[i + 1]] != 0) i = software[i + 2] - 3;
                } else if (software[i] == 105) {
                    if (software[i + 1] != 0) i = software[software[i + 2]] - 3;
                } else if (software[i] == 1105) {
                    if (software[i + 1] != 0) i = software[i + 2] - 3;
                } else if (software[i] == 5) {
                    if (software[software[i + 1]] != 0) i = software[software[i + 2]] - 3;
                }
                i += 1;
            } else if (optCode == 6) {
                if (software[i] == 1006) {
                    if (software[software[i + 1]] == 0) i = software[i + 2] - 3;
                } else if (software[i] == 106) {
                    if (software[i + 1] == 0) i = software[software[i + 2]] - 3;
                } else if (software[i] == 1106) {
                    if (software[i + 1] == 0) i = software[i + 2] - 3;
                } else if (software[i] == 6) {
                    if (software[software[i + 1]] == 0) i = software[software[i + 2]] - 3;
                }
                i += 1;
            } else if (optCode == 7) {
                if (software[i] == 1107){
                    if (software[i+1] < software[i+2]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;
                } else if (software[i] == 1007){
                    if (software[software[i+1]] < software[i+2]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;

                } else if (software[i] == 107) {
                    if (software[i+1] < software[software[i+2]]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;
                } else {
                    if (software[software[i+1]] < software[software[i+2]]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;
                }
                i += 2;
            } else if (optCode == 8) {
                if (software[i] == 1108){
                    if (software[i+1] == software[i+2]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;
                } else if (software[i] == 1008){
                    if (software[software[i+1]] == software[i+2]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;

                } else if (software[i] == 108) {
                    if (software[i+1] == software[software[i+2]]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;
                } else {
                    if (software[software[i+1]] == software[software[i+2]]) software[software[i+3]] = 1;
                    else software[software[i+3]] = 0;
                }
                i += 2;
            }
        }
        return output;
    }

}

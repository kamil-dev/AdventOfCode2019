package com.kamil.adventOfCode.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utilities {


    public static List<Integer> loadIntegersAsList(String inputPath){

        List<Integer> values = new ArrayList<>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(inputPath));

            String line;
            while ((line = reader.readLine()) != null) {
                values.add(Integer.parseInt(line));
            }
        } catch (IOException e1) {
            System.err.println("Input Output exception: " + e1);
        }
        return values;
    }

    public static String loadInputAsString(String inputPath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            System.err.println("Input Output exception " + e);
        }
        return sb.toString();
    }

    public static List<String> loadInputAsListOfStrings(String inputPath){
        List<String> listOfStrings = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            String line;
            while ((line = reader.readLine()) != null) {
                listOfStrings.add(line);
            }

        } catch (IOException e) {
            System.err.println("Input Output exception " + e);
        }
        return listOfStrings;
    }
}

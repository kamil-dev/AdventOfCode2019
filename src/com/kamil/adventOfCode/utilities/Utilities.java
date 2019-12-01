package com.kamil.adventOfCode.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utilities {


    public static List<Integer> loadIntegers(String inputPath){

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
}

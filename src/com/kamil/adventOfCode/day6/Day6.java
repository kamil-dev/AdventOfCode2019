package com.kamil.adventOfCode.day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6 {
    private final static String PATH = "src/com/kamil/adventOfCode/day6/InputDay6.txt";
    private final static String ROOT = "COM";

    public static int solveProblemA( String path) {
        List<String> orbits = readData(path);
        Map<String, Node> tree = buildTree(orbits);
        int numOfAllOrbits = 0;
        for (Node n : tree.values()){
            numOfAllOrbits += n.getLevel();
        }
        return numOfAllOrbits;
    }

    public static int solveProblemA(){
        return solveProblemA(PATH);
    }

    private static List<String> readData(String path){
        List<String> data = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while((line = bufferedReader.readLine()) != null) data.add(line);
        } catch (IOException e) {
            System.err.println(e);
        }
        return data;
    }

    private static Map<String, Node> buildTree(List<String> orbits){
        Map<String, Node> tree = new HashMap<>();
        String parent;
        String child;
        for (String s : orbits){
            parent = s.substring(0,s.indexOf(')'));
            child = s.substring(s.indexOf(')') + 1);
            if (!tree.containsKey(parent)) tree.put(parent, new Node(parent));
            if (!tree.containsKey(child)) {
                tree.put(child, new Node(child, parent));
            } else tree.get(child).setParent(parent);
        }
        calculateNodeLevels(tree);
        return tree;
    }

    private static void  calculateNodeLevels(Map<String, Node> tree){
        int level;
        for (String currentNode : tree.keySet()){
            level = establishNodeLevel(currentNode, tree);
            tree.get(currentNode).setLevel(level);
            tree.get(currentNode).setLevelSet(true);
        }
    }

    private static int establishNodeLevel(String currentNode, Map<String, Node> tree){
        if (currentNode.equals(ROOT)) return 0;
        if (tree.get(currentNode).isLevelSet) return tree.get(currentNode).level;
        String parentNode = tree.get(currentNode).getParent();
        return 1 + establishNodeLevel(parentNode, tree);
    }




    private static class Node {
        String object;
        String parent;
        int level;
        boolean isLevelSet;

        public Node(String object) {
            this.object = object;
            isLevelSet = false;
        }

        public Node(String object, String parent ) {
            this.object = object;
            this.parent = parent;
            isLevelSet = false;
        }


        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public boolean isLevelSet() {
            return isLevelSet;
        }

        public void setLevelSet(boolean levelSet) {
            isLevelSet = levelSet;
        }

    }
}

package me.cizezsy.chapter3.symboltables;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

public class FrequencyCounter {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length > 1)
            System.setIn(new FileInputStream(args[1]));

        int minLen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new BinarySearchST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }

        Queue<String> maxQueue = new LinkedList<>();
        String max = "";
        st.put(max, 0);
        for (String s : st.keys()) {
            if (st.get(s) > st.get(max)) {
                max = s;
                maxQueue.clear();
                maxQueue.add(max);
            } else if(st.get(s).equals(st.get(max))) {
                maxQueue.add(s);
            }
        }

        for (String s : maxQueue) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}

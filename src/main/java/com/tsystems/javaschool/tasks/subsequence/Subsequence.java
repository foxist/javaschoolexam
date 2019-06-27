package com.tsystems.javaschool.tasks.subsequence;

import java.util.*;

//TODO
// Checks if it is possible to get a sequence which is equal to the first
// one by removing some elements from the second one.
public class Subsequence {

    /**
     * find - compares two sequences
     * Params: List x - first sequence, List y - second sequence
     * Return: return true if the first sequence is a subsequence of the second sequence, else false
     */
    public boolean find(List x, List y) throws IllegalArgumentException {
        if (x == null || y == null)
            throw new IllegalArgumentException();
        if (x.size() > y.size())
            return false;
        boolean[] values = new boolean[y.size()];
        for (Object ox : x) {
            int countVal = 0;
            for (int i = 0; i < y.size(); i++) {
                if (ox.equals(y.get(i)) && countVal == 0) {
                    values[i] = true;
                    countVal++;
                }
            }
        }

        List<Object> z = new ArrayList<>();
        for (int i = 0; i < values.length; i++)
            if (values[i])
                z.add(y.get(i));
        return x.equals(z);
    }
}

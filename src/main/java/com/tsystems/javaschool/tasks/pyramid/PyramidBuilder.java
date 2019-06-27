package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;

//TODO
// Create a class that builds pyramids from the numbers contained in the List

/**
 * class PyramidBuilder build the pyramid
 * class has private fields row, column and array pyramid
 */
public class PyramidBuilder {
    private int row = 0, column = 0;
    private int[][] pyramid;

    /**
     * isTriangularNumber - is the length of the List a triangular number
     * Params: int n - length of List
     * Return: return true if length a triangular number, else false
     */
    private boolean isTriangularNumber(int n) {
        double ans = (-1.0 + Math.sqrt(1.0 + 4.0 * n * 2)) / 2;
        if (ans - (int) ans == 0) {
            column = (int) ans + (int) ans - 1;
            row = (int) ans;
            return true;
        }
        return false;
    }

    /**
     * isNull - checks if the array is null
     * Params: List<Integer> integerList - list of values
     * Return: return true if array has a null, else false
     */
    private boolean isNull(List<Integer> integerList) {
        for (Integer integer : integerList)
            if (integer == null)
                return true;
        return false;
    }

    /**
     * buildPyramid - fills the array with values taken from the List
     * Params: List<Integer> integerList - List of values
     * Exception: if list of values has a null or his size isn't triangular throws an exception
     * Return: return built pyramid
     */
    public int[][] buildPyramid(List<Integer> integerList) throws CannotBuildPyramidException {
        if (!isTriangularNumber(integerList.size()) || isNull(integerList))
            throw new CannotBuildPyramidException("Error");
        else {
            Object[] a = integerList.toArray();
            Arrays.sort(a);
            int k = 0;
            pyramid = new int[row][column];
            for (int i = 0; i < row; i++) {
                int pos = row - i - 1;
                for (int j = 0; j < column; j++) {
                    if (j >= pos && j <= column - row + i) {
                        pyramid[i][j] = (int) a[k++];
                        pos += 2;
                    }
                }
            }
            return pyramid;
        }
    }
}

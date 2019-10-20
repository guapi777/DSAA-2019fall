package com.company;


import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        int[] ints = {5, 4, 23, 231, 434, 3, 0, 0};
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}

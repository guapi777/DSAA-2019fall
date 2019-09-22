package com.company.Lab2;

import java.util.Random;
import java.util.Scanner;

public class problemA {
    private static void QuickSort(int[] arr, int left, int right) {
        if (left <= right) {
            Random random = new Random();
            int random_index = random.nextInt(right - left + 1) + left;
            int temp = arr[random_index];
            arr[random_index] = arr[left];
            arr[left] = temp;
            int base = arr[left];
            int i = left;
            int j = right;
            while (i != j) {
                while (arr[j] >= base && i < j) {
                    j--;
                }
                while (arr[i] <= base && i < j) {
                    i++;
                }
                int temp2 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp2;
            }
            arr[left] = arr[i];
            arr[i] = base;
            QuickSort(arr, left, i - 1);
            QuickSort(arr, j + 1, right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        for (int i = 0; i < time; i++) {
            int length = scanner.nextInt();
            int[] ints = new int[length];
            for (int j = 0; j < length; j++) {
                ints[j] = scanner.nextInt();
            }
            QuickSort(ints, 0, ints.length - 1);
            if (ints.length >= 4) {
                if (ints[ints.length - 3] == ints[ints.length - 2] || ints[ints.length - 3] == ints[ints.length - 4]) {
                    System.out.println("wa");
                } else System.out.println(ints[ints.length - 3]);
            } else {
                if (ints[ints.length - 3] == ints[ints.length - 2]) {
                    System.out.println("wa");
                } else System.out.println(ints[ints.length - 3]);
            }


        }
    }
}

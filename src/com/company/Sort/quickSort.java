package com.company.Sort;

import java.util.Random;

public class quickSort {


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

        int[] arr = {3, 5, 2, 4, 5, 67, 23, 55, 42, 45, 11, 3, 34};
        QuickSort(arr, 0, arr.length - 1);
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

}

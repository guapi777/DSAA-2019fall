package com.company.Lab2;

import java.util.Random;
import java.util.Scanner;

public class problemC {
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

    private static int halfSearch(int[] arr, int key, int start) {
        int min = start, max = arr.length - 1, mid;

        while (min <= max) {
            mid = (min + max) >> 1;
            if (key > arr[mid])
                min = mid + 1;
            else if (key < arr[mid])
                max = mid - 1;
            else
                return arr[mid];
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ints = new int[scanner.nextInt()];
        int m = scanner.nextInt();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = scanner.nextInt();
        }
        long count = 0;
        QuickSort(ints, 0, ints.length - 1);
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length - 1; j++) {
                int tag = m - ints[i] - ints[j];
                int result = halfSearch(ints, tag, j + 1);
                if (result != -1) {
                    for (int k = j + 1; k < ints.length; k++) {
                        if (ints[k] == result) {
                            count++;
                        }
                    }
                }

            }
        }
        System.out.print(count);


    }

}

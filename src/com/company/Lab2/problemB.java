package com.company.Lab2;

import java.util.Random;
import java.util.Scanner;

public class problemB {
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
        int length = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = scanner.nextInt();
        }
        QuickSort(arr, 0, length - 1);

        System.out.print(arr[k - 1]);

    }
}

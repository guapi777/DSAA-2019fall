package com.company.ACM;

import java.util.Scanner;

public class A {
    private int getMin(char[] input, int i3) {
        char c = input[i3];
        int i2 = i3 + 1;
        char z = 'z';
        for (int i = i3 + 1; i < input.length; i++) {
            if (input[i] > c && input[i] < z) {
                z = input[i];
                i2 = i;
            }
        }
        return i2;
    }

    private static void merge(char[] arr, int low, int mid, int high) {
        char[] nums = new char[high - low + 1];
        int p = low, q = mid + 1;

        int index = 0;
        while (p <= mid && q <= high) {
            if (arr[p] > arr[q]) {
                nums[index++] = arr[q++];
            } else {
                nums[index++] = arr[p++];
            }
        }

        while (p <= mid) {
            nums[index++] = arr[p++];
        }
        while (q <= high) {
            nums[index++] = arr[q++];
        }
        System.arraycopy(nums, 0, arr, low, high - low + 1);
    }

    private static void MergeSort(char[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            MergeSort(arr, low, mid);
            MergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }

    }


    private void reverse(char[] aVoid, int aVoid1, int i) {
        while (aVoid1 < i) {
            char temp = aVoid[aVoid1];
            aVoid[aVoid1] = aVoid[i];
            aVoid[i] = temp;
            aVoid1++;
            i--;
        }
    }

    private void getDictionary(char[] c) {
        System.out.println(new String(c));
        int i;
        while (true) {
            i = c.length - 1;
            for (; i > 0; i--) {
                if (c[i - 1] < c[i]) break;
            }
            if (i == 0) break;
            int minIndex = getMin(c, i - 1);
            char temp = c[i - 1];
            c[i - 1] = c[minIndex];
            c[minIndex] = temp;
            reverse(c, i, c.length - 1);
            System.out.println(new String(c));
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            scanner.nextInt();
            char[] c = scanner.next().toCharArray();
            MergeSort(c, 0, c.length - 1);
            new A().getDictionary(c);
        }
    }
}

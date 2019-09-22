package com.company.Lab2;

import java.util.Scanner;

public class problemD {

    private static long cost = 0;

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] nums = new int[high - low + 1];
        int p = low, q = mid + 1;

        int index = 0;
        while (p <= mid && q <= high) {
            if (arr[p] > arr[q]) {
                cost += (long) (mid - p + 1) * (long) arr[q];
                nums[index++] = arr[q++];
            } else {
                cost += (long) (q - mid - 1) * (long) arr[p];
                nums[index++] = arr[p++];
            }
        }

        while (p <= mid) {
            cost += (long) (q - mid - 1) * (long) arr[p];
            nums[index++] = arr[p++];
        }
        while (q <= high) {
            cost += (long) (mid - p + 1) * (long) arr[q];
            nums[index++] = arr[q++];

        }
        System.arraycopy(nums, 0, arr, low, high - low + 1);
    }

    private static void MergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (high + low) / 2;
            MergeSort(arr, low, mid);
            MergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = scanner.nextInt();
        }
        MergeSort(arr, 0, arr.length - 1);
        System.out.print(cost);
    }

}

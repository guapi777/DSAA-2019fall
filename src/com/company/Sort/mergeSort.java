package com.company.Sort;

public class mergeSort {
    private static void merge(int[] arr, int low, int mid, int high) {
        int[] nums = new int[high - low + 1];
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

    private static void MergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            MergeSort(arr, low, mid);
            MergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }

    }


    public static void main(String[] args) {

        int[] arr = {2, 3, 45, 3, 9, 5, 2, 3, 4, 5, 66};

        MergeSort(arr, 0, arr.length - 1);

        for (int value : arr) {
            System.out.print(value + " ");
        }
    }


}

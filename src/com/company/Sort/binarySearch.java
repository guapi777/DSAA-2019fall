package com.company.Sort;

public class binarySearch {
    public static void main(String[] args) {

        int[] arr = {23, 34, 45, 56, 67, 67, 78, 89, 120};
        int key = 67;

        int index = halfSearch(arr, key);
        System.out.println("index=" + index);
    }

    private static int halfSearch(int[] arr, int key) {
        int min = 0, max = arr.length - 1, mid;

        while (min <= max) {
            mid = (min + max) >> 1;
            if (key > arr[mid])
                min = mid + 1;
            else if (key < arr[mid])
                max = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}

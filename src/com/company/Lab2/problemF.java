package com.company.Lab2;

import java.util.Scanner;

public class problemF {
    private static void merge(int[] arr, int low, int mid, int high, int[] arr2) {
        int[] nums = new int[high - low + 1];
        int[] nums2 = new int[high - low + 1];
        int p = low, q = mid + 1;
        int p2 = low, q2 = mid + 1;
        int index = 0;
        int index2 = 0;
        while (p <= mid && q <= high) {
            if (arr[p] > arr[q]) {
                nums[index++] = arr[q++];
                nums2[index2++] = arr2[q2++];
            } else {
                nums[index++] = arr[p++];
                nums2[index2++] = arr2[p2++];
            }
        }
        while (p <= mid) {
            nums[index++] = arr[p++];
            nums2[index2++] = arr2[p2++];
        }
        while (q <= high) {
            nums[index++] = arr[q++];
            nums2[index2++] = arr2[q2++];
        }
        System.arraycopy(nums, 0, arr, low, high - low + 1);
        System.arraycopy(nums2, 0, arr2, low, high - low + 1);
    }

    private static void MergeSort(int[] arr, int low, int high, int[] arr2) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            MergeSort(arr, low, mid, arr2);
            MergeSort(arr, mid + 1, high, arr2);
            merge(arr, low, mid, high, arr2);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int chuan = scanner.nextInt();
            int[] blue = new int[chuan];
            int[] red = new int[chuan];
            int[] cha = new int[chuan];
            long count = 0;
            int xiaoyu0 = 0;
            int dayuling = 0;
            for (int j = 0; j < chuan; j++) {
                blue[j] = scanner.nextInt();
                red[j] = scanner.nextInt();
                cha[j] = blue[j] - red[j];
            }

            for (int j = 0; j < chuan; j++) {
                if (cha[j] >= 0) {
                    dayuling++;
                } else if (cha[j] < 0) {
                    xiaoyu0++;
                }
            }
            int[] bluexiao = new int[xiaoyu0];
            int[] redxiao = new int[xiaoyu0];
            int[] blueda = new int[dayuling];
            int[] redda = new int[dayuling];
            int xiao = 0;
            int da = 0;
            for (int j = 0; j < chuan; j++) {
                if (cha[j] < 0) {
                    bluexiao[xiao] = blue[j];
                    redxiao[xiao] = red[j];
                    xiao++;
                } else if (cha[j] >= 0) {
                    blueda[da] = blue[j];
                    redda[da] = red[j];
                    da++;
                }
            }
            MergeSort(bluexiao, 0, xiaoyu0 - 1, redxiao);
            MergeSort(redda, 0, dayuling - 1, blueda);
            int[] blueda2 = new int[dayuling];
            int[] redda2 = new int[dayuling];
            for (int j = 0; j < dayuling; j++) {
                blueda2[j] = blueda[dayuling - j - 1];
                redda2[j] = redda[dayuling - j - 1];
            }

            if (redxiao.length > 0 && redda2.length > 0) {
                int R = redxiao[0];
                for (int j = 1; j < redxiao.length; j++) {
                    if (R > bluexiao[j]) {
                        R = redxiao[j] + R - bluexiao[j];
                        count += bluexiao[j];
                    } else {
                        count += R;
                        R = redxiao[j];
                    }
                }
                if (R > blueda2[0]) {
                    count += blueda2[0];
                    R = redda2[0] + R - blueda2[0];
                } else {
                    count += R;
                    R = redda2[0];
                }
                for (int j = 1; j < redda2.length; j++) {
                    if (R > blueda2[j]) {
                        count += blueda2[j];
                        R = redda2[j] + R - blueda2[j];
                    } else if (R <= blueda2[j]) {
                        count += R;
                        R = redda2[j];
                    }
                }

            } else if (redxiao.length > 0) {
                int R = redxiao[0];
                for (int j = 1; j < redxiao.length; j++) {
                    if (R > bluexiao[j]) {
                        R = redxiao[j] + R - bluexiao[j];
                        count += bluexiao[j];
                    } else if (R <= bluexiao[j]) {
                        count += R;
                        R = redxiao[j];
                    }
                }
            } else if (redda2.length > 0) {
                int R = redda2[0];
                for (int j = 1; j < redda2.length; j++) {
                    if (R > blueda2[j]) {
                        count += blueda2[j];
                        R = redda2[j] + R - blueda2[j];
                    } else if (R <= blueda2[j]) {
                        count += R;
                        R = redda2[j];
                    }
                }
            }
            System.out.println(count);
        }
    }
}

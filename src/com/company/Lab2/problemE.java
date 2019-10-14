package com.company.Lab2;

import java.util.Scanner;

public class problemE {
    private static void merge(long[] arr, int low, int mid, int high, long[] HP, int[] gongji) {
        long[] nums = new long[high - low + 1];
        long[] nums2 = new long[high - low + 1];
        int[] nums3 = new int[high - low + 1];
        int p = low, q = mid + 1;
        int p2 = low, q2 = mid + 1;
        int p3 = low, q3 = mid + 1;
        int index = 0;
        int index2 = 0;
        int index3 = 0;
        while (p <= mid && q <= high) {
            if (arr[p] > arr[q]) {
                nums[index++] = arr[q++];
                nums2[index2++] = HP[q2++];
                nums3[index3++] = gongji[q3++];
            } else {
                nums[index++] = arr[p++];
                nums2[index2++] = HP[p2++];
                nums3[index3++] = gongji[p3++];
            }
        }
        while (p <= mid) {
            nums[index++] = arr[p++];
            nums2[index2++] = HP[p2++];
            nums3[index3++] = gongji[p3++];
        }
        while (q <= high) {
            nums[index++] = arr[q++];
            nums2[index2++] = HP[q2++];
            nums3[index3++] = gongji[q3++];
        }
        System.arraycopy(nums, 0, arr, low, high - low + 1);
        System.arraycopy(nums2, 0, HP, low, high - low + 1);
        System.arraycopy(nums3, 0, gongji, low, high - low + 1);
    }

    private static void MergeSort(long[] arr, int low, int high, long[] HP, int[] gongji) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            MergeSort(arr, low, mid, HP, gongji);
            MergeSort(arr, mid + 1, high, HP, gongji);
            merge(arr, low, mid, high, HP, gongji);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int xuefanbei = scanner.nextInt();
        int gongdengshen = scanner.nextInt();
        long[] HP = new long[n];
        int[] gongji = new int[n];
        long[] shouyi = new long[n];
        for (int i = 0; i < n; i++) {
            HP[i] = scanner.nextLong();
            gongji[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            shouyi[i] = HP[i] - gongji[i];
        }


        int zhengshouyi = 0;
        for (int i = 0; i < n; i++) {
            if (shouyi[i] > 0) {
                zhengshouyi++;
            }
        }
        int shifanggongdengshen = Math.min(zhengshouyi, gongdengshen);
        MergeSort(shouyi, 0, shouyi.length - 1, HP, gongji);
        long xuefanbeizuida = 0;
        int xuefanbeimubiao = 0;
        for (int i = 0; i < n; i++) {
            if (i < n - shifanggongdengshen) {
                if (shifanggongdengshen == gongdengshen) {
                    if (shifanggongdengshen == 0) {
                        break;
                    }
                    if (HP[i] * (long) Math.pow(2, xuefanbei) - gongji[i] - shouyi[n - shifanggongdengshen] > xuefanbeizuida) {
                        xuefanbeizuida = HP[i] * (long) Math.pow(2, xuefanbei) - gongji[i] - shouyi[n - shifanggongdengshen];
                        xuefanbeimubiao = i;
                    }
                } else {
                    if (HP[i] * (long) Math.pow(2, xuefanbei) - gongji[i] > xuefanbeizuida) {
                        xuefanbeizuida = HP[i] * (long) Math.pow(2, xuefanbei) - gongji[i];
                        xuefanbeimubiao = i;
                    }
                }
            } else {
                if (HP[i] * (long) Math.pow(2, xuefanbei) - HP[i] > xuefanbeizuida) {
                    xuefanbeizuida = HP[i] * (long) Math.pow(2, xuefanbei) - HP[i];
                    xuefanbeimubiao = i;
                }
            }
        }
        long count = 0;
        for (int value : gongji) {
            count += value;
        }
        if (xuefanbeizuida <= 0 && zhengshouyi == 0 || gongdengshen == 0) {
            System.out.print(count);
        } else if (xuefanbeizuida <= 0 && zhengshouyi > 0 || xuefanbei == 0) {
            for (int i = shouyi.length - shifanggongdengshen; i < shouyi.length; i++) {
                count += shouyi[i];
            }
            System.out.print(count);
        } else if (xuefanbeimubiao >= shouyi.length - shifanggongdengshen) {
            for (int i = shouyi.length - shifanggongdengshen; i < shouyi.length; i++) {
                count += shouyi[i];
            }
            count -= shouyi[xuefanbeimubiao];
            count += HP[xuefanbeimubiao] * (long) Math.pow(2, xuefanbei) - gongji[xuefanbeimubiao];
            System.out.print(count);
        } else if (xuefanbeimubiao < shouyi.length - shifanggongdengshen && shifanggongdengshen == gongdengshen) {
            for (int i = shouyi.length - shifanggongdengshen; i < shouyi.length; i++) {
                count += shouyi[i];
            }
            count -= shouyi[gongji.length - shifanggongdengshen];
            count += HP[xuefanbeimubiao] * (long) Math.pow(2, xuefanbei) - gongji[xuefanbeimubiao];
            System.out.print(count);
        } else if (xuefanbeimubiao < shouyi.length - shifanggongdengshen && shifanggongdengshen < gongdengshen) {
            for (int i = shouyi.length - shifanggongdengshen; i < shouyi.length; i++) {
                count += shouyi[i];
            }
            count += HP[xuefanbeimubiao] * (long) Math.pow(2, xuefanbei) - gongji[xuefanbeimubiao];
            System.out.print(count);
        }
    }
}



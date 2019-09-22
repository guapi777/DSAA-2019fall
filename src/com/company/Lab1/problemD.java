package com.company.Lab1;

import java.util.Scanner;

public class problemD {

    private static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;
        return obj - Math.floor(obj) < eps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int quchongLength = length;
        long target = scanner.nextLong();
        int count = 0;
        int count2 = 0;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = scanner.nextInt();
        }
        if (isIntegerForDouble(Math.pow((double) target, 0.5))) {
            int root = (int) Math.pow((double) target, 0.5);
            if (root != 0) {
                int fugenroot = 0;
                int zhenggenroot = 0;
                for (int i = 0; i < length; i++) {
                    if (ints[i] == root) {
                        zhenggenroot++;
                    } else if (ints[i] == root * (-1)) {
                        fugenroot++;
                    }

                }
                if (fugenroot >= 2) {
                    count++;
                }
                if (zhenggenroot >= 2) {
                    count++;
                }
            } else {
                int lingroot = 0;
                for (int i = 0; i < length; i++) {
                    if (ints[i] == 0) {
                        lingroot++;
                    }
                }
                if (lingroot >= 2) {
                    count++;
                }
            }


        }
        for (int i = 1; i < length; i++) {
            if (ints[i - 1] == ints[i]) {
                quchongLength--;
            }
        }
        int[] quchongints = new int[quchongLength];
        quchongints[0] = ints[0];
        int jiji = 1;
        for (int i = 1; i < length; i++) {
            if (ints[i] != ints[i - 1]) {
                quchongints[jiji] = ints[i];
                jiji++;
            }
        }
        length = quchongLength;
        if (target == 0) {
            System.out.print(count + length - 1);
        } else {
            for (int i = 0; i < length; i++) {
                int left = 0;
                int right = length - 1;
                int chengshu = quchongints[i];
                boolean fushu = false;
                int mid;
                if (chengshu < 0) {
                    fushu = true;
                }
                while (left <= right) {
                    mid = (left + right) / 2;
                    if (((long) chengshu * (long) quchongints[mid]) == target && mid != i) {
                        count2++;
                        break;
                    } else if (((long) chengshu * (long) quchongints[mid]) > target) {
                        if (!fushu) {
                            right = mid - 1;
                        } else left = mid + 1;
                    } else if (!fushu) {
                        left = mid + 1;
                    } else right = mid - 1;
                }
            }
            System.out.print(count + count2 / 2);
        }

    }
}


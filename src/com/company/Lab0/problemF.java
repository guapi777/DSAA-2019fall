package com.company.Lab0;

import java.util.Scanner;

public class problemF {
    private static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;
        return obj - Math.floor(obj) < eps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        for (int i = 0; i < time; i++) {
            long sum = scanner.nextLong();
            long zuiduohang = (long) Math.floor(Math.pow(1 + 8 * sum, 0.5) / 2 - 0.5);
            for (int j = 2; j <= zuiduohang; j++) {
                double k = (double) (sum - j * (j - 1) / 2) / j;
                if (isIntegerForDouble(k)) {
                    System.out.print(j);
                    break;
                }
                if (j == zuiduohang) {
                    System.out.print("impossible");
                }
            }
            if (zuiduohang == 1) {
                System.out.print("impossible");

            }
            if (i != time - 1) {
                System.out.println();
            }
        }
    }

}

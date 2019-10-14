package com.company.Lab3;

import java.util.Scanner;

public class problemE {
    private static int getValue(int max, int power) {
        String string = (long) max * (long) max + "";
        int weishu = string.length() - power;
        if (weishu >= 0) {
            return (int) ((long) max * (long) max / Math.pow(10, weishu));
        } else return max * max;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int power = scanner.nextInt();
            int max = scanner.nextInt();
            int shortPoint = getValue(max, power);

            max = Math.max(shortPoint, max);
            int quickPoint = getValue(shortPoint, power);
            max = Math.max(quickPoint, max);

            while (shortPoint != quickPoint) {
                shortPoint = getValue(shortPoint, power);
                quickPoint = getValue(quickPoint, power);
                max = Math.max(quickPoint, max);
                quickPoint = getValue(quickPoint, power);
                max = Math.max(quickPoint, max);
            }
            System.out.println(max);
        }
    }
}






package com.company.ACM;

import java.util.Scanner;

public class F {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {


            long a = scanner.nextLong();
            long b = scanner.nextLong();
            long c = scanner.nextLong();
            long end = scanner.nextLong();
            long MO = 2147483231;
            long a1 = a;
            long b1 = b;
            long c1 = c;

            int countA = 0;
            int countB = 0;
            int countC = 0;
            while (a != end) {
                countA++;
                a = a * a % MO;
            }
            while (b != end) {
                countB++;
                b = b * b % MO;
            }
            while (c != end) {
                countC++;
                c = c * c % MO;
            }

            int minCount = Math.min(countA, Math.min(countB, countC));

            for (int j = 0; j < countA - minCount; j++) {
                a1 = a1 * a1 % MO;
            }

            for (int j = 0; j < countB - minCount; j++) {
                b1 = b1 * b1 % MO;
            }
            for (int j = 0; j < countC - minCount; j++) {
                c1 = c1 * c1 % MO;
            }

            while (a1 != c1 || b1 != c1) {
                a1 = a1 * a1 % MO;
                b1 = b1 * b1 % MO;
                c1 = c1 * c1 % MO;
            }
            System.out.println(a1);
        }
    }
}

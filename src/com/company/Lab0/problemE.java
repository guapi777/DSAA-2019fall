package com.company.Lab0;

import java.util.Scanner;

public class problemE {


    private static int[] number(long longNumber, int[] ints) {
        double a = Math.log(longNumber) / Math.log(4);
        long min = (long) Math.pow(4, Math.ceil(a) - 1);
        long length = (long) Math.pow(min, (double) 1 / 2);
        if (longNumber == 1) {
            ints[0] += 1;
            ints[1] += 1;
            return ints;
        } else if (longNumber == 2) {
            ints[0] += 2;
            ints[1] += 1;
            return ints;
        } else if (longNumber == 3) {
            ints[0] += 2;
            ints[1] += 2;
            return ints;
        } else if (longNumber == 4) {
            ints[0] += 1;
            ints[1] += 2;
            return ints;
        } else if (min < longNumber && longNumber <= min * 2) {
            ints[0] += length;
            longNumber = longNumber - min;
            return number(longNumber, ints);
        } else if (longNumber > min * 2 && longNumber <= min * 3) {
            ints[0] += length;
            ints[1] += length;
            longNumber -= 2 * min;
            return number(longNumber, ints);
        } else
            ints[1] += length;
        longNumber = longNumber - 3 * min;
        return number(longNumber, ints);
    }

    private static long longNumber(int a, int b, long yigelong) {
        if (a == 1 && b == 1) {
            return yigelong + 1;
        } else if (a == 2 && b == 1) {
            return yigelong + 2;
        } else if (a == 2 && b == 2) {
            return yigelong + 3;
        } else if (a == 1 && b == 2) {
            return yigelong + 4;
        } else {
            long bianchang = (long) Math.pow(2, Math.ceil(Math.log(Math.max(a, b)) / Math.log(2)) - 1);
            if (a > bianchang && b <= bianchang) {
                yigelong += bianchang * bianchang;
                a -= bianchang;
                return longNumber(a, b, yigelong);
            } else if (a > bianchang && b > bianchang) {
                yigelong += 2 * bianchang * bianchang;
                a -= bianchang;
                b -= bianchang;
                return longNumber(a, b, yigelong);
            } else {
                yigelong += 3 * bianchang * bianchang;
                b -= bianchang;
                return longNumber(a, b, yigelong);
            }

        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < time; i++) {
            String string = scanner.nextLine();
            if (string.substring(0, 1).equals("(")) {
                int a = Integer.parseInt(string.substring(1, string.indexOf(",")));
                int b = Integer.parseInt(string.substring(string.indexOf(",") + 1, string.indexOf(")")));
                System.out.print(longNumber(a, b, 0));
            } else {
                long longNumber = Long.parseLong(string);
                int[] ints = new int[2];
                number(longNumber, ints);
                System.out.print("(" + ints[0] + "," + ints[1] + ")");
            }
            if (i != time - 1) {
                System.out.println();
            }
        }
    }
}

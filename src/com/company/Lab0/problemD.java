package com.company.Lab0;

import java.util.Scanner;


public class problemD {
    private static int countN(Long limit) {
        int n = limit.toString().length();
        int count = 0;
        if (n % 2 == 1) {
            Long magicNumber = (long) Math.pow(10, n - 1) + 1;
            while (magicNumber <= limit) {
                int chengshi = (n - 1) / 2;
                boolean you9 = false;
                for (int i = 0; i < (n - 1) / 2; i++) {
                    if (magicNumber.toString().substring((n - 1) / 2 + i, (n - 1) / 2 + 1 + i).equals("9")) {
                        you9 = true;
                        chengshi--;
                    } else break;
                }
                if (you9) {
                    magicNumber += 11 * (long) Math.pow(10, chengshi);
                } else magicNumber += (long) Math.pow(10, (double) (n - 1) / 2);
                count++;
            }

        } else {
            Long magicNumber = (long) Math.pow(10, n - 1) + 1;
            while (magicNumber <= limit) {
                int chengshi = n / 2 - 1;
                boolean you9 = false;
                for (int i = 0; i < n / 2; i++) {
                    if (magicNumber.toString().substring(n / 2 + i, n / 2 + 1 + i).equals("9")) {
                        you9 = true;
                        chengshi--;
                    } else break;
                }
                if (you9) {
                    magicNumber += 11 * (long) Math.pow(10, chengshi);
                } else magicNumber += 11 * (long) Math.pow(10, (double) n / 2 - 1);
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < time; i++) {
            long limit = scanner.nextLong();
            int length = Long.toString(limit).length();
            int count = 0;
            for (int j = 1; j < length; j++) {
                if (j == 1) {
                    count += 10;
                } else if (j == 2) {
                    count += 9;
                } else {
                    if (j % 2 == 1) {
                        count += 9 * Math.pow(10, (double) (j - 1) / 2);
                    } else {
                        count += 9 * Math.pow(10, (double) j / 2 - 1);
                    }
                }
            }
            count += countN(limit);
            if (length != 1) {
                System.out.print(count);
            } else System.out.print(limit + 1);
            if (i != time - 1) {
                System.out.println();
            }
        }
    }
}
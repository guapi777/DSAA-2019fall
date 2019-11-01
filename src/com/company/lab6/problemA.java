package com.company.lab6;

import java.util.Scanner;

public class problemA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int j = 0; j < times; j++) {
            int total = scanner.nextInt();
            int pow = scanner.nextInt();
            int n = (int) Math.ceil(Math.log(total) / Math.log(pow));
            int a;
            if ((total - ((int) Math.pow(pow, n - 1) - 1) / (pow - 1)) % pow == 0) {
                a = (total - ((int) Math.pow(pow, n - 1) - 1) / (pow - 1)) / pow;
            } else {
                a = (total - ((int) Math.pow(pow, n - 1) - 1) / (pow - 1)) / pow + 1;
            }
            System.out.println(total - ((int) Math.pow(pow, n - 1) - 1) / (pow - 1) + (int) Math.pow(pow, n - 2) - a);
        }
    }
}

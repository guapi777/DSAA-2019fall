package com.company.Lab1;

import java.util.Scanner;

public class problemA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        for (int i = 0; i < time; i++) {
            long day = scanner.nextLong();
            long sum = (day * (day + 1) * (2 * day + 1) / 6 + (day + 1) * day / 2) / 2;
            System.out.println(sum);
        }
    }
}

package com.company.Lab5;

import java.util.Scanner;

public class problemA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < times; i++) {
            String string = scanner.nextLine();
            int a = string.length();
            System.out.println((a * (a + 1)) / 2);
        }
    }
}

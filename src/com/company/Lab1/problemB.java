package com.company.Lab1;

import java.util.Scanner;

public class problemB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int days = scanner.nextInt();
        int kinds = scanner.nextInt();
        int[] money = new int[days];
        int[] fishes = new int[kinds];
        for (int i = 0; i < days; i++) {
            money[i] = scanner.nextInt();
        }
        for (int i = 0; i < kinds; i++) {
            fishes[i] = scanner.nextInt();
        }
        for (int i = 0; i < days; i++) {
            int left = 0;
            int right = kinds - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (money[i] == fishes[mid]) {
                    System.out.println("Meow");
                    break;
                } else if (money[i] < fishes[mid]) {
                    if (mid - 1 >= 0) {
                        if (money[i] > fishes[mid - 1]) {
                            System.out.println(money[i] - fishes[mid - 1]);
                            break;
                        } else right = mid;
                    } else {
                        System.out.println(money[i]);
                        break;
                    }
                } else if (money[i] > fishes[mid]) {
                    if (mid == kinds - 1) {
                        System.out.println(money[i] - fishes[mid]);
                        break;
                    }
                    if ((right - left + 1) % 2 == 0) {
                        left = mid + 1;
                    } else left = mid;
                }
            }
        }


    }
}
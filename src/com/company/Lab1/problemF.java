package com.company.Lab1;

import java.util.Scanner;

public class problemF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        for (int i = 0; i < time; i++) {
            long shuzi = scanner.nextLong();
            long count = 0;
            long temp = shuzi / 5;
            while (temp != 0) {
                count += temp;
                temp /= 5;
            }
            System.out.println(count);
        }
    }
}


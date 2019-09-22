package com.company.Lab1;

import java.util.Scanner;

public class problemC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        if (n == 1 || n == 0) {
            System.out.print(1 % m);
        } else if (n == 2) {
            System.out.print(2 % m);
        } else if (n == 3) {
            if (m <= 720) {
                System.out.println(0);
            } else {
                long mo = 1;
                for (int i = 2; i <= 720; i++) {
                    mo = ((mo % m) * (i % m)) % m;
                    if (mo == 0) {
                        break;
                    }
                }
                System.out.print(mo);
            }
        } else System.out.print(0);
    }
}

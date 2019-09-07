package com.company.Lab0;

import java.util.Scanner;

public class problemA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        for (int i = 0; i < time; i++) {
            scanner.nextLine();
            scanner.nextLine();
            String secondLine = scanner.nextLine();
            scanner.nextLine();
            System.out.println(secondLine.substring(2, 3));

        }


    }
}

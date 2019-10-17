package com.company.Lab5;

import java.util.Scanner;

public class problemB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < times; i++) {
            scanner.nextLine();
            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            if (!s1.contains("*")) {
                if (s1.equals(s2)) {
                    System.out.println("YES");
                } else System.out.println("NO");
                continue;
            }

            if (s2.length() < s1.length() - 1) {
                System.out.println("NO");
                continue;
            }

            int tag = 0;
            for (int j = 0; j < s1.length(); j++) {
                if (s1.substring(j, j + 1).equals("*")) {
                    tag = j;
                    break;
                }
            }
            int mowei = s1.length() - tag;

            if (s1.substring(0, tag).equals(s2.substring(0, tag))
                    && s1.substring(tag + 1).equals(s2.substring(s2.length() - mowei + 1))) {
                System.out.println("YES");
            } else System.out.println("NO");
        }
    }
}

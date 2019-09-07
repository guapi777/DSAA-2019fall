package com.company.Lab0;

import java.util.Scanner;

public class problemC {
    private static int sort(String s) {
        switch (s) {
            case "E":
                return 7;
            case "S":
                return 6;
            case "W":
                return 5;
            case "N":
            case "T":
                return 4;
            case "B":
            case "Y":
                return 3;
            case "F":
                return 2;
            default:
                return 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < time; i++) {
            String[] split = scanner.nextLine().split("\\s+");
            int[] ints = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                if (split[j].length() == 1) {
                    ints[j] = sort(split[j]);
                } else ints[j] = sort(split[j].substring(0, 1)) * 10 + 10 - Integer.parseInt(split[j].substring(1, 2));
            }
            for (int j = 0; j < ints.length; j++) {
                String stringTemp = split[j];
                int temp = ints[j];
                int flag = j;
                for (int k = j + 1; k < ints.length; k++) {
                    if (ints[k] > temp) {
                        stringTemp = split[k];
                        temp = ints[k];
                        flag = k;
                    }
                }
                if (flag != j) {
                    ints[flag] = ints[j];
                    ints[j] = temp;
                    split[flag] = split[j];
                    split[j] = stringTemp;
                }
            }
            for (String s : split) {
                System.out.print(s + " ");
            }
            if (i != time - 1) {
                System.out.println();
            }
        }
    }
}

package com.company.ACM;

import java.util.ArrayList;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<char[]> arrayList = new ArrayList<>();
        for (int i = 1; i <= n - m + 1; i++) {
            arrayList.add(scanner.next().toCharArray());
        }
        for (int i = 0; i < n; i++) {
            int[] ints = new int[256];
            for (int j = 0; j < n - m + 1; j++) {
                ints[arrayList.get(j)[i]]++;
                if (ints[arrayList.get(j)[i]] >= m + 1) {
                    System.out.print(arrayList.get(j)[i]);
                    break;
                }
            }
        }
    }
}

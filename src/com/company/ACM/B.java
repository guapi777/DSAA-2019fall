package com.company.ACM;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long m = scanner.nextLong();
        long k = scanner.nextLong();
        long n = scanner.nextLong();
        long[] sigema = new long[(int) n];
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            long tmp = 0;
            for (int j = 1; j <= Math.sqrt(n); j++) {
                if (i % j == 0) {
                    int b = i / j;
                    if (j != b) {
                        tmp += Math.pow(j, k);
                        tmp += Math.pow(b, k);
                    } else tmp += Math.pow(j, k);
                }
            }
            sigema[i - 1] = tmp;
        }

        if (m == 1) {
            for (int i = 1; i <= n; i++) {
                ans = ans + sigema[i - 1];
                ans = ans % 1000000007;
            }
            System.out.println(ans % 1000000007);
        } else if (m == 2) {
            for (int i = 1; i <= n; i++) {
                ans = ans + sigema[i - 1] * (n - i + 1);
                ans = ans % 1000000007;
            }
            System.out.println(ans % 1000000007);
        } else if (m == 3) {
            for (int i = 1; i <= n; i++) {
                ans = ans + sigema[i - 1] * (n - i + 1) * (n - i + 2) / 2;
                ans = ans % 1000000007;
            }
            System.out.println(ans % 1000000007);
        } else if (m == 4) {
            for (int i = 1; i <= n; i++) {
                ans = ans + sigema[i - 1] * ((n - i + 1) * (n - i + 2) * (n - i + 3) / 6);
                ans = ans % 1000000007;
            }
            System.out.println(ans % 1000000007);
        }
    }
}

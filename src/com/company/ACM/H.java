package com.company.ACM;

import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long i = scanner.nextLong();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[] days = new int[m];
        int[] price = new int[m];
        for (int j = 0; j < m; j++) {
            int tmp = scanner.nextInt();
            price[j] = tmp;
            int day = 0;
            if (k != 0) {
                day = (tmp - 1) / k;
            }
            days[j] = day;
        }
        boolean isMorning = true;
        long min = 0;
        long max = Long.MAX_VALUE;
        while (min <= max) {
            long mid = (min - max) / 2 + max;
            long cost = 0;
            for (int j = 0; j < m; j++) {
                if (mid > days[j]) {
                    cost = cost + price[j] - days[j] * k;
                } else
                    cost = cost + price[j] - mid * k;
            }

            long money = mid * i + n;
            isMorning = money - i >= cost;
            if (money >= cost)
                max = mid - 1;
            else min = mid + 1;

        }
        if (min == 0)
            min = min + 1;
        if (isMorning)
            System.out.print(min + " morning");
        else System.out.print(min + " evening");
    }
}

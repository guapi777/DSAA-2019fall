package com.company.Lab1;

import java.util.Scanner;

public class problemE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        String strings = scanner.nextLine();
        char[] chars = strings.toCharArray();
        int diejiax = 0;
        int diejiay = 0;
        for (char aChar : chars) {
            if (aChar == 'D') {
                diejiay--;
            } else if (aChar == 'U') {
                diejiay++;
            } else if (aChar == 'R') {
                diejiax++;
            } else if (aChar == 'L') {
                diejiax--;
            }
        }
        long min = 0;
        long max = (long) Math.pow(10, 14);
        while (min <= max) {
            long y3 = y2;
            long x3 = x2;
            long mid = (min + max) / 2;
            long zhengshu = mid / chars.length;
            long yushu = mid % chars.length;
            for (int i = 0; i < yushu; i++) {
                if (chars[i] == 'D') {
                    y3--;
                } else if (chars[i] == 'U') {
                    y3++;
                } else if (chars[i] == 'R') {
                    x3++;
                } else if (chars[i] == 'L') {
                    x3--;
                }
            }
            y3 += diejiay * zhengshu;
            x3 += diejiax * zhengshu;
            boolean midzhua = Math.abs(y3 - y1) + Math.abs(x3 - x1) <= mid;
            if (chars[(int) yushu] == 'D') {
                y3--;
            } else if (chars[(int) yushu] == 'U') {
                y3++;
            } else if (chars[(int) yushu] == 'R') {
                x3++;
            } else x3--;
            boolean midjia1zhua = Math.abs(y3 - y1) + Math.abs(x3 - x1) <= mid + 1;
            if (!midzhua && mid == Math.pow(10, 14)) {
                System.out.print(-1);
                break;
            }
            if (midjia1zhua) {
                if (midzhua) {
                    max = mid - 1;
                } else {
                    System.out.print(mid + 1);
                    break;
                }
            } else {
                if (!midzhua) {
                    min = mid + 1;
                } else break;
            }
        }
    }
}

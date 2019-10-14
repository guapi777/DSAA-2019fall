package com.company.Lab4;

import java.util.Scanner;

public class problemB {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int length = scanner.nextInt();
            if (length % 2 == 1) {
                System.out.println("NO");
                scanner.next();
            } else {
                boolean tag = false;
                char[] chars = scanner.next().toCharArray();
                char[] stack = new char[length];
                int head = 0;
                for (char aChar : chars) {
                    if (aChar == '(' || aChar == '[' || aChar == '{') {
                        stack[head] = aChar;
                        head++;
                    } else if (aChar == ')') {
                        if (head <= 0 || stack[head - 1] != '(') {
                            tag = true;
                            break;
                        } else {
                            head--;
                            stack[head] = '\u0000';
                        }
                    } else if (aChar == ']') {
                        if (head <= 0 || stack[head - 1] != '[') {
                            tag = true;
                            break;
                        } else {
                            head--;
                            stack[head] = '\u0000';
                        }
                    } else {
                        if (head <= 0 || stack[head - 1] != '{') {
                            tag = true;
                            break;
                        } else {
                            head--;
                            stack[head] = '\u0000';
                        }
                    }
                }
                if (head == 0 && !tag) {
                    System.out.println("YES");
                } else
                    System.out.println("NO");
            }
        }
    }
}

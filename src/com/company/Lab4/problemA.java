package com.company.Lab4;

import java.util.Scanner;

public class problemA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] queue = new int[length];
        int font = 0;
        int rear = 0;
        for (int i = 0; i < length; i++) {
            String string =scanner.next();
            switch (string) {
                case "E":
                    int n = scanner.nextInt();
                    if (font == rear) {
                        queue[font] = n;
                    }
                    queue[rear] = n;
                    rear++;
                    break;
                case "D":
                    if (font < rear) {
                        font++;
                    }
                    break;
                case "A":
                    if (font != rear) {
                        System.out.println(queue[font]);
                    }
                    break;
            }
        }
        if (font != rear) {
            for (int i = font; i < rear; i++) {
                System.out.print(queue[i] + " ");
            }
        }
    }
}

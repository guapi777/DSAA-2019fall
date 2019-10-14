package com.company.Lab3;

import java.util.Scanner;

class NodeD {
    Integer position;
    Integer number;
    NodeD next;
    NodeD prev;

    NodeD(Integer position, Integer number) {
        this.position = position;
        this.number = number;
    }
}

public class problemD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int length = scanner.nextInt();
            int start = scanner.nextInt();
            NodeD head = new NodeD(1, scanner.nextInt());
            NodeD tail = head;
            if (length == 1) {
                System.out.println(1);
                i++;
            }
            for (int j = 2; j <= length - 1; j++) {
                NodeD temp = new NodeD(j, scanner.nextInt());
                temp.prev = tail;
                tail.next = temp;
                tail = tail.next;
            }
            NodeD trueTail = new NodeD(length, scanner.nextInt());
            trueTail.prev = tail;
            tail.next = trueTail;
            trueTail.next = head;
            head.prev = trueTail;
            while (true) {
                start = start % length;
                if (start == 0) {
                    start = head.prev.number;
                    head.prev.prev.next = head;
                    head.prev = head.prev.prev;
                    length--;
                } else {
                    if (start < length + 2 - start) {
                        for (int j = 0; j < start; j++) {
                            head = head.next;
                        }
                        start = head.prev.number;
                        head.prev.prev.next = head;
                        head.prev = head.prev.prev;
                        length--;
                    } else {
                        for (int j = 1; j <= length - start; j++) {
                            head = head.prev;
                        }
                        start = head.prev.number;
                        head.prev.prev.next = head;
                        head.prev = head.prev.prev;
                        length--;
                    }
                }
                if (length == 1) {
                    System.out.println(head.position);
                    break;
                }
            }

        }

    }


}

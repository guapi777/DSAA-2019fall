package com.company.Lab3;

import java.util.Scanner;

class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
    }

    Node() {
    }
}


public class problemB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();
        for (int i = 0; i < time; i++) {
            int duichang = scanner.nextInt();
            int cishu = scanner.nextInt();
            Node head = new Node(scanner.nextInt());
            Node tail = null;
            Node[] Nodes = new Node[duichang];
            for (int j = 1; j < duichang; j++) {
                Node newNode = new Node(scanner.nextInt());
                if (j == 1) {
                    head.next = newNode;
                    tail = head;
                }
                Node temp = tail;
                tail = newNode;
                temp.next = tail;
                tail.prev = temp;
            }
            Node temp = head;
            while (temp != null) {
                Nodes[temp.data] = temp;
                temp = temp.next;
            }
            Integer head2 = null;
            for (int j = 0; j < cishu; j++) {
                int start1 = scanner.nextInt();
                int end1 = scanner.nextInt();
                int start2 = scanner.nextInt();
                int end2 = scanner.nextInt();
                Node linshi = new Node();

                linshi.prev = Nodes[start2].prev;
                linshi.next = Nodes[end2].next;

                Nodes[end2].next = Nodes[end1].next;
                Nodes[start2].prev = Nodes[start1].prev;
                if (Nodes[start1].prev != null) {
                    Nodes[start1].prev.next = Nodes[start2];
                } else {
                    head2 = Nodes[start2].data;
                }
                if (Nodes[end2].next.equals(Nodes[start2])) {
                    Nodes[end2].next = Nodes[start1];
                    Nodes[end1].next = linshi.next;
                    Nodes[start1].prev = Nodes[end2];

                } else {
                    Nodes[end1].next = linshi.next;
                    Nodes[start1].prev = linshi.prev;
                    linshi.prev.next = Nodes[start1];
                }

            }
            if (head2 != null) {
                while (Nodes[head2] != null) {
                    System.out.print(Nodes[head2].data + " ");
                    Nodes[head2] = Nodes[head2].next;
                }
            } else {
                while (head != null) {
                    System.out.print(head.data + " ");
                    head = head.next;
                }
            }
            System.out.println();
        }
    }

}

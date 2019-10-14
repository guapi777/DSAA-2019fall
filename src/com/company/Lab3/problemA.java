package com.company.Lab3;

import java.util.Scanner;

 class node {
    int xishu;
    int mi;
    node next;

    node(int xishu, int mi) {
        this.xishu = xishu;
        this.mi = mi;
    }
}

public class problemA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ceshicishu = scanner.nextInt();
        for (int i = 0; i < ceshicishu; i++) {
            int time = scanner.nextInt();
            node head1 = null;
            node tail1 = null;
            node head2 = null;
            node tail2 = null;
            for (int j = 0; j < time; j++) {
                if (head1 == null) {
                    head1 = tail1 = new node(scanner.nextInt(), scanner.nextInt());
                } else {
                    node temp = new node(scanner.nextInt(), scanner.nextInt());
                    if (temp.mi == tail1.mi) {
                        tail1.xishu += temp.xishu;
                    } else {
                        tail1.next = temp;
                        tail1 = temp;
                    }
                }
            }
            int time2 = scanner.nextInt();
            for (int j = 0; j < time2; j++) {
                if (head2 == null) {
                    head2 = tail2 = new node(scanner.nextInt(), scanner.nextInt());
                } else {
                    node temp = new node(scanner.nextInt(), scanner.nextInt());
                    if (temp.mi == tail2.mi) {
                        tail2.xishu += temp.xishu;
                    } else {
                        tail2.next = temp;
                        tail2 = temp;
                    }
                }
            }
            node newNodeHead = null;
            node newNodeTail;
            node temp1 = head1;
            node temp2 = head2;
            if (temp2 != null && temp1 != null) {
                if (temp1.mi > temp2.mi) {
                    newNodeTail = newNodeHead = new node(temp2.xishu, temp2.mi);
                    temp2 = temp2.next;
                } else if (temp1.mi < temp2.mi) {
                    newNodeTail = newNodeHead = new node(temp1.xishu, temp1.mi);
                    temp1 = temp1.next;
                } else {
                    newNodeTail = newNodeHead = new node(temp1.xishu + temp2.xishu, temp1.mi);
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                while (temp1 != null || temp2 != null) {
                    if (temp1 == null) {
                        newNodeTail.next = temp2;
                        break;
                    } else if (temp2 == null) {
                        newNodeTail.next = temp1;
                        break;
                    } else if (temp1.mi > temp2.mi) {
                        node temp = new node(temp2.xishu, temp2.mi);
                        newNodeTail.next = temp;
                        newNodeTail = temp;
                        temp2 = temp2.next;
                    } else if (temp1.mi < temp2.mi) {
                        node temp = new node(temp1.xishu, temp1.mi);
                        newNodeTail.next = temp;
                        newNodeTail = temp;
                        temp1 = temp1.next;
                    } else {
                        node temp = new node(temp1.xishu + temp2.xishu, temp1.mi);
                        newNodeTail.next = temp;
                        newNodeTail = temp;
                        temp1 = temp1.next;
                        temp2 = temp2.next;
                    }
                }
            } else if (temp2 == null) {
                newNodeHead = head1;
            }
            int cishu = scanner.nextInt();
            int[] tags = new int[cishu];
            for (int j = 0; j < cishu; j++) {
                tags[j] = scanner.nextInt();
            }
            int aa = 0;
            node temp = newNodeHead;
            if (cishu > 0) {
                while (temp != null) {
                    if (temp.mi == tags[aa]) {
                        System.out.print(temp.xishu + " ");
                        aa++;
                        if (aa >= cishu) {
                            break;
                        }
                        temp = temp.next;
                    } else if (temp.mi < tags[aa]) {
                        temp = temp.next;
                    } else {
                        System.out.print(0 + " ");
                        aa++;
                        if (aa >= cishu) {
                            break;
                        }
                    }
                }
                for (int j = aa; j < cishu; j++) {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();

        }
    }
}

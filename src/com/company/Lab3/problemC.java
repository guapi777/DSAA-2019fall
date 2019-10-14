package com.company.Lab3;

import java.util.Scanner;

class NodeC {
    Integer data;
    NodeC next;
    NodeC prev;

    NodeC(Integer data) {
        this.data = data;
    }
}

public class problemC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            scanner.nextInt();
            scanner.nextLine();
            char[] chars = scanner.nextLine().toCharArray();
            NodeC EOL = new NodeC(-1);
            NodeC tag = EOL;
            NodeC head = EOL;
            for (int j = 0; j < chars.length; j++) {
                if (Character.isDigit(chars[j])) {
                    int num = (int) chars[j] - '0';
                    if (tag != null) {
                        if (tag.prev == null) {
                            head = new NodeC(num);
                            head.next = tag;
                            tag.prev = head;
                        } else {
                            NodeC temp = new NodeC(num);
                            tag.prev.next = temp;
                            temp.prev = tag.prev;
                            temp.next = tag;
                            tag.prev = temp;
                        }
                    }
                } else if (chars[j] == 'r') {
                    if (tag != null) {
                        if (j + 1 < chars.length) {
                            if (tag.data != -1) {
                                tag.data = (int) chars[j + 1] - '0';
                                j = j + 1;
                            } else {
                                tag.data = (int) chars[j + 1] - '0';
                                EOL = new NodeC(-1);
                                EOL.prev = tag;
                                tag.next = EOL;
                                j = j + 1;
                            }
                        }
                    }
                } else if (chars[j] == 'I') {
                    if (tag != null) {
                        tag = head;
                    }
                } else if (chars[j] == 'H') {
                    if (tag != null) {
                        if (tag != head) {
                            tag = tag.prev;
                        }
                    }
                } else if (chars[j] == 'L') {
                    if (tag != null) {
                        if (tag!= EOL) {
                            tag = tag.next;
                        }
                    }

                } else if (chars[j] == 'x') {
                    if (tag != null) {
                        if (tag == head && head.data != -1) {
                            tag = tag.next;
                            tag.prev = null;
                            head = tag;
                        } else if (head.data == -1 || tag == EOL) {

                        } else {
                            tag.prev.next = tag.next;
                            tag.next.prev = tag.prev;
                            tag = tag.next;
                        }
                    }
                }
            }
            while (head.data != -1) {
                System.out.print(head.data);
                head = head.next;
            }

            System.out.println();
        }
    }
}

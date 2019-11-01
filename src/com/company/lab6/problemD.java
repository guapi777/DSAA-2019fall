package com.company.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problemD {
    static class linkedList {
        private Node first;
        private Node last;

        static class Node {
            private Node previous;
            private Node next;
            int data;

            Node(Node previous, Node next, int data) {
                this.previous = previous;
                this.next = next;
                this.data = data;
            }
        }

        void addLast(int e) {
            if (last == null) {
                Node node = new Node(null, null, e);
                this.first = node;
                this.last = node;
            } else {
                Node node = new Node(last, null, e);
                this.last.next = node;
                this.last = node;
            }
        }

        void insertPrevious(Node base, Node newNode) {
            base.previous.next = newNode;
            newNode.previous = base.previous;
            newNode.next = base;
            base.previous = newNode;
        }


    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }


    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int[] ints = new int[in.nextInt()];
            for (int j = 0; j < ints.length; j++) {
                ints[j] = in.nextInt();
            }
            Arrays.sort(ints);
            linkedList list = new linkedList();
            for (int anInt : ints) {
                list.addLast(anInt);
            }
            long answer = 0;

            int length = ints.length;
            while (length >= 2) {
                linkedList.Node temp = list.first;
                int he = temp.data + temp.next.data;
                answer = answer + he;
                if (length == 2) {
                    break;
                }
                list.first = list.first.next.next;
                list.first.previous = null;
                linkedList.Node temp2 = list.first;
                if (he <= temp2.data) {
                    linkedList.Node node = new linkedList.Node(null, list.first, he);
                    list.first.previous = node;
                    list.first = node;
                    length--;
                    continue;
                }

                while (temp2.next != null) {
                    temp2 = temp2.next;
                }
                if (temp2.data <= he) {
                    temp2.next = new linkedList.Node(temp2, null, he);
                    length--;
                    continue;
                }
                linkedList.Node temp3 = list.first;
                while (temp3.data <= he) {
                    temp3 = temp3.next;
                }
                list.insertPrevious(temp3, new linkedList.Node(null, null, he));
                length--;
            }
            System.out.println(answer);
        }
    }
}

package com.company.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class problemC {


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

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

    static class Node {
        ArrayList<Node> next;
        ArrayList<Node> prev;
        Integer value;
        int tag;

        Node(int value) {
            next = new ArrayList<>();
            prev = new ArrayList<>();
            this.value = value;

        }

        void addNext(Node node) {
            next.add(node);
        }

        void addPrev(Node node) {
            prev.add(node);
        }

    }

    static class stack {
        ArrayList<Integer> arrayList;

        stack() {
            arrayList = new ArrayList<>();
        }

        void enStack(int num) {
            arrayList.add(num);
        }

        int deStack() {
            int a = arrayList.get(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);

            return a;
        }

    }


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            nodes[a].addNext(nodes[b]);
            nodes[b].addPrev(nodes[a]);
        }

        stack stack = new stack();

        int[] ints = new int[n];

        stack.enStack(0);
        ints[0] = 1;


        while (stack.arrayList.size() != 0) {

            int now = stack.deStack();

            for (int i = 0; i < nodes[now].next.size(); i++) {
                if (ints[nodes[now].next.get(i).value] == 0) {
                    stack.enStack(nodes[now].next.get(i).value);
                    ints[nodes[now].next.get(i).value] = 1;
                }

            }

        }
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            if (ints[i] == 0) {
                flag = true;
                break;
            }
        }
        stack stack2 = new stack();

        int[] ints2 = new int[n];

        stack2.enStack(0);
        ints2[0] = 1;


        while (stack2.arrayList.size() != 0) {

            int now = stack2.deStack();

            for (int i = 0; i < nodes[now].prev.size(); i++) {
                if (ints2[nodes[now].prev.get(i).value] == 0) {
                    stack2.enStack(nodes[now].prev.get(i).value);
                    ints2[nodes[now].prev.get(i).value] = 1;
                }

            }

        }
        for (int i = 0; i < n; i++) {
            if (ints2[i] == 0) {
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.print("ovarB");
        } else System.out.print("Bravo");
    }
}

package com.company.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemI {
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

    static class stack {
        ArrayList<Integer> arrayList;

        stack() {
            arrayList = new ArrayList<>();
        }

        void enStack(int num) {
            arrayList.add(num);
        }

        void deStack() {
            arrayList.remove(arrayList.size() - 1);
        }

        int getTop() {
            return arrayList.get(arrayList.size() - 1);
        }
    }


    static class Node {
        ArrayList<Node> next;
        ArrayList<Node> prev;
        int key;
        Long value;
        int inDegree;
        int tag;

        Node(int key, long value, int inDegree, int tag) {
            next = new ArrayList<>();
            prev = new ArrayList<>();
            this.key = key;
            this.value = value;
            this.inDegree = inDegree;
            this.tag = tag;

        }

        void addNext(Node node) {
            next.add(node);
            node.inDegree++;
        }


    }


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {

            int n = in.nextInt();
            int m = in.nextInt();

            Node[] nodes = new Node[n];
            int[] aints = new int[n];
            int[] bints = new int[n];

            for (int j = 0; j < n; j++) {
                nodes[j] = new Node(j, 0, 0, 0);
            }

            for (int j = 0; j < n; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                aints[j] = a;
                bints[j] = b;
            }
            for (int j = 0; j < m; j++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                nodes[a].addNext(nodes[b]);
                nodes[b].prev.add(nodes[a]);
            }


            stack stack = new stack();
            for (int j = 0; j < n; j++) {


                if (nodes[j].inDegree == 0) {
                    stack.enStack(nodes[j].key);
                    nodes[j].key = 1;
                }
            }
            while (stack.arrayList.size() != 0) {
                Node tmp = nodes[stack.getTop()];
                boolean complete = true;
                for (int j = 0; j < tmp.next.size(); j++) {
                    if (tmp.next.get(j).tag == 0) {
                        stack.enStack(tmp.next.get(j).key);
                        complete = false;
                        break;
                    }
                }
                if (complete) {
                    for (int j = 0; j < tmp.prev.size(); j++) {
                        tmp.prev.get(j).value = (tmp.prev.get(j).value + bints[tmp.key] + tmp.value) % 1000000007;
                    }
                    tmp.tag = 2;
                    stack.deStack();
                }
            }

            long ans = 0;
            for (int j = 0; j < n; j++) {
                ans = ans + (aints[j] * nodes[j].value) % 1000000007;
            }
            System.out.println(ans % 1000000007);

        }
    }
}

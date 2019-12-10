package com.company.lab8;

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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

    static class Node {
        Node next;
        int value;

        Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }

    }

    static class queue {
        ArrayList<Node> arrayList;

        queue() {
            this.arrayList = new ArrayList<>();
        }

        void enQueue(Node node) {
            arrayList.add(node);
        }

        Node deQueue() {
            Node tmp = arrayList.get(0);
            arrayList.remove(0);
            return tmp;
        }


    }


    public static void main(String[] args) {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int n = in.nextInt();
        long m = in.nextLong();
        Node[] nodes = new Node[8000004];
        int count = n;
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(null, i);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            long weight = in.nextLong();
            Node tmp;
            if (nodes[x - 1].next != null) {
                tmp = nodes[x - 1].next;

                while (tmp.next != null) {
                    tmp = tmp.next;
                }
            } else tmp = nodes[x - 1];

            if (weight == 2) {
                tmp.next = new Node(null, count);
                nodes[count].next = new Node(null, y - 1);
                count++;
            } else {
                tmp.next = new Node(null, y - 1);
            }

        }


        int[] ints = new int[8000004];
        int[] depth = new int[8000004];

        queue queue = new queue();
        queue.enQueue(nodes[0]);
        boolean flag = false;

        ints[nodes[0].value] = 1;
        while (queue.arrayList.size() != 0) {
            Node tmp = queue.deQueue();
            ints[tmp.value] = 1;

            Node tmp2 = new Node(tmp.next, tmp.value);

            while (tmp.next != null && ints[tmp.next.value] == 0) {
                queue.enQueue(nodes[tmp.next.value]);
                depth[tmp.next.value] = depth[tmp2.value] + 1;
                ints[tmp.next.value] = 1;
                if (tmp.next.value == n - 1) {
                    System.out.print(depth[n - 1]);
                    flag = true;
                    break;
                }
                tmp = tmp.next;
            }
            if (flag)
                break;
        }
        if (depth[n - 1] == 0)
            System.out.print(-1);
    }
}

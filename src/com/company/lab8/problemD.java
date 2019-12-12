package com.company.lab8;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemD {
    static class Node {
        int value;
        Node next;

        Node(int value, Node next) {

            this.value = value;
            this.next = next;
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

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            Node[] nodes = new Node[in.nextInt()];
            for (int j = 0; j < nodes.length; j++) {
                nodes[j] = new Node(j, null);
            }
            int[] types = new int[nodes.length];
            int[] tag = new int[nodes.length];
            int road = in.nextInt();
            for (int j = 0; j < road; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                Node tmp1, tmp2;
                if (nodes[a - 1].next != null) {
                    tmp1 = nodes[a - 1].next;

                    while (tmp1.next != null) {
                        tmp1 = tmp1.next;
                    }
                } else tmp1 = nodes[a - 1];

                if (nodes[b - 1].next != null) {
                    tmp2 = nodes[b - 1].next;

                    while (tmp2.next != null) {
                        tmp2 = tmp2.next;
                    }
                } else tmp2 = nodes[b - 1];
                tmp1.next = new Node(b - 1, null);
                tmp2.next = new Node(a - 1, null);
            }
            queue queue = new queue();
            queue.enQueue(nodes[0]);
            types[nodes[0].value] = 1;
            while (queue.arrayList.size() != 0) {
                Node tmp = queue.deQueue();
                int curType = types[tmp.value];
                tag[tmp.value] = 2;
                while (tmp.next != null) {
                    if (tag[tmp.next.value] == 0) {
                        queue.enQueue(nodes[tmp.next.value]);
                        tag[tmp.next.value] = 1;
                        if (curType == 1)
                            types[tmp.next.value] = 2;
                        else if (curType == 2)
                            types[tmp.next.value] = 1;
                    }
                    tmp = tmp.next;
                }
            }
            int count = 0;
            for (int type : types) {
                if (type == 1)
                    count++;
            }
            if (count < types.length - count) {
                System.out.println(count);
                for (int j = 0; j < types.length; j++) {
                    if (types[j] == 1)
                        System.out.print(j + 1 + " ");
                }
            } else {
                System.out.println(types.length - count);
                for (int j = 0; j < types.length; j++) {
                    if (types[j] == 2 || types[j] == 0)
                        System.out.print(j + 1 + " ");
                }
            }
            System.out.println();
        }
    }
}

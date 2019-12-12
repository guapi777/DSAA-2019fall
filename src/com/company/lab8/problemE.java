package com.company.lab8;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//bfs求字典序，利用优先队列
//最小的尽可能靠前->逆序最大字典序
//建立图的时候反向
//用大顶堆
class problemE {
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        InputReader(
                InputStream stream) {
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
        int value;
        int inDegree;
        int tag;

        Node(int value, int inDegree, int tag) {
            next = new ArrayList<>();
            this.value = value;
            this.inDegree = inDegree;
            this.tag = tag;

        }

        void addNext(Node node) {
            next.add(node);
            node.inDegree++;
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
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        int times = in.nextInt();

        for (int i = 0; i < times; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Node[] nodes = new Node[n];
            for (int j = 0; j < n; j++) {
                nodes[j] = new Node(j, 0, 0);
            }
            for (int j = 0; j < m; j++) {
                int ru = in.nextInt();
                int chu = in.nextInt();
                nodes[chu - 1].addNext(nodes[ru - 1]);
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            stack stack = new stack();
            for (int j = 0; j < n; j++) {
                if (nodes[j].inDegree == 0) {
                    maxHeap.add(nodes[j].value);
                    nodes[j].tag = 1;
                }
            }
            while (maxHeap.size() != 0) {
                int max = maxHeap.remove();
                stack.enStack(max);
                for (int k = 0; k < nodes[max].next.size(); k++) {
                    nodes[max].next.get(k).inDegree--;
                    if (nodes[max].next.get(k).inDegree == 0 && nodes[max].next.get(k).tag == 0) {
                        maxHeap.add(nodes[max].next.get(k).value);
                        nodes[max].next.get(k).tag = 1;
                    }
                }
            }
            while (stack.arrayList.size() != 0) {
                out.print(stack.deStack() + 1 + " ");
            }
            out.println();
        }
        out.close();
    }
}






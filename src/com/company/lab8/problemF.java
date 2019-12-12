package com.company.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//n^2时间遍历，判断是否能叠在一起，形成一个DAG图
//dag是有向无环图
//Dijkstra算法
//关键词：有向无环图最长路径的标准解法
public class problemF {
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

    static class Node {
        ArrayList<Node> next;
        int x;
        int y;
        int z;
        int value;
        int inDegree;
        int tag;

        Node(int x, int y, int z, int value, int inDegree, int tag) {
            this.x = x;
            this.z = z;
            this.y = y;
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

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            Node[] nodes = new Node[in.nextInt()];

            for (int j = 0; j < nodes.length; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int z = in.nextInt();
                nodes[j] = new Node(x, y, z, z, 0, 0);
            }
            for (int j = 0; j < nodes.length; j++) {
                for (int k = j + 1; k < nodes.length; k++) {
                    if ((nodes[j].x < nodes[k].x && nodes[j].y < nodes[k].y)
                            || (nodes[j].x < nodes[k].y && nodes[j].y < nodes[k].x))
                        nodes[k].addNext(nodes[j]);
                    else if ((nodes[k].x < nodes[j].x && nodes[k].y < nodes[j].y)
                            || (nodes[k].x < nodes[j].y && nodes[k].y < nodes[j].x))
                        nodes[j].addNext(nodes[k]);
                }
            }

            queue queue = new queue();
            for (Node node : nodes) {
                if (node.inDegree == 0) {
                    queue.enQueue(node);
                    node.tag = 1;
                }
            }
            while (queue.arrayList.size() != 0) {
                Node tmp = queue.deQueue();
                tmp.tag = 2;
                for (int j = 0; j < tmp.next.size(); j++) {
                    tmp.next.get(j).inDegree--;
                    if (tmp.next.get(j).z + tmp.value > tmp.next.get(j).value)
                        tmp.next.get(j).value = tmp.next.get(j).z + tmp.value;
                    if (tmp.next.get(j).inDegree == 0 && tmp.next.get(j).tag == 0)
                        queue.enQueue(tmp.next.get(j));
                }

            }

            long max = 0;
            for (Node node : nodes) {
                if (node.value > max)
                    max = node.value;
            }
            System.out.println(max);
        }
    }
}

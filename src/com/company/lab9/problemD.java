package com.company.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problemD {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }

    static class Node {
        ArrayList<Node> next;
        int value;
        int weight;
        int flag;


        Node(int value) {
            next = new ArrayList<>();
            this.value = value;
            weight = -1;
            flag = 0;
        }

        void addNext(Node node) {
            next.add(node);
        }

    }


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        //    int time = in.nextInt();
        //   for (int p = 0; p < time; p++) {


        int n = in.nextInt();
        int m = in.nextInt();
        Node[][] nodes = new Node[n][m];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int a = in.nextInt();
                nodes[i][j] = new Node(a);
            }

        }


        if (n != 1 && m != 1) {

            //添加非边缘
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    nodes[i][j].addNext(nodes[i - 1][j]);
                    nodes[i][j].addNext(nodes[i + 1][j]);
                    nodes[i][j].addNext(nodes[i][j - 1]);
                    nodes[i][j].addNext(nodes[i][j + 1]);
                }
            }

            //添加列
            for (int i = 1; i < m - 1; i++) {
                nodes[0][i].addNext(nodes[1][i]);
                nodes[0][i].addNext(nodes[0][i + 1]);
                nodes[0][i].addNext(nodes[0][i - 1]);
            }

            for (int i = 1; i < m - 1; i++) {
                nodes[n - 1][i].addNext(nodes[n - 2][i]);
                nodes[n - 1][i].addNext(nodes[n - 1][i - 1]);
                nodes[n - 1][i].addNext(nodes[n - 1][i + 1]);
            }

            //添加行
            for (int i = 1; i < n - 1; i++) {
                nodes[i][0].addNext(nodes[i - 1][0]);
                nodes[i][0].addNext(nodes[i + 1][0]);
                nodes[i][0].addNext(nodes[i][1]);
            }

            for (int i = 1; i < n - 1; i++) {
                nodes[i][m - 1].addNext(nodes[i - 1][m - 1]);
                nodes[i][m - 1].addNext(nodes[i + 1][m - 1]);
                nodes[i][m - 1].addNext(nodes[i][m - 2]);
            }

            nodes[0][0].addNext(nodes[0][1]);
            nodes[0][0].addNext(nodes[1][0]);

            nodes[n - 1][0].addNext(nodes[n - 1][1]);
            nodes[n - 1][0].addNext(nodes[n - 2][0]);

            nodes[n - 1][m - 1].addNext(nodes[n - 2][m - 1]);
            nodes[n - 1][m - 1].addNext(nodes[n - 1][m - 2]);

            nodes[0][m - 1].addNext(nodes[0][m - 2]);
            nodes[0][m - 1].addNext(nodes[1][m - 1]);

        } else if (n == 1 && m != 1) {

            for (int i = 1; i < m - 1; i++) {
                nodes[0][i].addNext(nodes[0][i - 1]);
                nodes[0][i].addNext(nodes[0][i + 1]);
            }
            nodes[0][0].addNext(nodes[0][1]);
            nodes[0][m - 1].addNext(nodes[0][m - 2]);
        } else if (n != 1) {

            for (int i = 1; i < n - 1; i++) {
                nodes[i][0].addNext(nodes[i - 1][0]);
                nodes[i][0].addNext(nodes[i + 1][0]);
            }

            nodes[0][0].addNext(nodes[1][0]);
            nodes[n - 1][0].addNext(nodes[n - 2][0]);

        }


        PriorityQueue<Node> maxHeap = new PriorityQueue<>(n * m, (i1, i2) -> i2.weight - i1.weight);


        nodes[0][0].flag = 1;

        for (int i = 0; i < nodes[0][0].next.size(); i++) {
            nodes[0][0].next.get(i).weight = nodes[0][0].value * nodes[0][0].next.get(i).value;
            maxHeap.add(nodes[0][0].next.get(i));
            nodes[0][0].next.get(i).flag = 1;
        }


        while (!maxHeap.isEmpty()) {
            Node tmp = maxHeap.poll();
            for (int i = 0; i < tmp.next.size(); i++) {
                int nowWeight = tmp.value * tmp.next.get(i).value;
                if (tmp.next.get(i).flag == 1 && nowWeight > tmp.next.get(i).weight && maxHeap.contains(tmp.next.get(i))) {
                    maxHeap.remove(tmp.next.get(i));
                    tmp.next.get(i).weight = nowWeight;
                    maxHeap.add(tmp.next.get(i));
                } else if (tmp.next.get(i).flag == 0) {
                    tmp.next.get(i).weight = nowWeight;
                    maxHeap.add(tmp.next.get(i));
                    tmp.next.get(i).flag = 1;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += nodes[i][j].weight;
            }
        }
        System.out.println(ans - nodes[0][0].weight);

        //}
    }
}

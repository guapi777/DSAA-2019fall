package com.company.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemB {
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


    private static int MAX = 10000000;
    private ArrayList<Edge> edge = new ArrayList<>();//整个图的边
    private ArrayList<Edge> target = new ArrayList<>();//目标边，最小生成树
    private int[] parent = new int[MAX];//标志所在的集合
    private int n;//结点个数

    public static void main(String[] args) {
        problemB sp = new problemB();
        sp.init();
        sp.kruskal();
        sp.print();
    }

    //初始化
    public void init() {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int p, q;
        long w;

        n = in.nextInt();

        int m = in.nextInt();
        for (int j = 0; j < m; j++) {

            p = in.nextInt();
            q = in.nextInt();
            w = in.nextInt();
            if (p < 0 || q < 0 || w < 0) {
                break;
            }
            Edge e = new Edge();
            e.start = p;
            e.end = q;
            e.cost = w;
            edge.add(e);
        }

        for (int i = 1; i <= n; ++i) {
            parent[i] = i;
        }

    }

    //集合合并
    public void union(int j, int k) {
        for (int i = 1; i <= n; ++i) {
            if (parent[i] == j) {
                parent[i] = k;
            }
        }
    }

    //Kruskal算法主体
    public void kruskal() {
        //找剩下的n-2条边
        int i = 0;
        while (i < n - 1 && edge.size() > 0) {
            //每次取一最小边，并删除
            //定义无穷大
            long min = 99999999;
            Edge tmp = null;
            for (Edge tt : edge) {
                if (tt.cost < min) {
                    min = tt.cost;
                    tmp = tt;
                }
            }
            assert tmp != null;
            int jj = parent[tmp.start];
            int kk = parent[tmp.end];
            //去掉环，判断当前这条边的两个端点是否属于同一棵树
            if (jj != kk) {
                ++i;
                target.add(tmp);
                union(jj, kk);
            }
            edge.remove(tmp);
        }
    }

    //打印结果
    public void print() {
        long sum = 0;
        for (Edge e : target) {
            sum += e.cost;
        }
        System.out.print(sum);
    }

    static class Edge {
        public int start;//始边
        public int end;//终边
        public long cost;//权重
    }
}


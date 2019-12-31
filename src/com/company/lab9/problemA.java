package com.company.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class problemA {
    static class Graph {
        Graph() {
        }
        Map<Integer, List<Node>> map = new HashMap<>();//输出的地图
        List<Node> list = new ArrayList<>();

        void addNode(Integer src, Integer des, int len) {
            list.add(new Node(src, des, len));
        }

        void update() {
            for (Node node : list) {
                List<Node> temp = map.get(node.getSrc());
                if (temp == null)
                    temp = new ArrayList<>();
                temp.add(node);
                map.put(node.getSrc(), temp);
            }
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

    static class Node {
        private Integer src;//起点
        private Integer des;//终点
        private int len;      //距离长度

        Node(Integer src, Integer des, int len) {
            this.src = src;
            this.des = des;
            this.len = len;
        }

        Integer getSrc() {
            return src;
        }

        Integer getDes() {
            return des;
        }

        int getLen() {
            return len;
        }
    }

    static Map<Integer, Integer> dijkstra(Map<Integer, List<Node>> graph, Integer source) {
        //堆的初始化
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(source, 0);
        queue.add(map.entrySet().iterator().next());

        Map<Integer, Integer> visited = new HashMap<>();
        while (!queue.isEmpty()) {
            //从堆中获取最小距离的节点
            Map.Entry<Integer, Integer> temp = queue.poll();
            //讲距离值添加到visited
            visited.computeIfAbsent(temp.getKey(), k -> temp.getValue());
            if (graph.get(temp.getKey()) == null) {
                continue;
            }
            //更新与temp相邻各节点neighbour的distance
            for (int i = 0; i < graph.get(temp.getKey()).size(); i++) {
                if (visited.get(graph.get(temp.getKey()).get(i).getDes()) != null) {
                    continue;
                }

                Map<Integer, Integer> temp2 = new HashMap<>();
                temp2.put(graph.get(temp.getKey()).get(i).getDes(), temp.getValue() + graph.get(temp.getKey()).get(i).getLen());
                queue.add(temp2.entrySet().iterator().next());
            }
        }
        return visited;
    }


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        Graph graph = new Graph();
        in.nextInt();
        int roads = in.nextInt();
        for (int i = 0; i < roads; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            graph.addNode(a, b, c);
            graph.addNode(b, a, c);
        }
        int start = in.nextInt();
        int end = in.nextInt();

        graph.update();
        Map<Integer, Integer> result = dijkstra(graph.map, start);
        if (result.containsKey(end)) {
            System.out.println(result.get(end));
        } else System.out.println(-1);

    }
}


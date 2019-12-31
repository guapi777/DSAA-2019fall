package com.company.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemF {

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

        long nextLong() {
            return Long.parseLong(next());
        }

    }


    static class stack {
        ArrayList<Integer> arrayList;

        stack() {
            arrayList = new ArrayList<>();
        }

        void enStack(int in) {
            arrayList.add(in);
        }

        int deStack() {
            int a = arrayList.get(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
            return a;
        }

        int getTop() {
            return arrayList.get(arrayList.size() - 1);
        }

    }

    static class Node {
        ArrayList<Node> next;
        ArrayList<Node> prev;
        int position;
        int cost;
        int inDegree;
        int flag;


        Node(int cost, int position) {
            this.position = position;
            next = new ArrayList<>();
            prev = new ArrayList<>();
            this.cost = cost;
            inDegree = 0;
            flag = 0;
        }

        void addNext(Node node) {
            next.add(node);
        }

        void addPrev(Node node) {
            prev.add(node);
        }

    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int number = in.nextInt();

        long[] xs = new long[number];
        long[] ys = new long[number];
        long[] rs = new long[number];
        int[] costs = new int[number];


        for (int i = 0; i < number; i++) {
            xs[i] = in.nextLong();
            ys[i] = in.nextLong();
            rs[i] = in.nextLong();
            costs[i] = in.nextInt();

        }

        Node[] nodes = new Node[number];

        for (int i = 0; i < number; i++) {
            nodes[i] = new Node(costs[i], i);
        }

        for (int i = 0; i < number; i++) {
            for (int j = i + 1; j < number; j++) {
                long distance = (xs[i] - xs[j]) * (xs[i] - xs[j]) + (ys[i] - ys[j]) * (ys[i] - ys[j]);

                if (distance <= rs[i] * rs[i]) {
                    nodes[i].addNext(nodes[j]);
                    nodes[j].addPrev(nodes[i]);
                    nodes[j].inDegree++;
                }
                if (distance <= rs[j] * rs[j]) {
                    nodes[j].addNext(nodes[i]);
                    nodes[i].addPrev(nodes[j]);
                    nodes[i].inDegree++;
                }
            }
        }


        ArrayList<Integer> ints = new ArrayList<>();


        stack stack = new stack();

        for (int i = 0; i < number; i++) {
            if (nodes[i].flag == 0) {
                stack.enStack(i);
                nodes[i].flag = 1;
                while (stack.arrayList.size() != 0) {
                    int tmp = stack.getTop();
                    boolean out = true;
                    for (int j = 0; j < nodes[tmp].prev.size(); j++) {
                        if (nodes[tmp].prev.get(j).flag == 0) {
                            nodes[tmp].prev.get(j).flag = 1;
                            stack.enStack(nodes[tmp].prev.get(j).position);
                            out = false;
                            break;
                        }
                    }

                    if (out) {
                        ints.add(stack.deStack());
                    }


                }
            }
        }

        ArrayList<Node> bianlishunxv = new ArrayList<>();
        for (int i = ints.size() - 1; i >= 0; i--) {
            bianlishunxv.add(nodes[ints.get(i)]);
        }

        while (bianlishunxv.size() != 0) {
            ArrayList<Node> tmp = new ArrayList<>();
            int index = bianlishunxv.get(0).position;

            stack stack1 = new stack();
            stack1.enStack(index);
            while (stack1.arrayList.size() != 0) {
                int index2 = stack1.getTop();
                boolean out = true;
                nodes[index2].flag = 0;

                for (int i = 0; i < nodes[index2].next.size(); i++) {
                    if (nodes[index2].next.get(i).flag == 1) {
                        stack1.enStack(nodes[index2].next.get(i).position);
                        nodes[index2].next.get(i).flag = 0;
                        out = false;
                        break;
                    }
                }
                if (out) {
                    int a = stack1.deStack();
                    bianlishunxv.remove(nodes[a]);
                    tmp.add(nodes[a]);
                }


            }

            int minCost = 20000;
            for (Node node : tmp) {
                if (node.cost < minCost)
                    minCost = node.cost;
            }

            for (Node node : tmp) {
                node.cost = 0;
            }

            tmp.get(0).cost = minCost;
            boolean ylzbddfdru = false;


            for (int i = 0; i < tmp.size(); i++) {//对于每个节点
                int a = 0;
                for (Node node : tmp) {//对于其他的节点
                    for (int k = 0; k < node.next.size(); k++) {//对于其他节点的所有子节点
                        if (node.next.get(k).position == tmp.get(i).position)
                            a++;
                    }
                }
                if (a != tmp.get(i).inDegree) {
                    ylzbddfdru = true;
                }
            }
            if (ylzbddfdru) {
                tmp.get(0).cost = 0;
            }
        }
        long ans = 0;
        for (Node node : nodes) {
            ans += node.cost;
        }
        System.out.println(ans);
    }
}
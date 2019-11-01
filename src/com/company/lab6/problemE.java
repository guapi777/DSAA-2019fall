package com.company.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemE {
    static class Node {
        int key;
        int red;
        int blue;
        int color;
        ArrayList<Node> friends;
        int flag1 = 0;
        int flag2 = 0;

        Node(int key) {
            this.key = key;
            friends = new ArrayList<>();
        }

        void setColor(int color) {
            this.color = color;
        }

        int getColor() {
            return color;
        }

        void addFriends(Node node) {
            friends.add(node);
        }

    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

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

    private static int findRed(Node node) {
        node.flag1 = 1;
        int color = node.getColor();
        if (color == 1) {
            node.red++;
        }
        for (int i = 0; i < node.friends.size(); i++) {
            if (node.friends.get(i).flag1 == 0) {
                node.red += findRed(node.friends.get(i));
            }
        }
        return node.red;
    }

    private static int findBlue(Node node) {
        node.flag2 = 1;
        int color = node.getColor();
        if (color == 2) {
            node.blue++;
        }
        for (int i = 0; i < node.friends.size(); i++) {
            if (node.friends.get(i).flag2 == 0) {
                node.blue += findBlue(node.friends.get(i));
            }
        }
        return node.blue;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            Node[] nodes = new Node[in.nextInt()];
            for (int j = 0; j < nodes.length - 1; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                if (nodes[a - 1] == null) {
                    nodes[a - 1] = new Node(a);

                }
                if (nodes[b - 1] == null) {
                    nodes[b - 1] = new Node(b);
                }
                nodes[a - 1].addFriends(nodes[b - 1]);
                nodes[b - 1].addFriends(nodes[a - 1]);
            }
            int answer = 0;
            int sumRed = 0;
            int sumBlue = 0;
            for (Node node : nodes) {
                int a = in.nextInt();
                if (a == 1) {
                    sumRed++;
                }
                if (a == 2) {
                    sumBlue++;
                }
                node.setColor(a);
            }
            findRed(nodes[0]);
            findBlue(nodes[0]);
            for (Node node : nodes) {
                if (node.red == sumRed && node.blue == 0) {
                    answer++;
                }
                if (node.blue == sumBlue && node.red == 0) {
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }
}

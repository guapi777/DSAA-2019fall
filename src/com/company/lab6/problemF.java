package com.company.lab6;

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

    private static int maxLength = 0;
    private static int point = 0;

    static class Node {
        int key;
        ArrayList<Node> friends;
        int flag = 0;
        boolean inCity = false;

        Node(int key) {
            this.key = key;
            friends = new ArrayList<>();
        }

        void addFriends(Node node) {
            friends.add(node);
        }

    }

    private static void findPoint(Node node, int length) {
        node.flag = 1;
        if (node.inCity && length > maxLength) {
            maxLength = length;
            point = node.key;
        }
        for (int i = 0; i < node.friends.size(); i++) {
            if (node.friends.get(i).flag == 0) {
                findPoint(node.friends.get(i), length + 1);
            }
        }

    }


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            Node[] nodes = new Node[in.nextInt()];
            int city = in.nextInt();

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
            if (city == 1) {
                in.nextInt();
                System.out.println(0);
                continue;
            }
            for (int j = 0; j < city; j++) {
                nodes[in.nextInt() - 1].inCity = true;
            }
            findPoint(nodes[0], 0);
            for (Node node : nodes) {
                node.flag = 0;
            }
            maxLength = 0;
            findPoint(nodes[point - 1], 0);
            System.out.println((maxLength + 1) / 2);
            maxLength = 0;
            point = 0;
        }
    }
}

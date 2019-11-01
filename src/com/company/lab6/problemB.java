package com.company.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemB {

    private static ArrayList<Integer> depth = new ArrayList<>();


    static class Node {
        int key;
        ArrayList<Node> friends;
        int flag = 0;

        Node(int key) {
            this.key = key;
            friends = new ArrayList<>();
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

    private static void find(Node node, int deep) {
        node.flag = 1;
        depth.set(node.key - 1, deep);
        for (int i = 0; i < node.friends.size(); i++) {
            if (node.friends.get(i).flag == 0) {
                find(node.friends.get(i), deep + 1);
            }
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            Node[] nodes = new Node[in.nextInt()];
            for (int j = 0; j < nodes.length; j++) {
                depth.add(-1);
            }
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

            find(nodes[0], 0);

            for (int j = 0; j < nodes.length; j++) {
                out.print(depth.get(j) + " ");
            }
            depth = new ArrayList<>();
            out.println();
        }


        out.close();
    }
}

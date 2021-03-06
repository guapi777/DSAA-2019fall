package com.company.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problemA {
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
        int value;
        Node leftNode;
        Node rightNode;
        boolean haveFather = false;

        Node(int value) {
            this.value = value;
        }

        void addLeftNode(Node node) {
            leftNode = node;
            node.haveFather = true;
        }

        void addRightNode(Node node) {
            rightNode = node;
            node.haveFather = true;
        }


    }

    private static boolean isCompleteBTree(Node root) {

        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.leftNode == null && node.rightNode != null) {
                return false;
            }

            if (leaf && node.leftNode != null) {
                return false;
            }

            if (node.leftNode != null) {
                queue.offer(node.leftNode);
            }

            if (node.rightNode != null) {
                queue.offer(node.rightNode);
            } else {
                leaf = true;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int size = in.nextInt();
            Node[] nodes = new Node[size];
            for (int j = 0; j < size; j++) {
                nodes[j] = new Node(j + 1);
            }
            for (int j = 0; j < size; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                if (a != 0) {
                    nodes[j].addLeftNode(nodes[a - 1]);
                }
                if (b != 0) {
                    nodes[j].addRightNode(nodes[b - 1]);
                }

            }
            Node root = null;
            for (int j = 0; j < size; j++) {
                if (!nodes[j].haveFather) {
                    root = nodes[j];
                    break;
                }
            }

            if (isCompleteBTree(root)) {
                System.out.println("Yes");
            } else System.out.println("No");
        }


    }
}

package com.company.lab7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

    private static boolean isMinHeap(Node root) {

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

            if (node.leftNode != null && node.leftNode.value > node.value) {
                queue.offer(node.leftNode);
            } else if (node.rightNode != null && node.rightNode.value < node.value) {
                return false;
            }

            if (node.rightNode != null && node.rightNode.value > node.value) {
                queue.offer(node.rightNode);
            } else if (node.rightNode != null && node.rightNode.value < node.value) {
                return false;
            } else {
                leaf = true;
            }
        }
        return true;
    }

    private static boolean isMaxHeap(Node root) {

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
            if (node.leftNode != null && node.leftNode.value < node.value) {
                queue.offer(node.leftNode);
            } else if (node.rightNode != null && node.rightNode.value > node.value) {
                return false;
            }
            if (node.rightNode != null && node.rightNode.value < node.value) {
                queue.offer(node.rightNode);
            } else if (node.rightNode != null && node.rightNode.value > node.value) {
                return false;
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
                nodes[j] = new Node(in.nextInt());
            }
            boolean flag = false;
            for (int j = 0; j < size - 1; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                if (nodes[a - 1].leftNode == null) {
                    nodes[a - 1].addLeftNode(nodes[b - 1]);
                    continue;
                }
                if (nodes[a - 1].rightNode == null) {
                    nodes[a - 1].addRightNode(nodes[b - 1]);
                    continue;
                }

                flag = true;
            }
            if (flag) {
                System.out.println("Case #" + (i + 1) + ": NO");
                continue;
            }
            Node root = null;
            for (int j = 0; j < size; j++) {
                if (!nodes[j].haveFather) {
                    root = nodes[j];
                    break;
                }
            }
            if (isMinHeap(root) || isMaxHeap(root)) {
                System.out.println("Case #" + (i + 1) + ": YES");
            } else System.out.println("Case #" + (i + 1) + ": NO");
        }
    }
}

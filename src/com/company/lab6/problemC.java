package com.company.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problemC {

    static class Node {
        Node left;
        Node right;
        int key;

        Node(int key) {
            this.key = key;
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


    private static Node structure(int[] pre, int[] In, int x1, int x2, int y1, int y2) {

        if (x1 > x2 || y1 > y2) {
            return null;
        }

        Node head = new Node(pre[x1]);

        int index = -1;
        for (int i = y1; i <= y2; i++) {
            if (In[i] == pre[x1]) {
                index = i;
                break;
            }
        }
        int offSet = index - y1 - 1;

        head.left = structure(pre, In, x1 + 1, x1 + 1 + offSet, y1, index - 1);
        head.right = structure(pre, In, x1 + 2 + offSet, x2, index + 1, y2);
        return head;
    }

    private static void postOrderRe(Node node) {
        if (node != null) {
            postOrderRe(node.left);
            postOrderRe(node.right);
            System.out.print(node.key + " ");
        }
    }


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int length = in.nextInt();
            int[] Pre = new int[length];
            int[] In = new int[length];
            for (int j = 0; j <length ; j++) {
                Pre[j] =in.nextInt();
            }
            for (int j = 0; j <length ; j++) {
                In[j] =in.nextInt();
            }
            Node root = structure(Pre, In, 0, length - 1, 0, length - 1);
            postOrderRe(root);
            System.out.println();
        }
    }
}

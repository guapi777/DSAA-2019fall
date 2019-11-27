package com.company.lab8;

import java.io.*;
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

    static class Node {
        ArrayList<Node> friends;
        int value;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {

            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            Node start = new Node(0);
            for (int j = 0; j < k; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int s = in.nextInt();


            }

        }
    }
}

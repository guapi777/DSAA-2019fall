package com.company.lab8;

import java.io.*;
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

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int city = in.nextInt();
            int[][] matrix = new int[city][city];
            int road = in.nextInt();
            for (int j = 0; j < road; j++) {
                matrix[in.nextInt() - 1][in.nextInt() - 1]++;
            }
            for (int j = 0; j < city; j++) {
                for (int k = 0; k < city; k++) {
                    out.print(matrix[j][k] + " ");
                }
                out.println();
            }
        }
        out.close();
    }
}

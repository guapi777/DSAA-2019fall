package com.company.Lab5;

import java.io.*;
import java.util.StringTokenizer;

public class problemD {
    private static int[] computeTemporaryArray(char[] pattern) {
        int[] lps = new int[pattern.length];
        int index = 0;
        for (int i = 1; i < pattern.length; ) {
            if (pattern[i] == pattern[index]) {
                lps[i] = index + 1;
                index++;
                i++;
            } else {
                if (index != 0) {
                    index = lps[index - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
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

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int a = in.nextInt();
        for (int i = 0; i < a; i++) {
            String s1 = in.next();
            int[] ints = computeTemporaryArray(s1.toCharArray());
            if (ints[s1.length() - 1] == 0) {
                out.println(s1.length());
                continue;
            }
            if (ints[s1.length() - 1] > s1.length() / 2) {
                int b = s1.length() % (s1.length() - ints[s1.length() - 1]);
                if (b == 0) {
                    out.println(0);
                } else out.println(s1.length() - ints[s1.length() - 1] - b);
            } else {
                 out.println(s1.length() - 2*ints[s1.length() - 1] );
            }
        }
        out.close();
    }
}

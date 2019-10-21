package com.company.Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problemC {
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

    private static int halfSearch(long[] arr, long key) {
        int min = 0, max = arr.length - 1, mid;

        while (min <= max) {
            mid = (min + max) >> 1;
            if (key > arr[mid])
                min = mid + 1;
            else if (key < arr[mid])
                max = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    private static long[] pow = new long[50000];

    static {
        long temp = 1;
        for (int i = 0; i < 50000; i++) {
            pow[i] = temp;
            temp *= 139;
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        int ans = 0;
        for (int aaa = 0; aaa < times; aaa++) {
            String s1 = in.next();
            String s2 = in.next();
            int length = (int) Math.ceil((double) s1.length() / 3);
            if (length > s2.length()) {
                continue;
            }

            long[] s2hash = new long[s2.length() - length + 1];

            for (int i = 0; i < length; i++) {
                s2hash[0] += s2.charAt(length - 1 - i) * pow[i];
            }

            for (int i = 1; i < s2.length() - length + 1; i++) {
                s2hash[i] = (s2hash[i - 1] - s2.charAt(i - 1) * pow[length - 1]) * 139 + s2.charAt(i + length - 1);
            }

            long s1hash = 0;

            for (int j = 0; j < length; j++) {
                s1hash += s1.charAt(length - 1 - j) * pow[j];
            }
            Arrays.sort(s2hash);
            int a = halfSearch(s2hash, s1hash);
            if (a != -1) {
                ans++;
            }

        }
        System.out.print(ans);

    }
}

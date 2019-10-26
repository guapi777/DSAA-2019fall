package com.company.Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problemE {
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
    }

    private static long[] pow = new long[50000];

    static {
        long temp = 1;
        for (int i = 0; i < 50000; i++) {
            pow[i] = temp;
            temp *= 139;
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

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        String s1 = in.next();
        String s2 = in.next();
        if (!(s1.length() >= s2.length())) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        int min = 0;
        int max = s2.length();
        while (min < max) {
            int mid = (min + max + 1) >> 1;
            long[] s2hash = new long[s2.length() - mid + 1];

            for (int i = 0; i < mid; i++) {
                s2hash[0] += s2.charAt(mid - 1 - i) * pow[i];
            }

            for (int i = 1; i < s2.length() - mid + 1; i++) {
                s2hash[i] = (s2hash[i - 1] - s2.charAt(i - 1) * pow[mid - 1]) * 139 + s2.charAt(i + mid - 1);
            }
            int a = -1;
            long[] s1hash = new long[s1.length() - mid + 1];

            for (int j = 0; j < mid; j++) {
                s1hash[0] += s1.charAt(mid - 1 - j) * pow[j];
            }

            for (int j = 1; j < s1.length() - mid + 1; j++) {
                s1hash[j] = (s1hash[j - 1] - s1.charAt(j - 1) * pow[mid - 1]) * 139 + s1.charAt(j + mid - 1);
            }
            Arrays.sort(s1hash);
            for (long hash : s2hash) {
                a = halfSearch(s1hash, hash);
                if (a != -1) {
                    break;
                }
            }
            if (a == -1) {
                max = mid - 1;
            } else min = mid;
        }
        System.out.print(max);
    }
}

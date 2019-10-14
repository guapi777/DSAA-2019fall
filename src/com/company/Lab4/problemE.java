package com.company.Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemE {

    static class element {
        int height;
        int position;

        element(int height, int position) {
            this.height = height;
            this.position = position;
        }
    }

    static class stack {

        ArrayList<element> arrayList;

        stack() {
            arrayList = new ArrayList<>();
        }

        int enStack(element element) {
            element element1 = null;
            while (true) {
                if (isEmpty()) {
                    break;
                }

                if (arrayList.get(0).height < element.height) {
                    element1 = arrayList.get(0);
                }

                if (arrayList.size() >= 2) {
                    if (arrayList.get(arrayList.size() - 2).height > element.height
                            && arrayList.get(arrayList.size() - 1).height < element.height) {
                        element1 = arrayList.get(arrayList.size() - 1);
                    }
                }

                if (arrayList.get(arrayList.size() - 1).height < element.height) {
                    arrayList.remove(arrayList.size() - 1);
                } else break;

            }
            arrayList.add(element);

            if (element1 == null) {
                return 0;
            } else return element1.position;

        }

        boolean isEmpty() {
            return arrayList.size() == 0;
        }


    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int length = in.nextInt();
            int[] ints = new int[length];
            stack stack = new stack();
            stack stack1 = new stack();
            for (int j = 0; j < length; j++) {
                ints[j] = in.nextInt();
            }
            int[] wangzuokan = new int[length];
            int[] wangyoukan = new int[length];

            for (int j = 0; j < length; j++) {
                wangzuokan[j] = stack.enStack(new element(ints[j], j + 1));
            }
            for (int j = length - 1; j >= 0; j--) {
                wangyoukan[j] = stack1.enStack(new element(ints[j], j + 1));
            }
            out.println("Case " + (i + 1) + ":");
            for (int j = 0; j < length; j++) {
                out.println(wangzuokan[j] + " " + wangyoukan[j]);
            }

        }

        out.close();
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
}

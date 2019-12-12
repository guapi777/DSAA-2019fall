package com.company.Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemD {

    static class stack {
        ArrayList<Integer> arrayList;

        stack() {
            arrayList = new ArrayList<>();
        }

        void enStack(int num) {
            arrayList.add(num);
        }

        int deStack() {
            int a = arrayList.get(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);

            return a;
        }

        boolean isEmpty() {
            return arrayList.size() == 0;
        }


        int getPeek() {
            return arrayList.get(arrayList.size() - 1);
        }


    }


    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
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
            int length = in.nextInt();
            int[] read = new int[length];
            int[] dizeng = new int[length];
            for (int j = 0; j < length; j++) {
                read[j] = in.nextInt();
            }
            dizeng[length - 1] = read[length - 1];
            for (int j = length - 2; j >= 0; j--) {
                dizeng[j] = Math.min(dizeng[j + 1], read[j]);
            }

            stack stack = new stack();


            for (int j = 0; j < length; j++) {

                while (!stack.isEmpty() && stack.getPeek() < dizeng[j]) {
                    int a = stack.deStack();
                    out.print(a + " ");
                }
                if (read[j] == dizeng[j]) {
                    out.print(read[j] + " ");
                    continue;
                }
                stack.enStack(read[j]);

            }


            while (!stack.isEmpty()) {
                int a = stack.deStack();
                out.print(a + " ");
            }
            if (i != times - 1) {
                out.println();
            }
        }
        out.close();

    }
}

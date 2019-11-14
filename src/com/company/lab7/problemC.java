package com.company.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemC {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class minHeap {
        private ArrayList<Long> mHeap;

        minHeap() {
            this.mHeap = new ArrayList<>();
        }

        long removeMin() {
            if (mHeap.size() == 0) {
                return -1;
            }

            long answer = mHeap.get(0);

            int size = mHeap.size();
            mHeap.set(0, mHeap.get(size - 1));
            mHeap.remove(size - 1);
            if (mHeap.size() > 1) {
                filterDown(mHeap.size() - 1);
            }
            return answer;

        }

        void filterDown(int end) {
            int c = 0;
            int l = 2 * c + 1;
            Long value = mHeap.get(c);

            while (l <= end) {
                if (l + 1 <= mHeap.size() - 1) {
                    int cmp = mHeap.get(l).compareTo(mHeap.get(l + 1));
                    if (l < end && cmp > 0)
                        l++;
                }
                int cmp2 = value.compareTo(mHeap.get(l));
                if (cmp2 <= 0)
                    break;
                else {
                    mHeap.set(c, mHeap.get(l));
                    c = l;
                    l = 2 * l + 1;
                }

            }
            mHeap.set(c, value);

        }

        int getSize() {
            return mHeap.size();
        }


        void filterUp(int start) {
            int c = start;
            int p = (start - 1) / 2;
            Long temp = mHeap.get(c);
            while (c > 0) {
                if (mHeap.get(p) <= temp) {
                    break;
                } else {
                    mHeap.set(c, mHeap.get(p));
                    c = p;
                    p = (p - 1) / 2;
                }
            }
            mHeap.set(c, temp);

        }

        void insert(Long value) {
            int size = mHeap.size();
            mHeap.add(value);
            filterUp(size);
        }

    }

    public static void main(String[] args) {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int times2 = in.nextInt();
            if (times2 == 1) {
                System.out.println(in.nextInt());
                continue;
            }
            minHeap minHeap = new minHeap();
            for (int j = 0; j < times2; j++) {
                minHeap.insert(in.nextLong());
            }
            long answer = 0;
            while (minHeap.getSize() >= 2) {
                Long temp = minHeap.removeMin() + minHeap.removeMin();
                answer += temp;
                minHeap.insert(temp);
            }
            System.out.println(answer);


        }
    }
}

package com.company.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemD {
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

        long nextLong() {
            return Long.parseLong(next());
        }

    }

    static class minHeap {
        private ArrayList<Long> mHeap;

        minHeap() {
            this.mHeap = new ArrayList<>();
        }

        void removeMin() {
            if (mHeap.size() == 0) {
                return;
            }

            int size = mHeap.size();
            mHeap.set(0, mHeap.get(size - 1));
            mHeap.remove(size - 1);
            if (mHeap.size() > 1) {
                filterDown(mHeap.size() - 1);
            }

        }

        long getMin() {
            return mHeap.get(0);
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

    private static long getSum(long num) {
        long answer = 0;
        char[] chars = (num + "").toCharArray();
        for (char aChar : chars) {
            answer += aChar - '0';
        }
        return answer;
    }

    public static void main(String[] args) {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        long times = in.nextLong();
        int k = (int) in.nextLong();
        long last_ans = in.nextLong();
        minHeap minHeap = new minHeap();
        for (int i = 1; i <= times; i++) {
            if (i <= k) {
                minHeap.insert(i + last_ans + getSum(i + last_ans));
            }
            if (i > k) {
                minHeap.insert(i + last_ans + getSum(i + last_ans));
                minHeap.removeMin();
            }
            if (i % 100 == 0) {
                last_ans = minHeap.getMin();
                System.out.print(last_ans + " ");
            }
        }
    }
}

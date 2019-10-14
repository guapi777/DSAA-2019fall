package com.company.Lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problemC {

    static class circleQueue {
        int head;
        int tail;
        int length;
        Integer[] integers;

        circleQueue(int length) {
            this.length = length;
            integers = new Integer[length];
            head = tail = 0;
        }

        boolean isEmpty() {
            return integers[head] == null;
        }

        void addLast(int last) {
            if (integers[head] == null) {
                integers[head] = last;
            } else {
                if (tail == length - 1) {
                    tail = 0;
                    integers[tail] = last;
                } else {
                    tail++;
                    integers[tail] = last;
                }
            }
        }

        void removeFirst() {
            if (integers[head] == null) {
                return;
            }
            if (head == tail) {
                integers[head] = null;
            } else if (head == length - 1) {
                head = 0;
                integers[length - 1] = null;
            } else {
                integers[head] = null;
                head++;
            }
        }

        void removeLast() {
            if (integers[head] == null) {
                return;
            }
            if (head == tail) {
                integers[head] = null;
            } else if (tail == 0) {
                integers[tail] = null;
                tail = length - 1;
            } else {
                integers[tail] = null;
                tail--;
            }
        }

        int getLast() {
            return integers[tail];
        }

        int getValue(int i) {
            return integers[i];
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
        InputReader in = new InputReader(inputStream);
        int length = in.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (true) {
            int a = in.nextInt();
            if (a != -1) {
                arrayList.add(a);
            } else break;
        }
        circleQueue circleQueue = new circleQueue(length);

        if (length == 1) {
            int answer = arrayList.get(0);
            for (int i = 1; i < arrayList.size(); i++) {
                answer = answer ^ arrayList.get(i);
            }
            System.out.print(answer);
        } else {
            circleQueue.addLast(arrayList.get(0));
            for (int i = 1; i < length; i++) {
                while (!circleQueue.isEmpty() && arrayList.get(i) > circleQueue.getLast()) {
                    circleQueue.removeLast();
                }
                circleQueue.addLast(arrayList.get(i));
            }
            int answer = circleQueue.getValue(circleQueue.head);
            for (int i = 0; i <= arrayList.size() - length - 1; i++) {
                if (arrayList.get(i) == circleQueue.getValue(circleQueue.head)) {
                    circleQueue.removeFirst();
                }
                while (!circleQueue.isEmpty() && arrayList.get(i + length) > circleQueue.getLast()) {
                    circleQueue.removeLast();
                }
                circleQueue.addLast(arrayList.get(i + length));
                answer = answer ^ circleQueue.getValue(circleQueue.head);
            }
            System.out.print(answer);
        }

    }
}



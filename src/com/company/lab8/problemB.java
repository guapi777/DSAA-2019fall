package com.company.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        int flag = 0;
        int x;
        int y;
        int r;

        Node(int x, int y, int r) {

            this.x = x;
            this.y = y;
            this.r = r;
            friends = new ArrayList<>();
        }

        void addFriends(Node node) {
            friends.add(node);
        }
    }

    private static Node judge(Node node) {

        if (node.flag == 0)
            node.flag = 1;
        if (node.flag == 2)
            return node;
        for (int i = 0; i < node.friends.size(); i++) {

            if (node.friends.get(i).flag == 0 || node.friends.get(i).flag == 2) {
                Node tmp = judge(node.friends.get(i));
                if (tmp != null)
                    return tmp;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {

            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            Node[] nodes = new Node[k + 2];
            nodes[0] = new Node(0, 0, 0);
            nodes[k + 1] = new Node(0, 0, 0);
            nodes[k + 1].flag = 2;
            for (int j = 1; j <= k; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int s = in.nextInt();
                nodes[j] = new Node(x, y, s);
                if (x - s <= 0 || y + s >= m) {
                    nodes[0].addFriends(nodes[j]);
                    nodes[j].addFriends(nodes[0]);
                }
                if (x + s >= n || y - s <= 0) {
                    nodes[k + 1].addFriends(nodes[j]);
                    nodes[j].addFriends(nodes[k + 1]);
                }

                for (int l = 1; l < j; l++) {
                    if ((nodes[l].x - nodes[j].x) * (nodes[l].x - nodes[j].x) +
                            (nodes[l].y - nodes[j].y) * (nodes[l].y - nodes[j].y) -
                            (nodes[l].r + nodes[j].r) * (nodes[l].r + nodes[j].r) <= 0) {
                        nodes[l].addFriends(nodes[j]);
                        nodes[j].addFriends(nodes[l]);

                    }
                }
            }

            if (judge(nodes[0]) == null)
                System.out.println("Yes");
            else System.out.println("No");
        }
    }
}

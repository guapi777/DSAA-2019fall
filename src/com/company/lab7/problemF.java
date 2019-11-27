package com.company.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problemF {
    static class Node {

        Node leftNode;
        Node rightNode;
        Long value;
        Long height;
        long tag;


        Node(long value, long tag) {
            this.value = value;
            this.tag = tag;
        }


    }

    static class AVLTree {


        Node root;

        Node getMin(Node node) {
            if (node == null) {
                return null;
            } else if (node.leftNode == null) {
                return node;
            }
            return getMin(node.leftNode);
        }

        Node findNext(Node n) {
            Node next = n.rightNode;
            while (next != null && next.leftNode != null)
                next = next.leftNode;
            return next;
        }

        Node findPrev(Node n) {
            Node prev = n.leftNode;
            while (prev != null && prev.rightNode != null)
                prev = prev.rightNode;
            return prev;
        }

        Node succ(long value, Node node) {
            if (node != null) {
                if (node.value < value)
                    return succ(value, node.rightNode);
                if (findPrev(node) != null && value <= findPrev(node).value)
                    return succ(value, node.leftNode);
                else
                    return node;
            }
            return null;
        }

        Node pori(long value, Node node) {
            if (node != null) {
                if (node.value > value)
                    return pori(value, node.leftNode);
                if (findNext(node) != null && value >= findNext(node).value)
                    return pori(value, node.rightNode);
                else
                    return node;
            }
            return node;

        }


        long getHeight(Node node) {
            if (node == null) {
                return -1;
            }
            return node.height;
        }

        void insert(Long data, Long tag) {
            this.root = insert(data, root, tag);
        }

        Node insert(Long data, Node node, Long tag) {
            if (node == null) {
                node = new Node(data, tag);
            }
            long answer = data.compareTo(node.value);
            if (answer < 0) {
                node.leftNode = insert(data, node.leftNode, tag);
                if (getHeight(node.leftNode) - getHeight(node.rightNode) == 2) {
                    if (data.compareTo(node.leftNode.value) < 0) {
                        node = LLRotate(node);
                    } else node = LRRotate(node);
                }
            } else if (answer > 0) {
                node.rightNode = insert(data, node.rightNode, tag);
                if (getHeight(node.rightNode) - getHeight(node.leftNode) == 2) {
                    if (data.compareTo(node.rightNode.value) < 0) {
                        node = RLRotate(node);
                    } else node = RRRotate(node);
                }
            }

            node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
            return node;
        }

        void remove(Long value) {
            this.root = remove(value, root);
        }

        Node remove(Long data, Node node) {
            if (node == null)
                return null;
            long answer = data.compareTo(node.value);
            if (answer < 0) {
                node.leftNode = remove(data, node.leftNode);

                if (getHeight(node.rightNode) - getHeight(node.leftNode) == 2) {
                    Node curNode = node.rightNode;
                    if (getHeight(curNode.leftNode) > getHeight(curNode.rightNode)) {
                        node = RLRotate(node);
                    } else node = RRRotate(node);

                }
            } else if (answer > 0) {
                node.rightNode = remove(data, node.rightNode);
                if (getHeight(node.leftNode) - getHeight(node.rightNode) == 2) {
                    Node curNode = node.leftNode;
                    if (getHeight(curNode.rightNode) > getHeight(curNode.leftNode)) {
                        node = LRRotate(node);
                    } else {
                        node = LLRotate(node);
                    }
                }
            } else if (node.rightNode != null && node.leftNode != null) {
                node.value = getMin(node.rightNode).value;
                node.rightNode = remove(node.value, node.rightNode);
            } else {
                node = (node.leftNode != null) ? node.leftNode : node.rightNode;
            }

            if (node != null) {

                node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
            }
            return node;
        }


        Node LLRotate(Node x) {
            Node w = x.leftNode;

            x.leftNode = w.rightNode;

            w.rightNode = x;

            x.height = Math.max(getHeight(x.leftNode), getHeight(x.rightNode)) + 1;
            w.height = Math.max(getHeight(w.leftNode), x.height) + 1;
            return w;
        }

        Node RRRotate(Node w) {
            Node x = w.rightNode;

            w.rightNode = x.leftNode;
            x.leftNode = w;
            x.height = Math.max(getHeight(x.leftNode), w.height) + 1;
            w.height = Math.max(getHeight(w.leftNode), getHeight(w.rightNode)) + 1;
            return x;
        }

        Node LRRotate(Node x) {
            x.leftNode = RRRotate(x.leftNode);
            return LLRotate(x);
        }

        Node RLRotate(Node w) {
            w.rightNode = LLRotate(w.rightNode);
            return RRRotate(w);
        }

    }

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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        AVLTree tree = new AVLTree();
        long ans = 0;
        long times = in.nextLong();
        for (long i = 0; i < times; i++) {
            if (in.nextLong() == 0) {
                long a = in.nextLong();
                if (tree.root == null || tree.root.tag == 0) {
                    tree.insert(a, (long) 0);
                } else {
                    Node tmp1 = tree.pori(a, tree.root);
                    Node tmp2 = tree.succ(a, tree.root);

                    if (tmp1 == null) {
                        ans = ans + tmp2.value - a;
                        tree.remove(tmp2.value);
                        continue;
                    }
                    if (tmp2 == null) {
                        ans = ans + a - tmp1.value;
                        tree.remove(tmp1.value);
                        continue;
                    }
                    if (a - tmp1.value <= tmp2.value - a) {
                        ans = ans + a - tmp1.value;
                        tree.remove(tmp1.value);
                    } else {
                        ans = ans + tmp2.value - a;
                        tree.remove(tmp2.value);
                    }
                }

            } else {
                long a = in.nextLong();
                if (tree.root == null || tree.root.tag == 1) {
                    tree.insert(a, (long) 1);
                } else {
                    Node tmp1 = tree.pori(a, tree.root);
                    Node tmp2 = tree.succ(a, tree.root);

                    if (tmp1 == null) {
                        ans = ans + tmp2.value - a;
                        tree.remove(tmp2.value);
                        continue;
                    }
                    if (tmp2 == null) {
                        ans = ans + a - tmp1.value;
                        tree.remove(tmp1.value);
                        continue;
                    }
                    if (a - tmp1.value <= tmp2.value - a) {
                        ans = ans + a - tmp1.value;
                        tree.remove(tmp1.value);
                    } else {
                        ans = ans + tmp2.value - a;
                        tree.remove(tmp2.value);
                    }
                }
            }
        }
        System.out.print(ans);
    }
}

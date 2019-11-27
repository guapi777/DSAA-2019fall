package com.company.lab7;

public class problemE {

    static class Node {

        Node leftNode;
        Node rightNode;
        Integer value;
        int height;

        Node(int value) {
            this.value = value;
        }

        Node(Node leftNode, Node rightNode, int value, int height) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.value = value;
            this.height = height;
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


        int getHeight(Node node) {
            if (node == null) {
                return -1;
            }
            return node.height;
        }

        Node insert(Integer data, Node node) {
            if (node == null) {
                node = new Node(data);
            }
            int answer = data.compareTo(node.value);
            if (answer < 0) {
                node.leftNode = insert(data, node.leftNode);
                if (getHeight(node.leftNode) - getHeight(node.rightNode) == 2) {
                    if (data.compareTo(node.leftNode.value) < 0) {
                        node = LLRotate(node);
                    } else node = LRRotate(node);
                }
            }
            if (answer > 0) {
                node.rightNode = insert(data, node.rightNode);
                if (getHeight(node.leftNode) - getHeight(node.rightNode) == -2) {
                    if (data.compareTo(node.rightNode.value) < 0) {
                        node = RLRotate(node);
                    } else node = RRRotate(node);
                }
            }

            node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
            return node;
        }

        Node remove(Integer data, Node node) {
            if (node == null)
                return null;
            int answer = data.compareTo(node.value);
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
                if (getHeight(node.rightNode) - getHeight(node.leftNode) == -2) {
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

                node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode));
            }
            return node;
        }



        Node LLRotate(Node a) {
            Node b = a.leftNode;

            a.leftNode = b.rightNode;
            b.rightNode = a;

            a.height = Math.max(getHeight(a.leftNode), getHeight(a.rightNode)) + 1;
            b.height = Math.max(getHeight(b.leftNode), b.height) + 1;

            return b;
        }

        Node RRRotate(Node a) {
            Node b = a.rightNode;

            a.rightNode = b.leftNode;
            b.leftNode = a;

            a.height = Math.max(getHeight(a.leftNode), getHeight(a.rightNode)) + 1;
            b.height = Math.max(getHeight(b.leftNode), b.height) + 1;

            return b;
        }

        Node LRRotate(Node a) {
            a.leftNode = RRRotate(a.leftNode);
            return LLRotate(a);
        }

        Node RLRotate(Node a) {
            a.rightNode = LLRotate(a.rightNode);
            return RRRotate(a);
        }

    }

    public static void main(String[] args) {

    }
}

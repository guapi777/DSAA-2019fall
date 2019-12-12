package com.company.lab8;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static class ENode {
        int ivex;       // 该边所指向的顶点的位置
        int weight;     // 该边的权
        ENode nextEdge; // 指向下一条弧的指针
    }

    // 邻接表中表的顶点
    private static class VNode {
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    }

    private int mEdgNum;    // 边的数量
    private VNode[] mVexs;  // 顶点数组

    private Main(char[] vexs, EData[] edges) {

        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;
        int elen = edges.length;

        // 初始化"顶点"
        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        // 初始化"边"
        mEdgNum = elen;
        for (EData edge : edges) {
            // 读取边的起始顶点和结束顶点
            char c1 = edge.start;
            char c2 = edge.end;
            int weight = edge.weight;

            // 读取边的起始顶点和结束顶点
            int p1 = getPosition(c1);
            int p2 = getPosition(c2);
            // 初始化node1
            ENode node1 = new ENode();
            node1.ivex = p2;
            node1.weight = weight;
            // 将node1链接到"p1所在链表的末尾"
            if (mVexs[p1].firstEdge == null)
                mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);
            // 初始化node2
            ENode node2 = new ENode();
            node2.ivex = p1;
            node2.weight = weight;
            // 将node2链接到"p2所在链表的末尾"
            if (mVexs[p2].firstEdge == null)
                mVexs[p2].firstEdge = node2;
            else
                linkLast(mVexs[p2].firstEdge, node2);
        }
    }

    /*
     * 将node节点链接到list的最后
     */
    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while (p.nextEdge != null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    /*
     * 返回ch位置
     */
    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++)
            if (mVexs[i].data == ch)
                return i;
        return -1;
    }

    /*
     * 读取一个输入字符
     */
    private char readChar() {
        char ch = '0';

        do {
            try {
                ch = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')));

        return ch;
    }

    /*
     * 读取一个输入字符
     */
    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /*
     * 深度优先搜索遍历图的递归实现
     */
    private void DFS(int i, boolean[] visited) {
        ENode node;

        visited[i] = true;
        System.out.printf("%c ", mVexs[i].data);
        node = mVexs[i].firstEdge;
        while (node != null) {
            if (!visited[node.ivex])
                DFS(node.ivex, visited);
            node = node.nextEdge;
        }
    }

    // 边的结构体
    private static class EData {
        char start; // 边的起点
        char end;   // 边的终点
        int weight; // 边的权重

        EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

    }
}

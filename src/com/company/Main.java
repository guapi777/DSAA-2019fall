package com.company;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static class CommonTreeNode {
        char val;
        List<CommonTreeNode> children;

        CommonTreeNode(char val) {
            this.val = val;
            children = new LinkedList<>();
        }

        void addChildren(CommonTreeNode... children) {
            Collections.addAll(this.children, children);
        }
    }

    // 3.2所述的解法
    private static CommonTreeNode getLastParent2(CommonTreeNode root, CommonTreeNode node1, CommonTreeNode node2) {
        List<CommonTreeNode> path1 = new ArrayList<>();
        List<CommonTreeNode> path2 = new ArrayList<>();
        getPath(root, node1, path1);
        getPath(root, node2, path2);
        CommonTreeNode lastParent = null;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) == path2.get(i))
                lastParent = path1.get(i);
            else
                break;
        }
        return lastParent;
    }


    private static boolean getPath(CommonTreeNode root, CommonTreeNode node, List<CommonTreeNode> curPath) {
        if (root == node)
            return true;
        curPath.add(root);
        for (CommonTreeNode child : root.children) {
            if (getPath(child, node, curPath))
                return true;
        }
        curPath.remove(curPath.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        CommonTreeNode root = new CommonTreeNode('A');
        CommonTreeNode b = new CommonTreeNode('B');
        CommonTreeNode c = new CommonTreeNode('C');
        CommonTreeNode d = new CommonTreeNode('D');
        CommonTreeNode e = new CommonTreeNode('E');
        CommonTreeNode f = new CommonTreeNode('F');
        CommonTreeNode g = new CommonTreeNode('G');
        CommonTreeNode h = new CommonTreeNode('H');
        CommonTreeNode i = new CommonTreeNode('I');
        CommonTreeNode j = new CommonTreeNode('J');
        root.addChildren(b, c);
        b.addChildren(d, e);
        d.addChildren(f, g);
        e.addChildren(h, i, j);
        System.out.println(getLastParent2(root, f, h).val);
        System.out.println(getLastParent2(root, h, i).val);

    }
}

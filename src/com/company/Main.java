package com.company;


public class Main {
    static long getSum(long num) {
        long answer = 0;
        char[] chars = (num + "").toCharArray();
        for (char aChar : chars) {
            answer += aChar - '0';
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(getSum(0));

    }
}
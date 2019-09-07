package com.company;

public class Main {


    public static void main(String[] args) {
        String string = "(111,2342)";
        System.out.println(string.substring(string.indexOf(",") + 1, string.indexOf(")")));
    }
}




package com.company.Lab0;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class problemB {
    private static boolean isNumeric(char ch) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(ch + "");
        return isNum.matches();
    }

    private static int judgeXY(int xy) {
        if (xy < 3) {
            return 0;
        } else if (xy < 6) {
            return 3;
        } else return 6;
    }

    private static int[][] sort(int[][] array) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (array[i][j] == 0) {
                    int heng = 0;
                    int shu = 0;
                    int square = 0;
                    int sum = 0;
                    for (int k = judgeXY(j); k < judgeXY(j) + 3; k++) {
                        for (int l = judgeXY(i); l < judgeXY(i) + 3; l++) {
                            if (array[l][k] == 0) {
                                square++;
                            }
                        }
                    }
                    for (int k = 0; k < 9; k++) {
                        if (array[i][k] == 0) {
                            heng++;
                        }
                        if (array[k][j] == 0) {
                            shu++;
                        }
                    }
                    if (heng == 1) {
                        for (int k = 0; k < 9; k++) {
                            sum += array[i][k];
                        }
                        array[i][j] = 45 - sum;
                        sum = 0;
                    }
                    if (shu == 1 && heng != 1) {
                        for (int k = 0; k < 9; k++) {
                            sum += array[k][j];
                        }
                        array[i][j] = 45 - sum;
                        sum = 0;
                    }
                    if (square == 1 && shu != 1 && heng != 1) {
                        for (int k = judgeXY(j); k < judgeXY(j) + 3; k++) {
                            for (int l = judgeXY(i); l < judgeXY(i) + 3; l++) {
                                sum += array[l][k];
                            }
                        }
                        array[i][j] = 45 - sum;
                    }

                }
            }
        }

        return array;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] array = new int[9][9];
        int[][] array2;
        for (int i = 0; i < 9; i++) {
            while (scanner.hasNextLine()) {
                char[] chars = scanner.nextLine().toCharArray();
                if (chars.length != 0) {
                    for (int j = 0; j < 9; j++) {
                        for (char aChar : chars) {
                            if (isNumeric(aChar)) {
                                array[i][j] = aChar - '0';
                                j++;
                            } else if (aChar == 'x') {
                                array[i][j] = 0;
                                j++;
                            }
                        }
                        i++;
                    }
                }
            }
        }
        array2 = sort(array);
        array = sort(array2);
        array2 = sort(array);
        array = sort(array2);
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (array[i][j] == 0) {
                    x = 1;
                    break;
                }
            }
        }
        if (x == 1) {
            System.out.print("The test data is incorrect!");
        } else {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(array[i][j] + " ");
                    if (j % 3 == 2) {
                        System.out.print("| ");
                    }
                }
                if (i == 2) {
                    System.out.println();
                } else if (i == 5) {
                    System.out.println();
                }
                if (i < 8) {
                    System.out.println();
                }
            }
        }
    }
}


package ru.algo.ya.c1.hw8;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/28069/problems/A/
Высота дерева
*/
public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Tree tree = new Tree();
            int max = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                int val = nums[i];
                int level = append(tree, val);
                max = Math.max(max, level);
            }
            System.out.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int append(Tree tree, int val) {
        int level = 1;
        if (tree.val == val) return level;
        if (tree.val == 0) {
            tree.val = val;
        } else if (val < tree.val) {
            if (tree.left == null) {
                tree.left = new Tree(val);
                level++;
            } else {
                level += append(tree.left, val);
            }
        } else {
            if (tree.right == null) {
                tree.right = new Tree(val);
                level++;
            } else {
                level += append(tree.right, val);
            }
        }
        return level;
    }

    @Test
    public void test_1() {
        provideConsoleInput("7 3 2 1 9 5 4 6 8 0\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Tree {
        int val;
        Tree left;
        Tree right;

        public Tree(int val) {
            this.val = val;
        }

        public Tree() {
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Tree getLeft() {
            return left;
        }

        public void setLeft(Tree left) {
            this.left = left;
        }

        public Tree getRight() {
            return right;
        }

        public void setRight(Tree right) {
            this.right = right;
        }
    }


}

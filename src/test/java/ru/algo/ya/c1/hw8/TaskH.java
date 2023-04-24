package ru.algo.ya.c1.hw8;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/28069/problems/H/
АВЛ-сбалансированность

  	                    Все языки 	Python 3.6
Ограничение времени 	2 секунды 	4 секунды
Ограничение памяти 	    64Mb 	    256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дерево называется АВЛ-сбалансированным, если для любой его вершины высота левого и правого поддерева для этой вершины
различаются не более чем на 1.

Формат ввода
Вводится последовательность целых чисел, оканчивающаяся нулем. Сам ноль в последовательность не входит. Постройте
дерево, соответствующее данной последовательности.

Формат вывода
Определите, является ли дерево сбалансированным, выведите слово YES или NO.

*/
public class TaskH extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Tree tree = Tree.createTree(nums);
            try {
                int l = dfs(tree, 1);
                System.out.println("YES");
            } catch (RuntimeException e) {
                System.out.println("NO");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int dfs(Tree tree, int level) {
        if (tree == null) return level;
        int l = dfs(tree.left, level + 1);
        int r = dfs(tree.right, level + 1);
        if (Math.abs(r - l) <= 1) {
            return Math.max(l, r);
        } else {
            throw new RuntimeException();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("7 3 2 1 9 5 4 6 8 0\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_101() {
        provideConsoleInput("7 3 2 1 5 4 6 0\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_102() {
        provideConsoleInput("7 0\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_103() {
        provideConsoleInput("7 5 9 0\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_104() {
        provideConsoleInput("7 5 4 9 0\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_105() {
        provideConsoleInput("7 5 4 3 9 0\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Tree {
        Integer val;
        Tree left;
        Tree right;

        public Tree(int val) {
            this.val = val;
        }

        public Tree() {
        }

        public static Tree createTree(int[] nums) {
            Tree tree = new Tree();
            for (int i = 0; i < nums.length - 1; i++) {
                append(tree, nums[i]);
            }
            return tree;
        }

        private static void append(Tree tree, int val) {
            if (tree.val == null) {
                tree.val = val;
            } else if (tree.val == val) return;
            else if (val < tree.val) {
                if (tree.left == null) {
                    tree.left = new Tree(val);
                } else {
                    append(tree.left, val);
                }
            } else {
                if (tree.right == null) {
                    tree.right = new Tree(val);
                } else {
                    append(tree.right, val);
                }
            }
        }
    }
}

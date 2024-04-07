package algo.c1.hw8;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/28069/problems/A/
Высота дерева

                        Все языки 	Python 3.6
Ограничение времени 	2 секунды 	4 секунды
Ограничение памяти 	    64Mb 	    256Mb
Ввод 	                стандартный ввод или input.txt
Вывод 	                стандартный вывод или output.txt

Реализуйте бинарное дерево поиска для целых чисел. Программа получает на вход последовательность целых чисел и строит из
них дерево. Элементы в деревья добавляются в соответствии с результатом поиска их места. Если элемент уже существует в
дереве, добавлять его не надо. Балансировка дерева не производится.

Формат ввода
На вход программа получает последовательность натуральных чисел. Последовательность завершается числом 0, которое
означает конец ввода, и добавлять его в дерево не надо.

Формат вывода
Выведите единственное число – высоту получившегося дерева.

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
                int level = append(tree, val, 1);
                max = Math.max(max, level);
            }
            System.out.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int append(Tree tree, int val, int level) {
        if (tree.val == val) return level;
        if (tree.val == 0) {
            tree.val = val;
        } else if (val < tree.val) {
            if (tree.left == null) {
                tree.left = new Tree(val);
                level++;
            } else {
                level = append(tree.left, val, level + 1);
            }
        } else {
            if (tree.right == null) {
                tree.right = new Tree(val);
                level++;
            } else {
                level = append(tree.right, val, level + 1);
            }
        }
        return level;
    }

    @Test
    public void test_01() {
        provideConsoleInput("7 3 2 1 9 5 4 6 8 0\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
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

    }

}

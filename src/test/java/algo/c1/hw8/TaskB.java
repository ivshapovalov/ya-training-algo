package algo.c1.hw8;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28069/problems/B/
Глубина добавляемых элементов

                        Все языки 	Python 3.6
Ограничение времени 	2 секунды 	4 секунды
Ограничение памяти 	    64Mb 	    256Mb
Ввод 	                стандартный ввод или input.txt
Вывод 	                стандартный вывод или output.txt

В бинарное дерево поиска добавляются элементы. Выведите глубину для каждого добавленного элемента в том порядке, как они
добавлялись. Если элемент уже есть в дереве, то ничего добавлять и выводить не нужно. Глубиной называется расстояние от
корня дерева до элемента включительно.

Формат ввода
Вводится последовательность целых чисел, оканчивающаяся нулем. Сам ноль в последовательность не входит. По данной
последовательности требуется построить дерево.

Формат вывода
Выведите ответ на задачу.

*/
public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Tree tree = new Tree();
            int max = 0;
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < nums.length - 1; i++) {
                int val = nums[i];
                int level = append(tree, val, 1);
                if (level != 0) {
                    result.add(level);
                }
            }
            System.out.println(result.stream().map(el -> String.valueOf(el)).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int append(Tree tree, int val, int level) {
        if (tree.val == null) {
            tree.val = val;
        } else if (tree.val == val) return 0;
        else if (val < tree.val) {
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
        String expected = "1 2 3 4 2 3 4 4 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_101() {
        provideConsoleInput("7 3 3 3 3 2 1 9 9 9 9 5 4 6 8 0\n");
        main(new String[0]);
        String expected = "1 2 3 4 2 3 4 4 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_102() {
        provideConsoleInput("1 1 1 0\n");
        main(new String[0]);
        String expected = "1\n";
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
    }
}

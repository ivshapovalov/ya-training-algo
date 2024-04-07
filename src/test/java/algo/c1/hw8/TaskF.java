package algo.c1.hw8;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/28069/problems/F/
Вывод развилок

  	                    Все языки 	Python 3.6
Ограничение времени 	2 секунды 	4 секунды
Ограничение памяти 	    64Mb 	    256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Для полученного дерева выведите список всех вершин, имеющих по два ребёнка, в порядке возрастания.

Формат ввода
Вводится последовательность целых чисел, оканчивающаяся нулем. Сам ноль в последовательность не входит. Постройте по этой последовательности дерево.

Формат вывода
Выведите ответ задачи.

*/
public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Tree tree = Tree.createTree(nums);
            List<Integer> result = new ArrayList<>();
            Queue<Tree> q = new LinkedList<>();
            q.offer(tree);
            while (!q.isEmpty()) {
                Tree current = q.poll();
                if (current.left != null && current.right != null) {
                    result.add(current.val);
                }
                if (current.left != null) {
                    q.offer(current.left);
                }
                if (current.right != null) {
                    q.offer(current.right);
                }
            }
            Collections.sort(result, Comparator.naturalOrder());

            result.stream()
                    .sorted(Comparator.naturalOrder())
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("7 3 2 1 9 5 4 6 8 0\n");
        main(new String[0]);
        String expected = "3\n" +
                "5\n" +
                "7\n";
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
            } else if (tree.val == val) {
            }
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

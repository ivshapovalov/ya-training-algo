package ru.algo.ya.c2.b.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29188/problems/A/
Быстрый поиск в массиве

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан массив из N целых чисел. Все числа от −109 до 109.

Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения отL доR?”.

Формат ввода
Число N (1≤N≤105). Далее N целых чисел.
Затем число запросов K (1≤K≤105).
Далее K пар чисел L,R (−109≤L≤R≤109) — собственно запросы.

Формат вывода
Выведите K чисел — ответы на запросы.

 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Arrays.sort(nums);
            int queryAmount = Integer.parseInt(reader.readLine().trim());
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < queryAmount; i++) {
                int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int from = input[0];
                int to = input[1];
                int interval = findFirst(new CheckGreater(), nums, 0, nums.length - 1, to) -
                        findFirst(new CheckGreaterOrEquals(), nums, 0, nums.length - 1, from);
                result.append(" ").append(interval);
            }
            System.out.println(result.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findFirst(Check check, int[] arr, int l, int r, int x) {
        int m;
        while (l < r) {
            m = (l + r) / 2;
            if (check(check, m, arr, x)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (!check(check, l, arr, x)) {
            return arr.length;
        }
        return l;
    }

    private static boolean check(Check check, int index, int[] arr, int x) {
        return check.check(index, arr, x);
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "10 1 10 3 4\n" +
                "4\n" +
                "1 10\n" +
                "2 9\n" +
                "3 4\n" +
                "2 2\n");
        main(new String[0]);
        String expected = "5 2 2 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


    @Test
    public void test_f007() throws FileNotFoundException {
        String testNumber = "007";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    interface Check {
        boolean check(int index, int[] arr, int x);
    }

    static class CheckGreaterOrEquals implements Check {
        @Override
        public boolean check(int index, int[] arr, int x1) {
            return arr[index] >= x1;
        }
    }

    static class CheckGreater implements Check {
        @Override
        public boolean check(int index, int[] arr, int x2) {
            return arr[index] > x2;
        }

    }

}
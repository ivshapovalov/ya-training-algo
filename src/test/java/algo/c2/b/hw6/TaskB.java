package algo.c2.b.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29188/problems/B/
Номер левого и правого вхождения

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Требуется определить в заданном массиве номер самого левого и самого правого элемента, равного искомому числу. Формат
ввода

В первой строке вводится одно натуральное число N, не превосходящее 105: количество чисел в массиве. Во второй строке
вводятся N натуральных чисел, не превосходящих 109, каждое следующее не меньше предыдущего. В третьей строке вводится
количество искомых чисел M – натуральное число, не превосходящее 106. В четвертой строке вводится M натуральных чисел,
не превосходящих 109. Формат вывода

Для каждого запроса выведите в отдельной строке через пробел два числа: номер элемента самого левого и самого правого
элементов массива, равных числу-запросу. Элементы массива нумеруются с единицы.Если в массиве нет такого числа, выведите
в соответствующей строке два нуля, разделенных пробелом.

 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int queryAmount = Integer.parseInt(reader.readLine().trim());
            StringBuilder result = new StringBuilder();
            int[] queries = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            for (int query : queries) {
                int left = findLeft(nums, 0, nums.length - 1, query);
                int right = findRight(nums, 0, nums.length - 1, query);
                result.append(left + " " + right + "\n");

            }
            System.out.println(result.toString().trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findLeft(int[] arr, int l, int r, int x) {
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] >= x) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (arr[l] != x) {
            return 0;
        }
        return l + 1;
    }

    private static int findRight(int[] arr, int l, int r, int x) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (arr[m] <= x) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        if (arr[l] != x) {
            return 0;
        }
        return l + 1;
    }

    @Test
    public void test_01() {
        provideConsoleInput("4\n" +
                "1 2 2 3\n" +
                "4\n" +
                "4 3 2 1\n");
        main(new String[0]);
        String expected = "0 0\n" +
                "4 4\n" +
                "2 3\n" +
                "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10\n" +
                "1 2 3 4 5 6 7 7 8 9\n" +
                "10\n" +
                "7 3 3 1 3 7 9 7 7 10\n");
        main(new String[0]);
        String expected = "7 8\n" +
                "3 3\n" +
                "3 3\n" +
                "1 1\n" +
                "3 3\n" +
                "7 8\n" +
                "10 10\n" +
                "7 8\n" +
                "7 8\n" +
                "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10\n" +
                "1 3 3 3 3 6 8 8 9 10\n" +
                "10\n" +
                "2 9 6 4 2 9 3 7 9 7\n");
        main(new String[0]);
        String expected = "0 0\n" +
                "9 9\n" +
                "6 6\n" +
                "0 0\n" +
                "0 0\n" +
                "9 9\n" +
                "2 5\n" +
                "0 0\n" +
                "9 9\n" +
                "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}

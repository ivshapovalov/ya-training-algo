package algo.c5.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/59542/problems/A/
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
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            int[] nums = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int k = Integer.parseInt(r.readLine());

            Map<Integer, Integer> mapLeft = new HashMap<>();
            Map<Integer, Integer> mapRight = new HashMap<>();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                int[] input = Arrays.stream(r.readLine()
                        .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
                int left = input[0];
                int right = input[1];
                Arrays.sort(nums);

                int indexLeft = 0;
                int indexRight = 0;
                if (mapLeft.containsKey(left)) {
                    indexLeft = mapLeft.get(left);
                } else {
                    indexLeft = binarySearchLeft(nums, left);
                    mapLeft.put(left, indexLeft);
                }
                if (mapRight.containsKey(right)) {
                    indexRight = mapRight.get(right);
                } else {
                    indexRight = binarySearchRight(nums, right);
                    mapRight.put(right, indexRight);
                }
                result.add(indexRight == indexLeft ? (nums[indexLeft] >= left && nums[indexRight] <= right ? 1 : 0) : indexRight - indexLeft + 1);

            }
            System.out.println(result.stream().map(el -> String.valueOf(el)).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearchLeft(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target <= arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static int binarySearchRight(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (arr[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }


    @Test
    public void test_001() {
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
    public void test_002() {
        provideConsoleInput("1\n" +
                "1\n" +
                "3\n" +
                "1 1\n" +
                "-1000000000 -1000000000\n" +
                "1000000000 1000000000\n");
        main(new String[0]);
        String expected = "1 0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("3\n" +
                "-1000000000 0 1000000000\n" +
                "12\n" +
                "-1000000000 -1000000000\n" +
                "1000000000 1000000000\n" +
                "-1000000000 1000000000\n" +
                "-999999999 999999999\n" +
                "-999999999 1000000000\n" +
                "-1000000000 999999999\n" +
                "0 999999999\n" +
                "-1 999999999\n" +
                "1 999999999\n" +
                "-999999999 -1\n" +
                "-999999999 0\n" +
                "-999999999 1\n");
        main(new String[0]);
        String expected = "1 1 3 1 2 2 1 1 0 0 1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

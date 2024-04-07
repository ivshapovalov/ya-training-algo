package algo.open.summer2023.week2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/50242/problems/D/
Majority

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Majority (в дословном переводе "большинство") — это значение элемента, который в массиве длиной n встречается более чем n / 2 раз. Определите majority массива, если гарантируется, что такой элемент существует.

Формат ввода
В первой строке вводится число n (1 ≤ n ≤ 5 × 104).
Во второй строке вводится n чисел через пробел, числа не превосходят 109 по модулю.

Формат вывода
Выведите majority массива.
 */

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Map<Integer, Integer> freq = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int num = input[i];
                freq.compute(num, ((k, v) -> (v == null) ? 1 : v + 1));
            }

            Integer result =
                    freq.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).map(e -> e.getKey()).findFirst().get();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "1 2 1");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("7\n" +
                "7 5 5 5 5 4 5");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n" +
                "3 3 3 1");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

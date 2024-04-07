package algo.open.summer2023.week3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/50340/problems/A/
Количество положительных на отрезке

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дана последовательность чисел и запросы вида "определите сколько положительных чисел на отрезке с индексами от L до R".
Формат ввода
В первой строке вводится число n (1 ≤ n ≤ 100000) — длина последовательности.
Во второй строке вводится последовательность из n целых чисел, все числа по модулю не превосходят 100000. Нумерация в последовательности начинается с единицы.
В первой строке вводится число q (1 ≤ q ≤ 100000) — количество запросов.
В каждой из следующих q строк вводятся запросы — два индекса l и r (1 ≤ l ≤ r ≤ n)

Формат вывода
Для каждого запроса выведите количество положительных на отрезке.
 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int[] prefix = new int[n + 1];
            prefix[0] = 0;
            for (int j = 0; j < n; j++) {
                prefix[j + 1] = prefix[j] + (nums[j] > 0 ? 1 : 0);
            }
            int q = Integer.parseInt(reader.readLine().trim());
            for (int j = 0; j < q; j++) {
                int[] interval = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int res = prefix[interval[1]] - prefix[interval[0]] + (nums[interval[0] - 1] > 0 ? 1 : 0);
                System.out.println(res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "2 -1 2 -2 3\n" +
                "4\n" +
                "1 1\n" +
                "1 3\n" +
                "2 4\n" +
                "1 5");
        main(new String[0]);
        String expected = "1\n" +
                "2\n" +
                "1\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

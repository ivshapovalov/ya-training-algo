package algo.open.summer2023.week3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
https://contest.yandex.ru/contest/50340/problems/C/
Гирлянды

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На завод по изготовлению гирлянд пришел срочный заказ: изготовить как можно больше одинаковых гирлянд, состоящих из n
лампочек. На складе завода есть лампочки k различных цветов, цвета занумерованы от 1 до k. Определите, сколько гирлянд
сможет изготовить завод и из каких лампочек должна состоять каждая гирлянда. Формат ввода

В первой строке задаются два числа n (1 ≤ n ≤ 40000) и k (1 ≤ k ≤ 50000).

В следующих k строках задается количество лампочек каждого из k цветов. Суммарное количество лампочек на складе не
превосходит 2 × 109. Формат вывода

В первой строке выведите максимальное количество гирлянд. В следующих n строках выведите описание гирлянды: в каждой
строке выведите номер цвета лампочки, находящейся в гирлянде на очередном месте. Если возможных ответов несколько —
выведите любой из них.
 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int k = input[1];
            int[] colors = new int[k + 1];
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < k + 1; j++) {
                int amount = Integer.parseInt(reader.readLine().trim());
//                if (amount<n) continue;
                colors[j] = amount;
                min = Math.min(amount, min);
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i < k + 1; i++) {
                int amount = colors[i] / min;
                res.addAll(Collections.nCopies(amount, i));
            }
            System.out.println(min);
            res.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 4\n" +
                "3\n" +
                "3\n" +
                "2\n" +
                "1\n");
        main(new String[0]);
        String expected = "2\n" +
                "1\n" +
                "2\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

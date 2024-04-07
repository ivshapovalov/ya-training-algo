package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/59541/problems/D/
Повторяющееся число

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вам дана последовательность измерений некоторой величины. Требуется определить, повторялась ли какое-либо число, причём
расстояние между повторами не превосходило k.

Формат ввода
В первой строке задаются два числа n и k (1 ≤ n, k ≤ 105).

Во второй строке задаются n чисел, по модулю не превосходящих 109.

Формат вывода
Выведите YES, если найдется повторяющееся число и расстояние между повторами не превосходит k и NO в противном случае.

*/

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int n = input[0];
            int k = input[1];
            int[] nums = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (map.containsKey(num)) {
                    Integer prev = map.get(num);
                    if (i - prev <= k) {
                        System.out.println("YES");
                        return;
                    } else {
                        map.put(num, i);
                    }
                } else {
                    map.put(num, i);
                }
            }
            System.out.println("NO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("4 2\n" +
                "1 2 3 1\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("4 1\n" +
                "1 0 1 1");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("6 2\n" +
                "1 2 3 1 2 3");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/45468/problems/30/
НОП с восстановлением ответа

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Даны две последовательности, требуется найти и вывести их наибольшую общую подпоследовательность.

Формат ввода
В первой строке входных данных содержится число N – длина первой последовательности (1 ≤ N ≤ 1000). Во второй строке
заданы члены первой последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

В третьей строке записано число M – длина второй последовательности (1 ≤ M ≤ 1000). В четвертой строке задаются члены
второй последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

Формат вывода
Требуется вывести наибольшую общую подпоследовательность данных последовательностей, через пробел.

*/
public class Task30 extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[] sequence1Origin = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int[] sequence1 = new int[n + 1];
            System.arraycopy(sequence1Origin, 0, sequence1, 1, sequence1Origin.length);
            int m = Integer.valueOf(reader.readLine().trim());
            int[] sequence2Origin = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int[] sequence2 = new int[m + 1];
            System.arraycopy(sequence2Origin, 0, sequence2, 1, sequence2Origin.length);

            int[][] dp = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    dp[i][j] = 0;
                }
            }

            //for (let s1 = 1; s1 < sequence1.length; ++s1) {
            //  for (let s2 = 1; s2 < sequence2.length; ++s2) {
            //    if (sequence1[s1] === sequence2[s2]) {
            //      matrix[s1][s2] = matrix[s1 - 1][s2 - 1] + 1;
            //    } else {
            //      matrix[s1][s2] = Math.max(matrix[s1 - 1][s2], matrix[s1][s2 - 1]);
            //    }
            //  }
            //}

            for (int s1 = 1; s1 < sequence1.length; s1++) {
                for (int s2 = 1; s2 < sequence2.length; s2++) {
                    if (sequence1[s1] == sequence2[s2]) {
                        dp[s1][s2] = dp[s1 - 1][s2 - 1] + 1;
                    } else {
                        dp[s1][s2] = Math.max(dp[s1 - 1][s2], dp[s1][s2 - 1]);
                    }
                }
            }

            String output = buildSequence(dp, sequence1, sequence2, sequence1.length - 1, sequence2.length - 1).trim();
            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static String buildSequence(int[][] dp, int[] sequence1, int[] sequence2, int lastIndex1, int lastIndex2) {
        if (dp[lastIndex1][lastIndex2] == 0) {
            return "";
        } else if (sequence1[lastIndex1] == sequence2[lastIndex2]) {
            return buildSequence(dp, sequence1, sequence2, lastIndex1 - 1, lastIndex2 - 1) + ' ' + sequence1[lastIndex1];
        } else if (dp[lastIndex1 - 1][lastIndex2] < dp[lastIndex1][lastIndex2 - 1]) {
            return buildSequence(dp, sequence1, sequence2, lastIndex1, lastIndex2 - 1);
        } else {
            return buildSequence(dp, sequence1, sequence2, lastIndex1 - 1, lastIndex2);
        }
    }


    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "1 2 3\n" +
                "3 \n" +
                "2 3 1\n");
        main(new String[0]);
        String expected = "2 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "1 2 3\n" +
                "3 \n" +
                "3 2 1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3\n" +
                "1 2 3\n" +
                "3 \n" +
                "1001 1002 1003");
        main(new String[0]);
        String expected = "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

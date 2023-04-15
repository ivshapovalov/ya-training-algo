package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/45468/problems/21/
Три единицы подряд

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

По данному числу N определите количество последовательностей из нулей и единиц длины N, в которых никакие три единицы не стоят рядом.
Формат ввода

Во входном файле написано натуральное число N, не превосходящее 35.
Формат вывода

Выведите количество искомых последовательностей. Гарантируется, что ответ не превосходит 231-1.
*/
public class Task21 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[] dp = new int[Math.max(4, n + 1)];
            dp[0] = 0;
            dp[1] = 2;
            dp[2] = 4;
            dp[3] = 7;
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
            System.out.print(dp[n] + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n");
        main(new String[0]);
        String expected = "24\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

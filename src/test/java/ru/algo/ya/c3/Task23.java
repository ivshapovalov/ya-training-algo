package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://contest.yandex.ru/contest/45468/problems/23/
Калькулятор

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Имеется калькулятор, который выполняет следующие операции:

    умножить число X на 2; умножить число X на 3; прибавить к числу X единицу.

Определите, какое наименьшее количество операций требуется, чтобы получить из числа 1 число N.

Формат ввода
Во входном файле написано натуральное число N, не превосходящее 106.

Формат вывода
В первой строке выходного файла выведите минимальное количество операций. Во второй строке выведите числа,
последовательно получающиеся при выполнении операций. Первое из них должно быть равно 1, а последнее N. Если решений
несколько, выведите любое.

*/
public class Task23 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[] dp = new int[n + 1];
            String[] path = new String[n + 1];
            Arrays.fill(path, "");
            dp[1] = 0;
            path[1] = "1";
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{1, 0});
            while (!q.isEmpty()) {
                int[] coord = q.poll();
                int num = coord[0];
                int step = coord[1];
                if (num == n) break;
                int m3 = num * 3;
                if (m3 <= n) {
                    if (dp[m3] == 0 || dp[m3] > step + 1) {
                        dp[m3] = step + 1;
                        path[m3] = path[num] + " " + m3;
                        q.add(new int[]{m3, step + 1});
                    }
                }

                int m2 = num * 2;
                if (m2 <= n) {
                    if (dp[m2] == 0 || dp[m2] > step + 1) {
                        dp[m2] = step + 1;
                        path[m2] = path[num] + " " + m2;
                        q.add(new int[]{m2, step + 1});
                    }
                }

                int p1 = num + 1;
                if (p1 <= n) {
                    if (dp[p1] == 0 || dp[p1] > step + 1) {
                        dp[p1] = step + 1;
                        path[p1] = path[num] + " " + p1;
                        q.add(new int[]{p1, step + 1});
                    }
                }

            }
            System.out.print(dp[n] + "\n");
            System.out.print(path[n] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n");
        main(new String[0]);
        String expected = "0\n" +
                "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n");
        main(new String[0]);
        String expected = "3\n" +
                "1 3 4 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

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
https://contest.yandex.ru/contest/45468/problems/26/
Самый дешевый путь

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В каждой клетке прямоугольной таблицы N×M записано некоторое число. Изначально игрок находится в левой верхней клетке.
За один ход ему разрешается перемещаться в соседнюю клетку либо вправо, либо вниз (влево и вверх перемещаться
запрещено). При проходе через клетку с игрока берут столько килограммов еды, какое число записано в этой клетке (еду
берут также за первую и последнюю клетки его пути).

Требуется найти минимальный вес еды в килограммах, отдав которую игрок может попасть в правый нижний угол.

Формат ввода
Вводятся два числа N и M — размеры таблицы (1≤N≤20, 1≤M≤20). Затем идет N строк по M чисел в каждой — размеры штрафов в
килограммах за прохождение через соответствующие клетки (числа от 0 до 100).

Формат вывода
Выведите минимальный вес еды в килограммах, отдав которую можно попасть в правый нижний угол.
*/
public class Task26 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            int[][] mat = new int[n + 2][m + 2];
            int[][] dp = new int[n + 2][m + 2];
            for (int i = 1; i <= n; i++) {
                int[] row = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                System.arraycopy(row, 0, mat[i], 1, row.length);
                Arrays.fill(dp[i], -1);
            }
            dp[1][1] = mat[1][1];
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{1, 1});
            while (!queue.isEmpty()) {
                int[] coords = queue.poll();
                int row = coords[0];
                int col = coords[1];
                if (row == n && col == m) break;
                if (row == n + 1 || col == m + 1) continue;
                int down = dp[row][col] + mat[row + 1][col];
                if (dp[row + 1][col] == -1 || dp[row + 1][col] > down) {
                    dp[row + 1][col] = down;
                    queue.add(new int[]{row + 1, col});
                }
                int right = dp[row][col] + mat[row][col + 1];
                if (dp[row][col + 1] == -1 || dp[row][col + 1] > right) {
                    dp[row][col + 1] = right;
                    queue.add(new int[]{row, col + 1});
                }
            }
            System.out.print(dp[n][m] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5 5\n" +
                "1 1 1 1 1\n" +
                "3 100 100 100 100\n" +
                "1 1 1 1 1\n" +
                "2 2 2 2 1\n" +
                "1 1 1 1 1\n");
        main(new String[0]);
        String expected = "11\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

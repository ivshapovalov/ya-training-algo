package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://contest.yandex.ru/contest/45468/problems/27/
Вывести маршрут максимальной стоимости

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В левом верхнем углу прямоугольной таблицы размером N×M находится черепашка. В каждой клетке таблицы записано некоторое
число. Черепашка может перемещаться вправо или вниз, при этом маршрут черепашки заканчивается в правом нижнем углу
таблицы.

Подсчитаем сумму чисел, записанных в клетках, через которую проползла черепашка (включая начальную и конечную клетку).
Найдите наибольшее возможное значение этой суммы и маршрут, на котором достигается эта сумма.

Формат ввода
В первой строке входных данных записаны два натуральных числа N и M, не превосходящих 100 — размеры таблицы. Далее идет
N строк, каждая из которых содержит M чисел, разделенных пробелами — описание таблицы. Все числа в клетках таблицы целые
и могут принимать значения от 0 до 100.

Формат вывода
Первая строка выходных данных содержит максимальную возможную сумму, вторая — маршрут, на котором достигается эта сумма.
Маршрут выводится в виде последовательности, которая должна содержать N-1 букву D, означающую передвижение вниз и M-1
букву R, означающую передвижение направо. Если таких последовательностей несколько, необходимо вывести ровно одну
(любую) из них.

*/
public class Task27 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            int[][] mat = new int[n + 2][m + 2];
            int[][] dp = new int[n + 2][m + 2];
            StringBuilder[][] path = new StringBuilder[n + 2][m + 2];
            for (int i = 1; i <= n; i++) {
                int[] row = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                System.arraycopy(row, 0, mat[i], 1, row.length);
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                    path[i][j] = new StringBuilder();
                }
            }
            dp[1][1] = mat[1][1];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{1, 1});
            while (!q.isEmpty()) {
                int[] coord = q.poll();
                int row = coord[0];
                int col = coord[1];
                if (row == n && col == m) break;
                if (row == n + 1 || col == m + 1) continue;
                int down = dp[row][col] + mat[row + 1][col];
                if (dp[row + 1][col] == Integer.MAX_VALUE || dp[row + 1][col] <= down) {
                    dp[row + 1][col] = down;
                    path[row + 1][col] = new StringBuilder(path[row][col]).append("D ");
                    q.add(new int[]{row + 1, col});
                }
                int right = dp[row][col] + mat[row][col + 1];
                if (dp[row][col + 1] == Integer.MAX_VALUE || dp[row][col + 1] <= right) {
                    dp[row][col + 1] = right;
                    path[row][col + 1] = new StringBuilder(path[row][col]).append("R ");
                    q.add(new int[]{row, col + 1});
                }
            }

            System.out.print(dp[n][m] + "\n");
            String way = (path[n][m] == null || path[n][m].equals("")) ? ""
                    : path[n][m].substring(0, path[n][m].length() - 1);
            System.out.print(way + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5 5\n" +
                "9 9 9 9 9\n" +
                "3 0 0 0 0\n" +
                "9 9 9 9 9\n" +
                "6 6 6 6 8\n" +
                "9 9 9 9 9\n");
        main(new String[0]);
        String expected = "74\n" +
                "D D R R R R D D\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1 1\n" +
                "179\n");
        main(new String[0]);
        String expected = "179\n" +
                "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_12() {
        provideConsoleInput("5 1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0");
        main(new String[0]);
        String expected = "0\n" +
                "D D D D\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://contest.yandex.ru/contest/45468/problems/28/
Ход конем

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дана прямоугольная доска N × M (N строк и M столбцов). В левом верхнем углу находится шахматный конь, которого
необходимо переместить в правый нижний угол доски. В данной задаче конь может перемещаться на две клетки вниз и одну
клетку вправо или на одну клетку вниз и две клетки вправо.

Необходимо определить, сколько существует различных маршрутов, ведущих из левого верхнего в правый нижний угол.

Формат ввода
Входной файл содержит два натуральных числа N и M , .

Формат вывода
В выходной файл выведите единственное число — количество способов добраться конём до правого нижнего угла доски.
*/
public class Task28 extends ContestTask {

    static int n = 0;
    static int m = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] size = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            n = size[0];
            m = size[1];
            long[][] dp = new long[n + 2][m + 2];
            dp[1][1] = 1;
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
            q.offer(new int[]{2, 3});
            q.offer(new int[]{3, 2});
            boolean[][] visited = new boolean[n + 2][m + 2];
            while (!q.isEmpty()) {
                int[] coords = q.poll();
                int row = coords[0];
                int col = coords[1];
                try {
                    if (visited[row][col]) continue;
                    int result = 0;

                    if (row - 1 >= 0 && col - 2 >= 0) {
                        result += dp[row - 1][col - 2];
                    }
                    if (row - 2 >= 0 && col - 1 >= 0) {
                        result += dp[row - 2][col - 1];
                    }

                    dp[row][col] = result;
                    visited[row][col] = true;
                    if (row + 1 <= n && col + 2 <= m) {
                        q.offer(new int[]{row + 1, col + 2});
                    }
                    if (row + 2 <= n && col + 1 <= m) {
                        q.offer(new int[]{row + 2, col + 1});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.print(dp[n][m] + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 2\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("31 34\n");
        main(new String[0]);
        String expected = "293930\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1 1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_18() {
        provideConsoleInput("50 50\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

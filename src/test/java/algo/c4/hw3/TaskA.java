package algo.c4.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
https://contest.yandex.ru/contest/53031/problems/A/
Дейкстра

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан ориентированный взвешенный граф. Найдите кратчайшее расстояние от одной заданной вершины до другой.

Формат ввода
В первой строке содержатся три числа: N, S и F (1≤ N ≤ 100, 1 ≤ S, F ≤ N), где N — количество вершин графа, S —
начальная вершина, а F — конечная. В следующих N строках вводится по N чисел, не превосходящих 100, – матрица смежности
графа, где -1 означает что ребра между вершинами нет, а любое неотрицательное число — наличие ребра данного веса. На
главной диагонали матрицы записаны нули.

Формат вывода
Выведите искомое расстояние или -1, если пути между указанными вершинами не существует.

*/

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" "))
                    .filter(el -> el != null && !el.isEmpty())
                    .mapToInt(el -> Integer.parseInt(el)).toArray();
            int N = input[0];
            int S = input[1];
            int F = input[2];
            int[][] matrix = new int[N + 1][N + 1];
            Integer[] dist = new Integer[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            for (int k = 1; k <= N; k++) {
                int[] rowOrigin = Arrays.stream(r.readLine().trim().split(" "))
                        .filter(el -> el != null && !el.isEmpty())
                        .mapToInt(el -> Integer.parseInt(el)).toArray();
                int[] row = new int[N + 1];
                System.arraycopy(rowOrigin, 0, row, 1, rowOrigin.length);
                matrix[k] = row;
            }
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(S);
            dist[S] = 0;
            while (!queue.isEmpty()) {
                Integer current = queue.poll();
                for (int k = 1; k <= N; k++) {
                    if (k == current) continue;
                    int adj = matrix[current][k];
                    if (adj == -1) continue;
                    if (dist[k] > dist[current] + adj) {
                        dist[k] = dist[current] + adj;
                        queue.add(k);
                    }
                }
            }
            System.out.println(dist[F] == Integer.MAX_VALUE ? -1 : dist[F]);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 2 1\n" +
                "0 1 1\n" +
                "4 0 1\n" +
                "2 1 0\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

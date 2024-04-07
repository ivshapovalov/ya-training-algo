package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/45468/problems/36/
Длина кратчайшего пути

 	                        Все языки 	                Python 3.6
Ограничение времени 	    1 секунда 	                5 секунд
Ограничение памяти      	64Mb 	                    256Mb
Ввод 	                         стандартный ввод или input.txt
Вывод 	                         стандартный вывод или output.txt

В неориентированном графе требуется найти длину минимального пути между двумя вершинами.

Формат ввода
Первым на вход поступает число N – количество вершин в графе (1 ≤ N ≤ 100). Затем записана матрица смежности (0
обозначает отсутствие ребра, 1 – наличие ребра). Далее задаются номера двух вершин – начальной и конечной.

Формат вывода
Выведите L – длину кратчайшего пути (количество ребер, которые нужно пройти).
Если пути нет, нужно вывести -1.

*/
public class Task36 extends ContestTask {
    static int[][] edges;
    static List<Integer> visited;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            edges = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                int[] edge = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                System.arraycopy(edge, 0, edges[i], 1, edge.length);
            }
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int from = input[0];
            int to = input[1];
            Deque<int[]> q = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            q.offer(new int[]{from, 0});
            int result = -1;
            while (!q.isEmpty()) {
                int[] vertex = q.poll();
                int cur = vertex[0];
                int step = vertex[1];
                if (cur == to) {
                    result = step;
                    break;
                }
                visited.add(cur);
                for (int i = 1; i <= n; i++) {
                    if (i != cur && edges[cur][i] == 1 && !visited.contains(i)) {
                        q.offer(new int[]{i, step + 1});
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("10\n" +
                "0 1 0 0 0 0 0 0 0 0\n" +
                "1 0 0 1 1 0 1 0 0 0\n" +
                "0 0 0 0 1 0 0 0 1 0\n" +
                "0 1 0 0 0 0 1 0 0 0\n" +
                "0 1 1 0 0 0 0 0 0 1\n" +
                "0 0 0 0 0 0 1 0 0 1\n" +
                "0 1 0 1 0 1 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 1 0\n" +
                "0 0 1 0 0 0 0 1 0 0\n" +
                "0 0 0 0 1 1 0 0 0 0\n" +
                "5 4\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n" +
                "0 1 0 0 1\n" +
                "1 0 1 0 0\n" +
                "0 1 0 0 0\n" +
                "0 0 0 0 0\n" +
                "1 0 0 0 0\n" +
                "3 5\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

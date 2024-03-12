package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/45468/problems/34/
Топологическая сортировка

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан ориентированный граф. Необходимо построить топологическую сортировку.

Формат ввода
В первой строке входного файла два натуральных числа N и M (1 ≤ N, M ≤ 100000) — количество вершин и рёбер в графе
соответственно. Далее в M строках перечислены рёбра графа. Каждое ребро задаётся парой чисел — номерами начальной и
конечной вершин соответственно.

Формат вывода
Выведите любую топологическую сортировку графа в виде последовательности номеров вершин (перестановка чисел от 1 до N).
Если топологическую сортировку графа построить невозможно, выведите -1.

*/
public class Task34 extends ContestTask {

    private static final Stack<Integer> history = new Stack<>();
    private static Map<Integer, List<Integer>> graph;
    private static int[] visited;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            graph = new HashMap<>();
            visited = new int[n + 1];
            for (int i = 1; i <= m; i++) {
                int[] edge = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                List<Integer> adj = graph.getOrDefault(edge[0], new ArrayList<>());
                adj.add(edge[1]);
                graph.put(edge[0], adj);
            }

            boolean success = false;
            for (int i = 1; i <= n; i++) {
                if (visited[i] == 0) {
                    success = successDfs(i);
                    if (!success) break;
                }
            }

            if (!success) {
                System.out.println(-1);
            } else {
                StringBuilder sb = new StringBuilder();
                while (!history.isEmpty()) {
                    sb.append(history.pop() + " ");
                }
                System.out.println(sb);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean successDfs(int cur) {
        visited[cur] = 1;
        List<Integer> adjs = graph.get(cur);
        if (adjs != null) {
            for (Integer adj : adjs) {
                if (visited[adj] == 1) {
                    return false;
                } else if (visited[adj] == 0) {
                    boolean success = successDfs(adj);
                    if (!success) return false;
                }
            }
        }
        visited[cur] = 2;
        history.push(cur);
        return true;
    }

    @Test
    public void test_01() {
        provideConsoleInput("6 6\n" +
                "1 2\n" +
                "3 2\n" +
                "4 2\n" +
                "2 5\n" +
                "6 5\n" +
                "4 6\n");
        main(new String[0]);
        String expected = "4 6 3 1 2 5 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("4 4\n" +
                "1 2\n" +
                "3 4\n" +
                "2 3\n" +
                "4 1\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_09() {
        provideConsoleInput("7 7\n" +
                "3 4\n" +
                "2 3\n" +
                "3 5\n" +
                "5 7\n" +
                "1 2\n" +
                "5 6\n" +
                "7 2\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

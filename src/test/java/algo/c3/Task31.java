package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/45468/problems/31/
Поиск в глубину

Ограничение времени 	2 секунды 	5 секунд
Ограничение памяти 	256Mb 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан неориентированный граф, возможно, с петлями и кратными ребрами. Необходимо построить компоненту связности,
содержащую первую вершину.

Формат ввода
В первой строке записаны два целых числа N (1 ≤ N ≤ 103) и M (0 ≤ M ≤ 5 * 105) — количество вершин и ребер в графе. В
последующих M строках перечислены ребра — пары чисел, определяющие номера вершин, которые соединяют ребра.

Формат вывода
В первую строку выходного файла выведите число K — количество вершин в компоненте связности. Во вторую строку выведите K
целых чисел — вершины компоненты связности, перечисленные в порядке возрастания номеров.

*/
public class Task31 extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            Map<Integer, List<Integer>> edges = new HashMap<>();
            for (int i = 1; i <= m; i++) {
                int[] edge = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                List<Integer> prev = edges.getOrDefault(edge[0], new ArrayList<>());
                prev.add(edge[1]);
                edges.put(edge[0], prev);

                prev = edges.getOrDefault(edge[1], new ArrayList<>());
                prev.add(edge[0]);
                edges.put(edge[1], prev);
            }
            List<Integer> visited = new ArrayList<>();
            dfs(edges, visited, 1);
            System.out.println(visited.size());
            System.out.println(visited.stream()
                    .sorted(Comparator.naturalOrder())
                    .map(el -> String.valueOf(el)).collect(Collectors.joining(" ")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void dfs(Map<Integer, List<Integer>> edges, List<Integer> visited, int cur) {
        visited.add(cur);
        if (edges.containsKey(cur)) {
            List<Integer> neigs = edges.get(cur);
            for (Integer neig : neigs) {
                if (!visited.contains(neig)) {
                    dfs(edges, visited, neig);
                }
            }
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("4 5\n" +
                "2 2\n" +
                "3 4\n" +
                "2 3\n" +
                "1 3\n" +
                "2 4\n" +
                "\n");
        main(new String[0]);
        String expected = "4\n" +
                "1 2 3 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10 0\n");
        main(new String[0]);
        String expected = "1\n" +
                "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

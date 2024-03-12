package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/*
https://contest.yandex.ru/contest/45468/problems/32/
Компоненты связности

Ограничение времени 	2 секунды 	5 секунд
Ограничение памяти 	256Mb 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан неориентированный невзвешенный граф, состоящий из N вершин и M ребер. Необходимо посчитать количество его компонент
связности и вывести их.

Формат ввода
Во входном файле записано два числа N и M (0 < N ≤ 100000, 0 ≤ M ≤ 100000). В следующих M строках записаны по два числа
i и j (1 ≤ i, j ≤ N), которые означают, что вершины i и j соединены ребром.

Формат вывода
В первой строчке выходного файла выведите количество компонент связности. Далее выведите сами компоненты связности в
следующем формате: в первой строке количество вершин в компоненте, во второй - сами вершины в произвольном порядке.

*/
public class Task32 extends ContestTask {
    static int[] visited;
    static Map<Integer, List<Integer>> edges;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            edges = new HashMap<>();
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

            visited = new int[n + 1];
            int component = 1;
            for (int cur = 1; cur <= n; cur++) {
                if (visited[cur] == 0) {
                    dfs(0, cur, component);
                    component++;
                }
            }
            Map<Integer, List<Integer>> result = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                int cur = visited[i];
                List<Integer> indices = result.getOrDefault(cur, new ArrayList<>());
                indices.add(i);
                result.put(cur, indices);
            }

            System.out.println(result.size());
            result.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(
                    entry -> {
                        List<Integer> list = entry.getValue();
                        System.out.println(list.size());
                        System.out.println(list.stream().map(el -> String.valueOf(el)).collect(Collectors.joining(" ")) + " ");
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int prev, int cur, int component) {
        if (visited[cur] == 0) {
            visited[cur] = component;
            if (edges.containsKey(cur)) {
                List<Integer> neigs = edges.get(cur);
                for (Integer neig : neigs) {
                    if (neig != prev && visited[neig] == 0) {
                        dfs(cur, neig, component);
                    }
                }
            }
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("6 4\n" +
                "3 1\n" +
                "1 2\n" +
                "5 4\n" +
                "2 3\n");
        main(new String[0]);
        String expected = "3\n" +
                "3\n" +
                "1 2 3 \n" +
                "2\n" +
                "4 5 \n" +
                "1\n" +
                "6 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("6 4\n" +
                "4 2\n" +
                "1 4\n" +
                "6 4\n" +
                "3 6\n");
        main(new String[0]);
        String expected = "2\n" +
                "5\n" +
                "1 2 3 4 6 \n" +
                "1\n" +
                "5 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f17() throws FileNotFoundException {
        String testNumber = "17";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

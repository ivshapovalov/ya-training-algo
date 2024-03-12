package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.*;

/*
https://contest.yandex.ru/contest/45468/problems/33/
Списывание

Ограничение времени 	2 секунды 	5 секунд
Ограничение памяти 	256Mb 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Во время контрольной работы профессор Флойд заметил, что некоторые студенты обмениваются записками. Сначала он хотел
поставить им всем двойки, но в тот день профессор был добрым, а потому решил разделить студентов на две группы:
списывающих и дающих списывать, и поставить двойки только первым.

У профессора записаны все пары студентов, обменявшихся записками. Требуется определить, сможет ли он разделить студентов
на две группы так, чтобы любой обмен записками осуществлялся от студента одной группы студенту другой группы.

Формат ввода
В первой строке находятся два числа N и M — количество студентов и количество пар студентов, обменивающихся записками (1
≤ N ≤ 102, 0 ≤ M ≤ N(N−1)/2).

Далее в M строках расположены описания пар студентов: два числа, соответствующие номерам студентов, обменивающихся
записками (нумерация студентов идёт с 1). Каждая пара студентов перечислена не более одного раза.

Формат вывода
Необходимо вывести ответ на задачу профессора Флойда. Если возможно разделить студентов на две группы - выведите YES;
иначе выведите NO.

*/
public class Task33 extends ContestTask {

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

                adj = graph.getOrDefault(edge[1], new ArrayList<>());
                adj.add(edge[0]);
                graph.put(edge[1], adj);
            }

            boolean result = true;
            int defaultColor = 1;
            for (int i = 1; i <= n; i++) {
                if (visited[i] == 0) {
                    visited[i] = defaultColor;
                    result = dfs(i);
                    if (!result) break;
                }
            }

            System.out.println(result ? "YES" : "NO");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean dfs(int cur) {
        List<Integer> adjs = graph.get(cur);
        if (adjs == null) return true;
        int color = visited[cur];
        for (Integer adj : adjs) {
            int adjColor = visited[adj];
            if (adjColor == 0) {
                visited[adj] = -color;
                boolean result = dfs(adj);
                if (!result) return result;
            } else if (adjColor == color) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 2\n" +
                "1 2\n" +
                "2 3\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3 3\n" +
                "1 2\n" +
                "2 3\n" +
                "1 3\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f17() throws FileNotFoundException {
        String testNumber = "09";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "YES\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

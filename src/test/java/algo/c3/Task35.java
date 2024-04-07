package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/45468/problems/35/
Поиск цикла

Все языки 	Python 3.6
Ограничение времени 	2 секунды 	5 секунд
Ограничение памяти 	256Mb 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан неориентированный граф. Требуется определить, есть ли в нем цикл, и, если есть, вывести его.

Формат ввода
В первой строке дано одно число n — количество вершин в графе ( 1≤ n ≤500 ). Далее в n строках задан сам граф матрицей
смежности.

Формат вывода
Если в иcходном графе нет цикла, то выведите «NO». Иначе, в первой строке выведите «YES», во второй строке выведите
число k — количество вершин в цикле, а в третьей строке выведите k различных чисел — номера вершин, которые принадлежат
циклу в порядке обхода (обход можно начинать с любой вершины цикла). Если циклов несколько, то выведите любой.
*/
public class Task35 extends ContestTask {
    static int[][] edges;
    static Set<Integer> visited;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            edges = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                int[] edge = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                System.arraycopy(edge, 0, edges[i], 1, edge.length);
            }
            List<Integer> cycle = new ArrayList<>();
            visited = new HashSet<>();
            for (int cur = 1; cur < edges.length; cur++) {
                if (!visited.contains(cur)) {
                    Stack<Integer> visited = new Stack<>();
                    cycle = dfs(0, cur, visited);
                    if (cycle.size() > 0) {
                        break;
                    }
                }
            }
            if (cycle.size() > 0) {
                System.out.println("YES");
                System.out.println(cycle.size());
                StringJoiner path = new StringJoiner(" ", "", "");
                for (int i = cycle.size() - 1; i >= 0; i--) {
                    path.add(String.valueOf(cycle.get(i)));
                }
                System.out.println(path);
            } else {
                System.out.print("NO\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> dfs(int prev, int cur, Stack<Integer> history) {
        int[] edge = edges[cur];
        history.push(cur);
        visited.add(cur);
        for (int adj = 1; adj < edge.length; adj++) {
            if (cur == adj || prev == adj) continue;
            if (edge[adj] == 1) {
                if (history.contains(adj)) {
                    int skip = Math.max(0, history.indexOf(adj));
                    return history.stream().skip(skip).collect(Collectors.toList());
                }
                List<Integer> result = dfs(cur, adj, history);
                if (result.size() != 0) {
                    return result;
                }
                history.pop();
            }
        }
        return new ArrayList<>();
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "0 1 1\n" +
                "1 0 1\n" +
                "1 1 0\n");
        main(new String[0]);
        String expected = "YES\n" +
                "3\n" +
                "3 2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4\n" +
                "0 0 1 0\n" +
                "0 0 0 1\n" +
                "1 0 0 0\n" +
                "0 1 0 0\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n" +
                "0 1 0 0 0\n" +
                "1 0 0 0 0\n" +
                "0 0 0 1 1\n" +
                "0 0 1 0 1\n" +
                "0 0 1 1 0\n");
        main(new String[0]);
        String expected = "YES\n" +
                "3\n" +
                "5 4 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("6\n" +
                "0 1 0 0 0 0\n" +
                "1 0 0 0 0 0\n" +
                "0 0 0 1 0 0\n" +
                "0 0 1 0 1 0\n" +
                "0 0 0 1 0 1\n" +
                "0 0 0 0 1 0\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f10() throws FileNotFoundException {
        String testNumber = "10";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

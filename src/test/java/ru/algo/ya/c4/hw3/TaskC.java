package ru.algo.ya.c4.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.*;

/*
https://contest.yandex.ru/contest/53031/problems/C/
Быстрый алгоритм Дейкстры

Ограничение времени 	5 секунд
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вам дано описание дорожной сети страны. Ваша задача – найти длину кратчайшего пути между городами А и B.

Формат ввода
Сеть дорог задана во входном файле следующим образом: первая строка содержит числа N и K (1 ≤ N ≤ 100000, 0 ≤ K ≤
300000), где K – количество дорог. Каждая из следующих K строк содержит описание дороги с двусторонним движением – три
целых числа ai, bi и li (1 ≤ ai, bi ≤ N, 1 ≤ li ≤ 106). Это означает, что имеется дорога длины li, которая ведет из
города ai в город bi. В последней строке находятся два числа А и В – номера городов, между которыми надо посчитать
кратчайшее расстояние (1 ≤ A, B ≤ N)

Формат вывода
Выведите одно число – расстояние между нужными городами. Если по дорогам от города А до города В доехать невозможно,
выведите –1.

*/

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" "))
                    .filter(el -> el != null && !el.isEmpty())
                    .mapToInt(el -> Integer.parseInt(el)).toArray();
            int N = input[0];
            int K = input[1];
            Map<Integer, Vertex> vertices = new HashMap<>();
            Map<Integer, Long> distances = new HashMap<>();

            for (int i = 1; i <= K; i++) {
                int[] row = Arrays.stream(r.readLine().trim().split(" "))
                        .filter(el -> el != null && !el.isEmpty())
                        .mapToInt(el -> Integer.parseInt(el)).toArray();
                int start = row[0];
                int finish = row[1];
                int distance = row[2];
                distances.put(start, Long.MAX_VALUE);
                distances.put(finish, Long.MAX_VALUE);

                Vertex from = vertices.getOrDefault(start, new Vertex(start));
                Vertex to = vertices.getOrDefault(finish, new Vertex(finish));
                from.edges.add(new Edge(from, to, distance));
                to.edges.add(new Edge(to, from, distance));
                vertices.putIfAbsent(start, from);
                vertices.putIfAbsent(finish, to);
            }
            input = Arrays.stream(r.readLine().trim().split(" "))
                    .filter(el -> el != null && !el.isEmpty())
                    .mapToInt(el -> Integer.parseInt(el)).toArray();
            int A = input[0];
            int B = input[1];
            if (A == B) {
                System.out.println("0");
                return;
            }
            PriorityQueue<Vertex> q = new PriorityQueue<>(new Comparator<Vertex>() {
                @Override
                public int compare(Vertex o1, Vertex o2) {
                    return (int) (o1.distPath - o2.distPath);
                }
            });
            Vertex from = vertices.get(A);
            if (from == null) {
                System.out.println("-1");
                return;
            }
            from.distPath = 0;
            q.add(from);
            while (!q.isEmpty()) {
                Vertex currentVertex = q.poll();
                List<Edge> edges = currentVertex.edges;
                if (edges == null) continue;
                for (Edge edge : edges) {
                    Vertex finish = edge.to;
                    if (finish.distPath > currentVertex.distPath + edge.distance) {
                        finish.distPath = currentVertex.distPath + edge.distance;
                        q.add(finish);
                    }
                }

            }
            System.out.println(vertices.get(B) == null || vertices.get(B).getDistPath() == Long.MAX_VALUE ? -1 :
                    vertices.get(B).getDistPath());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("6 4\n" +
                "1 2 7\n" +
                "2 4 8\n" +
                "4 5 1\n" +
                "4 3 100\n" +
                "3 1\n");
        main(new String[0]);
        String expected = "115\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10 20\n" +
                "9 3 8\n" +
                "6 3 10\n" +
                "3 8 1\n" +
                "2 7 10\n" +
                "1 2 10\n" +
                "7 1 9\n" +
                "5 6 8\n" +
                "6 8 2\n" +
                "5 9 2\n" +
                "2 10 4\n" +
                "1 5 5\n" +
                "8 7 10\n" +
                "10 7 10\n" +
                "8 9 1\n" +
                "2 8 7\n" +
                "4 8 9\n" +
                "7 5 9\n" +
                "1 6 6\n" +
                "3 2 9\n" +
                "4 9 4\n" +
                "2 6");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f04() throws FileNotFoundException {
        String testNumber = "04";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Edge {
        private Vertex from;
        private Vertex to;
        private int distance;

        public Edge(Vertex from, Vertex to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    static class Vertex {
        private int value;
        private long distPath;
        private List<Edge> edges = new ArrayList<>();

        public Vertex(int value) {
            this.value = value;
            this.distPath = Long.MAX_VALUE;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return value == vertex.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public long getDistPath() {
            return distPath;
        }

        public void setDistPath(long distPath) {
            this.distPath = distPath;
        }
    }
}

package ru.algo.ya.c4.sprint;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/53033/problems/C
Переезд

Ограничение времени 	2 секунды
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или transfer.in
Вывод 	стандартный вывод или transfer.out


Васина семья переезжает в новую квартиру, а переезд — это всегда хлопоты. Например, Васе совершенно не хочется
расставаться со своей коллекцией кружек, которую он собрал, посещая олимпиады. Все-таки, его коллекция самая большая в
мире и насчитывает 107 экземпляров! Поскольку каждая кружка весит 100 грамм, для ее перевозки Вася хочет нанять
грузовик. Однако, на всех дорогах висят знаки, ограничивающие вес транспорта. Кроме того, ровно через 24 часа выходит
новый эпизод любимого Васиного сериала, пропускать который он отказывается наотрез! От всей этой неразберихи у Васи
голова идет кругом, и он обращается к вам за помощью.

Вася хочет перевезти как можно больше кружек за первый же рейс, но если фуру, которая и так весит 3 тонны, полностью
нагрузить кружками, то, возможно, придется ехать в объезд. Так сколько же кружек можно довезти до новой квартиры, не
нарушая правил дорожного движения и не пропустив начало передачи?

Формат ввода

В первой строке входного файла указаны два числа N и M — число перекрестков на схеме города и число дорог соответственно
(1 ≤ N ≤ 500). В следующих M строках идет описание дорог. Каждая дорога описывается четырьмя числами: ai, bi, ti и wi.

ai и bi — это номера перекрестков, которые соединяет дорога, ai ≠ bi, 1 ≤ ai, bi ≤ N. Вася знает, что если есть дорога,
соединяющая напрямую два перекрестка, то она ровно одна.

ti — это время в минутах, которое тратится на проезд по этой дороге, 0 ≤ ti ≤ 1440.

wi — это максимальная масса в граммах, которую можно провозить по этой дороге, 0 ≤ wi ≤ 109.

Старая квартира Васи находится на этой схеме на перекрестке с номером 1, а новая — на перекрестке с номером N.

Формат вывода
Выведите ровно одно число — наибольшее количество кружек, которое Вася может увезти за один рейс, не нарушая правил
дорожного движения и не опоздав к началу сериала.

*/

public class TaskC extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
            int N=input[0];
            int M=input[1];
            int MAX_TIME_MINUTES=24*60;

            Map<Integer, Vertex> vertices = new HashMap<>();
            Map<Integer, Long> distances = new HashMap<>();
            for (int i = 1; i <=M ; i++) {
                int[] road = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
                int start = road[0];
                int finish = road[1];
                int distance = road[2];
                int weight = road[3];
                distances.put(start, Long.MAX_VALUE);
                distances.put(finish, Long.MAX_VALUE);

                Vertex from = vertices.getOrDefault(start, new Vertex(start));
                Vertex to = vertices.getOrDefault(finish, new Vertex(finish));
                from.edges.add(new Edge(from, to, distance,weight));
                to.edges.add(new Edge(to, from, distance,weight));
                vertices.putIfAbsent(start, from);
                vertices.putIfAbsent(finish, to);
            }
            int A = 1;
            int B = N;
            if (A == B) {
                System.out.println("10000000");
                return;
            }
            PriorityQueue<Vertex> q = new PriorityQueue<>(new Comparator<Vertex>() {
                @Override
                public int compare(Vertex o1, Vertex o2) {
                    if (o1.weightPath==o2.weightPath) {
                        return (int) (o1.timePath - o2.timePath);
                    } else {
                        return (int)(o2.weightPath-o1.weightPath);
                    }
                }
            });
            Vertex from = vertices.get(A);
            from.timePath = 0;
            q.add(from);
            while (!q.isEmpty()) {
                Vertex currentVertex = q.poll();
                List<Edge> edges = currentVertex.edges;
                if (edges == null) continue;
                for (Edge edge : edges) {
                    Vertex finish = edge.to;
                    if (currentVertex.timePath + edge.distance>MAX_TIME_MINUTES) continue;
                    if (finish.timePath > currentVertex.timePath + edge.distance) {
                        finish.timePath = currentVertex.timePath + edge.distance;
                        finish.weightPath = currentVertex.timePath + edge.distance;
                        q.add(finish);
                    }
                }

            }
            System.out.println(vertices.get(B) == null || vertices.get(B).getTimePath() == Long.MAX_VALUE ? -1 :
                    vertices.get(B).getTimePath());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Edge {
        private Vertex from;
        private Vertex to;
        private int distance;
        private int weight;

        public Edge(Vertex from, Vertex to, int distance, int weight) {
            this.from = from;
            this.to = to;
            this.distance = distance;
            this.weight = weight;
        }

    }

    static class Vertex {
        private int value;
        private long timePath;
        private long weightPath;
        private List<Edge> edges = new ArrayList<>();

        public Vertex(int value) {
            this.value = value;
            this.timePath = Long.MAX_VALUE;
            this.weightPath = 0;
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

        public long getTimePath() {
            return timePath;
        }

        public void setTimePath(long timePath) {
            this.timePath = timePath;
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 3\n" +
                "2 3 40 3000299\n" +
                "1 3 4 3000056\n" +
                "1 2 10 3000201\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

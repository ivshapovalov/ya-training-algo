package algo.c4.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/53031/problems/D/
Автобусы в Васюках

 	Все языки 	Python 3.6
Ограничение времени 	3 секунды 	5 секунд
Ограничение памяти 	512Mb 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Между некоторыми деревнями края Васюки ходят автобусы. Поскольку пассажиропотоки здесь не очень большие, то автобусы
ходят всего несколько раз в день.

Марии Ивановне требуется добраться из деревни d в деревню v как можно быстрее (считается, что в момент времени 0 она
находится в деревне d). Формат ввода

Сначала вводится число N – общее число деревень (1 <= N <= 100), затем номера деревень d и v, за ними следует количество
автобусных рейсов R (0 <= R <= 10000). Далее идут описания автобусных рейсов. Каждый рейс задается номером деревни
отправления, временем отправления, деревней назначения и временем прибытия (все времена – целые от 0 до 10000). Если в
момент t пассажир приезжает в какую-то деревню, то уехать из нее он может в любой момент времени, начиная с t. Формат
вывода

Выведите минимальное время, когда Мария Ивановна может оказаться в деревне v. Если она не сможет с помощью указанных
автобусных рейсов добраться из d в v, выведите -1.

*/

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int villagesAmount = Integer.parseInt(r.readLine().trim());
            int[] input = Arrays.stream(r.readLine().trim().split(" "))
                    .filter(el -> el != null && !el.isEmpty())
                    .mapToInt(el -> Integer.parseInt(el)).toArray();
            int fromIndex = input[0];
            int toIndex = input[1];
            int routesAmount = Integer.parseInt(r.readLine().trim());

            Map<Integer, Vertex> vertices = new HashMap<>();
            for (int routeIndex = 0; routeIndex < routesAmount; routeIndex++) {
                int[] route = Arrays.stream(r.readLine().trim().split(" "))
                        .filter(el -> el != null && !el.isEmpty())
                        .mapToInt(el -> Integer.parseInt(el)).toArray();

                Vertex from = vertices.getOrDefault(route[0], new Vertex(route[0]));
                Vertex to = vertices.getOrDefault(route[2], new Vertex(route[2]));
                from.edges.add(new Edge(from, to, route[1], route[3]));
                vertices.putIfAbsent(route[0], from);
                vertices.putIfAbsent(route[2], to);
            }

            if (fromIndex == toIndex) {
                System.out.println("0");
                return;
            }
            PriorityQueue<Vertex> q = new PriorityQueue<>((o1, o2) -> (int) (o1.totalTime - o2.totalTime));
            Vertex from = vertices.get(fromIndex);
            if (from == null) {
                System.out.println("-1");
                return;
            }
            from.totalTime = 0;
            q.add(from);
            while (!q.isEmpty()) {
                Vertex currentVertex = q.poll();
                List<Edge> edges = currentVertex.edges;
                if (edges == null) continue;
                for (Edge edge : edges) {
                    if (edge.timeFrom < currentVertex.totalTime) continue;
                    Vertex finish = edge.to;
                    if (finish.totalTime > edge.timeFrom + edge.time) {
                        finish.totalTime = edge.timeFrom + edge.time;
                        q.add(finish);
                    }
                }

            }
            System.out.println(vertices.get(toIndex) == null || vertices.get(toIndex).getTotalTime() == Long.MAX_VALUE ? -1 :
                    vertices.get(toIndex).getTotalTime());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "1 3\n" +
                "4\n" +
                "1 0 2 5\n" +
                "1 1 2 3\n" +
                "2 3 3 5\n" +
                "1 1 3 10\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Edge {
        private final Vertex from;
        private final int timeFrom;
        private final Vertex to;
        private final int timeTo;
        private final int time;

        public Edge(Vertex from, Vertex to, int timeFrom, int timeTo) {
            this.from = from;
            this.to = to;
            this.timeFrom = timeFrom;
            this.timeTo = timeTo;
            this.time = timeTo - timeFrom;
        }
    }

    static class Vertex {
        private int value;
        private long totalTime;
        private final List<Edge> edges = new ArrayList<>();

        public Vertex(int value) {
            this.value = value;
            this.totalTime = Long.MAX_VALUE;
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

        public long getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(long totalTime) {
            this.totalTime = totalTime;
        }
    }

}

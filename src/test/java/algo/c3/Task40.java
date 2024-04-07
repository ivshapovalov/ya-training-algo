package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/45468/problems/40/
Метро

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Метрополитен состоит из нескольких линий метро. Все станции метро в городе пронумерованы натуральными числами от 1 до N.
На каждой линии расположено несколько станций. Если одна и та же станция расположена сразу на нескольких линиях, то она
является станцией пересадки и на этой станции можно пересесть с любой линии, которая через нее проходит, на любую другую
(опять же проходящую через нее).

Напишите программу, которая по данному вам описанию метрополитена определит, с каким минимальным числом пересадок можно
добраться со станции A на станцию B. Если данный метрополитен не соединяет все линии в одну систему, то может так
получиться, что со станции A на станцию B добраться невозможно, в этом случае ваша программа должна это определить.

Формат ввода
Сначала вводится число N — количество станций метро в городе (2≤N≤100). Далее следует число M — количество линий метро
(1≤M≤20). Далее идет описание M линий. Описание каждой линии состоит из числа Pi — количество станций на этой линии
(2≤Pi≤50) и Pi чисел, задающих номера станций, через которые проходит линия (ни через какую станцию линия не проходит
дважды).

Затем вводятся два различных числа: A — номер начальной станции, и B — номер станции, на которую нам нужно попасть. При
этом если через станцию A проходит несколько линий, то мы можем спуститься на любую из них. Так же если через станцию B
проходит несколько линий, то нам не важно, по какой линии мы приедем.

Формат вывода
Выведите минимальное количество пересадок, которое нам понадобится. Если добраться со станции A на станцию B невозможно,
программа должна вывести одно число –1 (минус один).
*/
public class Task40 extends ContestTask {
    static Map<Integer, Set<Integer>> stations;
    static List<Integer> visited;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int m = Integer.valueOf(reader.readLine().trim());

            stations = new HashMap<>();
            for (int i = 1; i <= m; i++) {
                List<Integer> lineStations = Arrays.stream(reader.readLine().trim().split(" "))
                        .map(el -> Integer.valueOf(el)).collect(Collectors.toList());
                int lineNum = lineStations.get(0);
                lineStations.remove(0);
                stations.put(i, new HashSet<>(lineStations));
            }
            Map<Integer, Set<Integer>> lines = new HashMap<>();
            for (int i = 1; i <= m; i++) {
                for (int j = i + 1; j <= m; j++) {
                    Set<Integer> line1 = stations.get(i);
                    Set<Integer> line2 = stations.get(j);
                    if (line1.stream().filter(el -> !line2.contains(el)).collect(Collectors.toSet()).size() !=
                            line1.size()) {
                        Set<Integer> prev = lines.getOrDefault(i, new HashSet<>());
                        prev.add(j);
                        lines.put(i, prev);

                        prev = lines.getOrDefault(j, new HashSet<>());
                        prev.add(i);
                        lines.put(j, prev);
                    }
                }
            }
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int from = input[0];
            int to = input[1];
            Set<Integer> linesFrom =
                    stations.entrySet().stream()
                            .filter(entry -> entry.getValue().contains(from))
                            .map(el -> el.getKey()).collect(Collectors.toSet());
            Set<Integer> linesTo =
                    stations.entrySet().stream()
                            .filter(entry -> entry.getValue().contains(to))
                            .map(el -> el.getKey()).collect(Collectors.toSet());
            Queue<String[]> q = new LinkedList<>();
            for (Integer lineFrom : linesFrom) {
                q.offer(new String[]{String.valueOf(lineFrom), "0"});
            }
            Set<Integer> visited = new HashSet<>();
            int result = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                String[] poll = q.poll();
                int cur = Integer.valueOf(poll[0]);
                int step = Integer.valueOf(poll[1]);
                visited.add(cur);
                if (linesTo.contains(cur)) {
                    result = Math.min(result, step);
                    break;
                }
                Set<Integer> crossLines = lines.getOrDefault(cur, new HashSet<>());
                for (Integer crossLine : crossLines) {
                    if (visited.contains(crossLine)) continue;
                    if (lines.getOrDefault(crossLine, new HashSet<>()).contains(linesTo)) {
                        result = Math.min(result, step + 1);
                        break;
                    } else {
                        q.offer(new String[]{String.valueOf(crossLine), String.valueOf(step + 1)});
                    }
                }
            }
            System.out.println((result == Integer.MAX_VALUE ? -1 : result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "2\n" +
                "4 1 2 3 4\n" +
                "2 5 3\n" +
                "3 1\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("12\n" +
                "3\n" +
                "0 1 2 3 4 5\n" +
                "0 6 7 4 8 9\n" +
                "0 10 11 9 12\n" +
                "1 10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("12\n" +
                "3\n" +
                "0 1 2 3 4 5\n" +
                "0 6 7 4 8 9\n" +
                "0 10 11 9 12\n" +
                "1 4\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("12\n" +
                "3\n" +
                "0 1 2 3 4 5\n" +
                "0 6 7 4 8 9\n" +
                "0 10 11 9 12\n" +
                "1 6\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("12\n" +
                "3\n" +
                "0 1 2 3 4 5\n" +
                "0 6 7 4 8 9\n" +
                "0 10 11 9 12 5\n" +
                "1 12\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("5\n" +
                "5\n" +
                "2 1 2\n" +
                "2 1 3\n" +
                "2 2 3\n" +
                "2 3 4\n" +
                "2 4 5\n" +
                "1 5\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

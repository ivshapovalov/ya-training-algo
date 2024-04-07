package algo.c1.hw7;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/A/
Наблюдение за студентами

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На первом курсе одной Школы, учится 1 ≤ N ≤ 109 студентов. При проведении экзаменов студентов рассаживают в ряд, каждого
за своей партой. Парты пронумерованы числами от 0 до N - 1.

Известно, что студент, оставшись без наблюдения, открывает телефон и начинает искать ответы на экзамен в поисковике
Яндекса.

Поэтому было решено позвать M преподавателей наблюдать за студентами. Когда за студентом наблюдает хотя бы один
преподаватель, он стесняется и не идет искать ответы к экзамену. Преподаватель с номером i видит студентов сидящих за
партами от bi до ei включительно.

Необходимо посчитать количество студентов, которые все таки будут искать ответы к экзамену в Яндексе

Формат ввода
В первой строке находятся два целых числа 1 ≤ N ≤ 109, 1 ≤ M ≤ 104 — число студентов и число преподавателей
соответственно. В следующих M строках содержится по два целых числа 0 ≤ bi ≤ ei ≤ N - 1 — парты, за которыми наблюдает
i-й преподаватель.

Формат вывода
Выведите одно число — количество студентов оставшихся без наблюдения.

*/
public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            int[][] checked = new int[m][2];
            for (int i = 0; i < m; i++) {
                int[] check = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                checked[i] = check;
            }
            Map<Integer, Integer> events = new LinkedHashMap<>();
            for (int[] check : checked) {
                events.put(check[0], events.getOrDefault(check[0], 0) + 1);
                events.put(check[1], events.getOrDefault(check[1], 0) - 1);
            }
            int prev = 0;
            int sum = 0;
            int online = 0;
            for (Map.Entry<Integer, Integer> event :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                if (event.getValue() == 0) {
                    if (online == 0) {
                        sum += event.getKey() - prev;
                    }
                    prev = event.getKey() + 1;
                } else if (event.getValue() > 0) {
                    if (online == 0) {
                        sum += event.getKey() - prev;
                    }
                    online += event.getValue();
                } else {
                    online += event.getValue();
                    if (online == 0) {
                        prev = event.getKey() + 1;
                    }
                }
            }
            sum += n - prev;
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_01() {
        provideConsoleInput("10 3\n" +
                "1 3\n" +
                "2 4\n" +
                "9 9\n\n\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10 3\n" +
                "1 3\n" +
                "3 4\n" +
                "4 9\n\n\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10 2\n" +
                "1 1\n" +
                "1 2\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("10 1\n" +
                "5 6\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("10 1\n" +
                "0 0\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("10 3\n" +
                "0 4\n" +
                "1 3\n" +
                "5 7\n"

        );
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

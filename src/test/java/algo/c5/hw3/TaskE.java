package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/59541/problems/E/
Два из трех

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вам даны три списка чисел. Найдите все числа, которые встречаются хотя бы в двух из трёх списков.

Формат ввода
Во входных данных описывается три списка чисел. Первая строка каждого описания списка состоит из длины списка n (1 ≤ n ≤
1000). Вторая строка описания содержит список натуральных чисел, записанных через пробел. Числа не превосходят 109.

Формат вывода
Выведите все числа, которые содержатся хотя бы в двух списках из трёх, в порядке возрастания. Обратите внимание, что
каждое число необходимо выводить только один раз.

*/

public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n1 = Integer.parseInt(r.readLine());
            Set<Integer> set1 = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).map(el -> Integer.parseInt(el)).collect(Collectors.toSet());
            int n2 = Integer.parseInt(r.readLine());
            Set<Integer> set2 = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).map(el -> Integer.parseInt(el)).collect(Collectors.toSet());
            int n3 = Integer.parseInt(r.readLine());
            Set<Integer> set3 = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).map(el -> Integer.parseInt(el)).collect(Collectors.toSet());

            Set<Integer> common = new HashSet<>();
            Set<Integer> intersection1and2 = new HashSet<>(set1);
            intersection1and2.retainAll(set2);
            common.addAll(intersection1and2);
            Set<Integer> intersection1and3 = new HashSet<>(set1);
            intersection1and3.retainAll(set3);
            common.addAll(intersection1and3);
            Set<Integer> intersection2and3 = new HashSet<>(set2);
            intersection2and3.retainAll(set3);
            common.addAll(intersection2and3);
            System.out.println(common.stream().sorted().map(el -> String.valueOf(el)).collect(Collectors.joining(" ")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("2\n" +
                "3 1\n" +
                "2\n" +
                "1 3\n" +
                "2\n" +
                "1 2\n");
        main(new String[0]);
        String expected = "1 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("3\n" +
                "1 2 2\n" +
                "3\n" +
                "3 4 3\n" +
                "1\n" +
                "5");
        main(new String[0]);
        String expected = "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

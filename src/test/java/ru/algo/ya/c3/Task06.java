package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://contest.yandex.ru/contest/45468/problems/6/
Операционные системы lite

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Васин жесткий диск состоит из M секторов. Вася последовательно устанавливал на него различные операционные системы
следующим методом: он создавал новый раздел диска из последовательных секторов, начиная с сектора номер ai и до сектора
bi включительно, и устанавливал на него очередную систему. При этом, если очередной раздел хотя бы по одному сектору
пересекается с каким-то ранее созданным разделом, то ранее созданный раздел «затирается», и операционная система,
которая на него была установлена, больше не может быть загружена.

Напишите программу, которая по информации о том, какие разделы на диске создавал Вася, определит, сколько в итоге
работоспособных операционных систем установлено и работает в настоящий момент на Васином компьютере.

Формат ввода
Сначала вводятся натуральное число M — количество секторов на жестком диске (1 ≤ M ≤ 109) и целое число N — количество
разделов, которое последовательно создавал Вася (0 ≤ N ≤ 1000).
Далее идут N пар чисел ai и bi, задающих номера начального и конечного секторов раздела (1 ≤ ai ≤ bi ≤ M).

Формат вывода
Выведите одно число — количество работающих операционных систем на Васином компьютере.
*/

public class Task06 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int m = Integer.valueOf(reader.readLine().trim());
            int n = Integer.valueOf(reader.readLine().trim());
            List<int[]> systems = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] system =
                        Arrays.stream(reader.readLine().trim().split(" "))
                                .mapToInt(el -> Integer.valueOf(el).intValue())
                                .toArray();
                systems.removeIf(el -> systemIntersects(el, system));
                systems.add(system);
            }
            System.out.println(systems.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean systemIntersects(int[] s1, int[] s2) {
        return s2[1] >= s1[0] && s2[0] <= s1[1];
    }

    @Test
    public void test_01() {
        provideConsoleInput("10\n" +
                "3\n" +
                "1 3\n" +
                "4 7\n" +
                "3 4\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10\n" +
                "4\n" +
                "1 3\n" +
                "4 5\n" +
                "7 8\n" +
                "4 6\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10\n" +
                "5\n" +
                "1 5\n" +
                "7 9\n" +
                "4 8\n" +
                "5 6\n" +
                "9 10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("10\n" +
                "5\n" +
                "2 5\n" +
                "7 9\n" +
                "1 8\n" +
                "5 6\n" +
                "9 10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("1\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("1\n" +
                "1\n" +
                "1 1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07() {
        provideConsoleInput("1\n" +
                "5\n" +
                "1 1\n" +
                "1 1\n" +
                "1 1\n" +
                "1 1\n" +
                "1 1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/45468/problems/3/
Коллекционер Диего

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Диего увлекается коллекционированием наклеек. На каждой из них написано число, и каждый коллекционер мечтает собрать
наклейки со всеми встречающимися числами.

Диего собрал N наклеек, некоторые из которых, возможно, совпадают. Как-то раз к нему пришли K коллекционеров. i-й из них
собрал все наклейки с номерами не меньшими, чем pi. Напишите программу, которая поможет каждому из коллекционеров
определить, сколько недостающих ему наклеек есть у Диего. Разумеется, гостей Диего не интересуют повторные экземпляры
наклеек.

Формат ввода
В первой строке содержится единственное число N (0 ≤ N ≤ 100000) — количество наклеек у Диего.
В следующей строке содержатся N целых неотрицательных чисел (не обязательно различных) — номера наклеек Диего. Все
номера наклеек не превосходят 109.
В следующей строке содержится число K (0 ≤ K ≤ 100000) — количество коллекционеров, пришедших к Диего. В следующей
строке содержатся K целых чисел pi (0 ≤ pi ≤ 109), где pi — наименьший номер наклейки, не интересующий i-го
коллекционера.

Формат вывода
Для каждого коллекционера в отдельной строке выведите количество различных чисел на наклейках, которые есть у Диего, но
нет у этого коллекционера.
*/

public class Task03 extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            List<Integer> diego = Arrays.stream(reader.readLine().trim().split(" "))
                    .map(el -> Integer.valueOf(el).intValue())
                    .sorted(Comparator.naturalOrder())
                    .distinct()
                    .collect(Collectors.toList());
            int k = Integer.valueOf(reader.readLine().trim());
            int[] visitors = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

            for (int visitor : visitors) {
                int res = Collections.binarySearch(diego, visitor);
                if (res < 0) res = (-res) - 1;
                System.out.println(res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n" +
                "5\n" +
                "2\n" +
                "4 6\n");
        main(new String[0]);
        String expected = "0\n" +
                "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "100 1 50\n" +
                "3\n" +
                "300 0 75\n");
        main(new String[0]);
        String expected = "3\n" +
                "0\n" +
                "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f9() throws FileNotFoundException {
        String testNumber = "09";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

package ru.algo.ya.c4.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://contest.yandex.ru/contest/53032/problems/A/
Все перестановки заданной длины

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

По данному числу N (0 < N < 10) выведите все перестановки чисел от 1 до N в лексикографическом порядке.

*/

public class TaskA extends ContestTask {
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        result = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int num = Integer.valueOf(r.readLine().trim());
            String str = IntStream.rangeClosed(1, num).mapToObj(n -> String.valueOf(n)).collect(Collectors.joining());

            int n = str.length();
            permute(str, 0, n - 1);
            System.out.println(result.stream().sorted(Comparator.naturalOrder())
                    .collect(Collectors.joining("\n")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void permute(String str, int l, int r) {
        if (l == r) {
            result.add(str);

        } else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    public static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n");
        main(new String[0]);
        String expected = "12\n" +
                "21\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3\n");
        main(new String[0]);
        String expected = "123\n" +
                "132\n" +
                "213\n" +
                "231\n" +
                "312\n" +
                "321\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f09() throws FileNotFoundException {
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

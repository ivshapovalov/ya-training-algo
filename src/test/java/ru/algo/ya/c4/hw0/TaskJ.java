package ru.algo.ya.c4.hw0;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
https://contest.yandex.ru/contest/53027/problems/J/
Групповой проект

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Всего студентов по направлению «Мировая культура» — n человек. Преподаватель дал задание — групповой проект. Для
выполнения этого задания студенты должны разбиться на группы численностью от a до b человек. Скажите, можно ли разбить
всех студентов на группы для выполнения проекта или преподаватель что-то перепутал.

Формат ввода
В первой строке вводится число t (1 ≤ t ≤ 100) — количество тестовых случаев. Далее для каждого тестового случая
вводится 3 целых числа n, a и b (1 ≤ n ≤ 109, 1 ≤ a ≤ b ≤ n) — общее число студентов и ограничение на число студентов в
одной группе.

Формат вывода
Для каждого тестового случая выведите "YES", если можно разбить студентов на группы и "NO", если нельзя.
*/

public class TaskJ extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            Integer testCount = Integer.parseInt(r.readLine().trim());
            for (int i = 0; i < testCount; i++) {
                int[] input = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
                int n = input[0];
                int a = input[1];
                int b = input[2];

                boolean result = calcResult(n, a, b);
                System.out.println(result ? "YES" : "NO");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean calcResult(int n, int a, int b) {
       int groupsAmount=n/a;
       return (double)n%a/groupsAmount<= b-a;
    }

    @Test
    public void test_01() {
        provideConsoleInput("4\n" +
                "10 2 3\n" +
                "11 7 8\n" +
                "28 4 6\n" +
                "3 1 2\n");
        main(new String[0]);
        String expected = "YES\n" +
                "NO\n" +
                "YES\n" +
                "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
    @Test
    public void test_06_3() {
        provideConsoleInput("1\n" +
                "42042156 11842 20429177\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f06() throws FileNotFoundException {
        String testNumber = "06";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

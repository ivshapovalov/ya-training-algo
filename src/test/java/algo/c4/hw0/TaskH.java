package algo.c4.hw0;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/53027/problems/H/
Результаты контеста

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Чтобы оценить качество обучения программированию, в каждой группы студентов подсчитывается один параметр — суммарное
количество решенных студентами задач.

Известно, что в первой группе суммарное количество решенных на контесте задач равно a, а во второй — b. Всего на
контесте было предложено n задач, а также известно, что каждый студент решил не менее одной (и не более n) задач.

По заданным a, b и n определите, могло ли в первой группе быть строго больше студентов, чем во второй.

Формат ввода Вводятся три целых числа a, b, n (1 ≤ a, b, n ≤ 10000).

Формат вывода Выведите "Yes" если в первой группе могло быть строго больше студентов, чем во второй, и "No" в противном
случае.

*/

public class TaskH extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(r.readLine().trim());//Решено 1 группой
            int b = Integer.parseInt(r.readLine().trim());//решено 2 группой
            int n = Integer.parseInt(r.readLine().trim());// всего задач

            if (Math.ceil((double) b / n) < Math.ceil(a)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("60\n" +
                "30\n" +
                "4\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("30\n" +
                "30\n" +
                "1\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("30\n" +
                "150\n" +
                "4\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_18() {
        provideConsoleInput("2\n" +
                "10000\n" +
                "10000");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_22() {
        provideConsoleInput("2\n" +
                "10000\n" +
                "9999");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

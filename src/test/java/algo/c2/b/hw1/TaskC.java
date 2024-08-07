package algo.c2.b.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/28730/problems/С/
Даты

Ограничение времени 	2 секунды
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Как известно, два наиболее распространённых формата записи даты — это европейский (сначала день, потом месяц, потом год)
и американски (сначала месяц, потом день, потом год). Системный администратор поменял дату на одном из бэкапов и сейчас
хочет вернуть дату обратно. Но он не проверил, в каком формате дата используется в системе. Может ли он обойтись без
этой информации?

Иначе говоря, вам даётся запись некоторой корректной даты. Требуется выяснить, однозначно ли по этой записи определяется
дата даже без дополнительной информации о формате.

Формат ввода
Первая строка входных данных содержит три целых числа —
x, y и z (1≤x≤31, 1≤y≤31, 1970≤z≤2069. Гарантируется, что хотя бы в одном формате запись xyz задаёт корректную дату.

Формат вывода
Выведите 1, если дата определяется однозначно, и 0 в противном случае.
 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int x = input[0];
            int y = input[1];
            int z = input[2];

            if ((x <= 12 && y <= 12) && (x != y)) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 2 2003\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2 29 2008\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3 3 2067\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

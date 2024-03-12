package ru.algo.ya.c4.sprint;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*
https://contest.yandex.ru/contest/53033/problems/A
Объединение последовательностей

Ограничение времени 	3 секунды
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Даны две бесконечных возрастающих последовательности чисел A и B. i-ый член последовательности A равен i2. i-ый член
последовательности B равен i3.

Требуется найти Cx, где C – возрастающая последовательность, полученная при объединении последовательностей A и B. Если
существует некоторое число, которое встречается и в последовательности A и в последовательности B, то в
последовательность C это число попадает в единственном экземпляре.

Формат ввода
В единственной строке входного файла дано натуральное число x (1 ≤ x ≤ 107).

Формат вывода
В выходной файл выведите Cx.

*/

public class TaskA extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int x = Integer.parseInt(r.readLine().trim());
            long counter2=0;
            long counter3=0;
            int i=0;
            for (i = 0; i <= x; i++) {
                double pow3 = Math.pow(i, 3);
                double sqrt3 = Math.sqrt(pow3);
                counter3++;
                if (sqrt3==(double)(int)sqrt3) {
                    //nothing
                    int a=0;
                } else {
                    counter2++;
                }
                if (counter2+counter3 > x) break;
            }
            if (counter2+counter3==x){
                System.out.println((long)Math.pow(i, 3));
            } else {
                System.out.println((long)Math.pow(i, 2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("10000000\n");
        main(new String[0]);
        String expected = "9223372036854775807\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("6\n");
        main(new String[0]);
        String expected = "27\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

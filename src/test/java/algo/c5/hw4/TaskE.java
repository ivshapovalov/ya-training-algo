package algo.c5.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/59542/problems/E/
Нумерация дробей

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Георг Кантор доказал, что множество всех рациональных чисел счетно (т.е. все рациональные числа можно пронумеровать).

Чтобы упорядочить дроби необходимо их положить в таблицу, как показано на рисунке. В строку с номером i этой матрицы по
порядку записаны дроби с числителем i, а в столбец с номером j дроби с знаменателем j.

Дальше необходимо выписать все дроби в том порядке, как показано на рисунке стрелками. Получится такая
последовательность: , , , , , , …
https://contest.yandex.ru/testsys/statement-image?imageId=3cc21750b8e8a181a72a5c418e33d8dbfb560a6d294bac8db573eeb3958595a9

Вам требуется по числу n найти числитель и знаменатель n-ой дроби.

Формат ввода
Во входном файле дано число n (1 ≤ n ≤ 1018) — порядковый номер дроби в последовательности.

Формат вывода
В выходной файл требуется вывести через символ / два числа: числитель и знаменатель соответствующей дроби.
*/

public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("1\n");
        main(new String[0]);
        String expected = "1/1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("6\n");
        main(new String[0]);
        String expected = "3/1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("2\n");
        main(new String[0]);
        String expected = "2/1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

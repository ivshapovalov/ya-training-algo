package algo.c5.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/59542/problems/J/
Дождик

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В НИИ метеорологии решили изучить процесс образования водоемов на различных рельефах местности во время дождя. Ввиду
сложности реальной задачи была создана двумерная модель, в которой местность имеет только два измерения — высоту и
длину. В этой модели рельеф местности можно представить как N-звенную ломаную c вершинами (x0, y0), ..., (xN, yN), где
x0 < x1 < ... < xN и yi ≠ yj, для любых i ≠ j. Слева в точке x0 и справа в точке xN рельеф ограничен вертикальными
горами огромной высоты.

Если бы рельеф был горизонтальным, то после дождя вся местность покрылась бы слоем воды глубины H. Но поскольку рельеф —
это ломаная, то вода стекает и скапливается в углублениях, образуя водоемы.

Требуется найти максимальную глубину в образовавшихся после дождя водоемах.

Формат ввода
В первой строке расположены натуральное число N (1 ≤ N ≤ 100) и H — действительное число, заданное с тремя цифрами после
десятичной точки (0 ≤ H ≤ 109). В последующих N + 1 строках — по два целых числа xi, yi (-10000 ≤ xi, yi ≤ 10000).

Числа в строках разделены пробелами.

Формат вывода
Выведите единственное число — искомую глубину с точностью 10-4.
*/

public class TaskJ extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("7 7.000\n" +
                "-5 10\n" +
                "-3 4\n" +
                "-1 6\n" +
                "1 –4\n" +
                "4 17\n" +
                "5 3\n" +
                "9 5\n" +
                "12 15\n");
        main(new String[0]);
        String expected = "15.8446";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

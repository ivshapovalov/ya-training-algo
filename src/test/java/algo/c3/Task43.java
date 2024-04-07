package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/46304/problems/C/
Доставка со склада

Ограничение времени 	4 секунды
Ограничение памяти 	    1Gb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt


В городе работает два склада, из которых заказ может быть доставлен на дом. На каждом складе работает по одному курьеру.
Курьер может доставлять только один заказ одновременно.

Сегодня поступило N заказов, каждый заказ может быть доставлен с любого из складов. Для каждого заказа и каждого из двух
складов известно время, необходимое для доставки заказа и возвращения курьера обратно на склад. Заказы могут выполняться
в любом порядке. Курьер может приступать к доставке следующего заказа сразу после возвращения на склад.

Для каждого из заказов определите, какой из курьеров должен его доставить чтобы последний из двух курьеров вернулся на
склад после выполнения всех своих заказов как можно раньше.

Формат ввода
В первой строке задаётся число N (1≤N≤1000) — количество заказов.
В каждой из следующих N описвыются заказы, по одному в строке. В i-й строке даны два числа ai и bi (1≤ai,bi≤100) — время
доставки и возвращения первого и второго курьера соответственно для выполнения i-го заказа.

Формат вывода
Выведите N чисел 1 или 2, задающих номер курьера, который будет выполнять соответствующий заказ.

Если правильных ответов несколько — выведите любой из них.


*/
public class Task43 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.valueOf(reader.readLine().trim());
            int[] point = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_1() {
        provideConsoleInput("push 1\n" +
                "front\n" +
                "exit\n");
        main(new String[0]);
        String expected = "ok\n" +
                "1\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

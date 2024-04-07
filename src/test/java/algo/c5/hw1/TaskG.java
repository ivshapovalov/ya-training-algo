package algo.c5.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59539/problems/G/
Разрушить казарму

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt


Вы играете в интересную стратегию. У вашего соперника остались всего одна казарма — здание, в котором постоянно
появляются новые солдаты. Перед атакой у вас есть x солдат. За один раунд каждый солдат может убить одного из солдат
противника или нанести 1 очко урона казарме (вычесть единицу здоровья у казармы). Изначально у вашего оппонента нет
солдат. Тем не менее, его казарма имеет y единиц здоровья и производит p солдат за раунд.

Ход одного раунда:

    Каждый солдат из вашей армии либо убивает одного из солдат вашего противника, либо наносит 1 очко урона казарме.
    Каждый солдат может выбрать своё действие. Когда казарма теряет все свои единицы здоровья, она разрушается. Ваш
    противник атакует. Он убьет k ваших солдат, где k — количество оставшихся у противника солдат. Если казармы еще не
    разрушены, ваш противник производит p новых солдат.

Ваша задача — разрушить казарму и убить всех солдат противника. Если это возможно, посчитайте минимальное количество
раундов, которое вам нужно для этого. В противном случае выведите -1.

Формат ввода
На вход подаётся три целых числа x, y, p (1 ≤ x, y, p ≤ 5000) — количество ваших солдат на старте игры, количество очков
здоровья казармы и количество производимых за раунд казармой солдат, соответственно. Каждое число расположено в новой
строке.

Формат вывода
Если возможно убить всех вражеских солдат и разрушить казарму, выведите минимальное количество раундов, необходимых для
этого. В противном случае выведите -1.

*/

public class TaskG extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int n = input[0];
            int k = input[1];
            int d = input[2];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("10\n" +
                "11\n" +
                "15\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("1\n" +
                "2\n" +
                "1\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("1\n" +
                "1\n" +
                "1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("25\n" +
                "200\n" +
                "10\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

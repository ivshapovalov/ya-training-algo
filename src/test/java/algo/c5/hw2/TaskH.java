package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/*
https://contest.yandex.ru/contest/59540/problems/H/
Наилучший запрет

Ограничение времени 	3 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Константин и Михаил играют в настольную игру «Ярость Эльфов». В игре есть n рас и m классов персонажей. Каждый персонаж
характеризуется своими расой и классом. Для каждой расы и каждого класса существует ровно один персонаж такой расы и
такого класса. Сила персонажа i-й расы и j-го класса равна ai j, и обоим игрокам это прекрасно известно.

Сейчас Константин будет выбирать себе персонажа. Перед этим Михаил может запретить одну расу и один класс, чтобы
Константин не мог выбирать персонажей, у которых такая раса или такой класс. Конечно же, Михаил старается, чтобы
Константину достался как можно более слабый персонаж, а Константин, напротив, выбирает персонажа посильнее. Какие расу и
класс следует запретить Михаилу?

Формат ввода
Первая строка содержит два целых числа n и m (2 ≤ n,m ≤ 1000) через пробел — количество рас и классов в игре «Ярость
Эльфов», соответственно.

В следующих n строках содержится по m целых чисел через пробел. j-е число i-й из этих строк — это ai j (1 ≤ ai j ≤ 109).

Формат вывода
В единственной строке выведите два целых числа через пробел — номер расы и номер класса, которые следует запретить
Михаилу. Расы и классы нумеруются с единицы. Если есть несколько возможных ответов, выведите любой из них.


*/

public class TaskH extends ContestTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // количество рас
        int m = scanner.nextInt(); // количество классов
        long[][] power = new long[n][m]; // сила персонажей
        long[] maxPowerByRace = new long[n]; // максимальная сила по каждой расе
        long[] minPowerByClass = new long[m]; // минимальная сила по каждому классу

        // Считываем силу персонажей
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                power[i][j] = scanner.nextLong();
                maxPowerByRace[i] = Math.max(maxPowerByRace[i], power[i][j]);
                if (i == 0 || power[i][j] < minPowerByClass[j]) {
                    minPowerByClass[j] = power[i][j];
                }
            }
        }

        // Находим максимальное из минимальных значений силы класса и минимальное из максимальных значений силы расы
        long maxOfMinPowers = minPowerByClass[0];
        long minOfMaxPowers = maxPowerByRace[0];
        int maxMinClass = 1;
        int minMaxRace = 1;

        for (int i = 1; i < m; i++) {
            if (minPowerByClass[i] > maxOfMinPowers) {
                maxOfMinPowers = minPowerByClass[i];
                maxMinClass = i + 1;
            }
        }

        for (int i = 1; i < n; i++) {
            if (maxPowerByRace[i] < minOfMaxPowers) {
                minOfMaxPowers = maxPowerByRace[i];
                minMaxRace = i + 1;
            }
        }

        System.out.println(minMaxRace + " " + maxMinClass);
    }

    @Test
    public void test_001() {
        provideConsoleInput("2 2\n" +
                "1 2\n" +
                "3 4\n");
        main(new String[0]);
        String expected = "2 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("3 4\n" +
                "1 3 5 7\n" +
                "9 11 2 4\n" +
                "6 8 10 12\n");
        main(new String[0]);
        String expected = "3 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

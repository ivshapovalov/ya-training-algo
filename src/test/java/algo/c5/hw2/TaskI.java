package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/59540/problems/I/
Пираты Баренцева моря

Ограничение времени 	3 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вася играет в настольную игру «Пираты Баренцева моря», которая посвящена морским битвам. Игровое поле представляет
 собой квадрат из N×N клеток, на котором расположено N кораблей (каждый корабль занимает одну клетку).

Вася решил воспользоваться линейной тактикой, для этого ему необходимо выстроить все N кораблей в одном столбце. За один
ход можно передвинуть один корабль в одну из четырёх соседних по стороне клеток. Номер столбца, в котором будут
выстроены корабли, не важен. Определите минимальное количество ходов, необходимых для построения кораблей в одном
столбце. В начале и процессе игры никакие два корабля не могут находиться в одной клетке.

Формат ввода
В первой строке входных данных задаётся число N (1≤N≤100).

В каждой из следующих N строк задаются координаты корабля: сначала номер строки, затем номер столбца (нумерация
начинается с единицы).

Формат вывода
Выведите одно число — минимальное количество ходов, необходимое для построения.

Примечания
В примере необходимо выстроить корабли в столбце номер 2. Для этого необходимо переставить корабль из клетки
3 3 в клетку 3 2 за один ход, а корабль из клетки 1 1 в клетку 2 2 за два хода. Существуют и другие варианты
перестановки кораблей, однако ни в одном из них нет меньше трёх ходов.


*/

public class TaskI extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            List<int[]> ships = new ArrayList<>();
            int minY = Integer.MAX_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int[] coords = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
                ships.add(coords);
                minY = Math.min(minY, coords[1]);
                maxY = Math.max(maxY, coords[1]);
            }

            while (minY < maxY) {
                int midY = (minY + maxY) / 2;
                if (totalMoves(n, midY, ships) < totalMoves(n, midY + 1, ships)) {
                    maxY = midY;
                } else {
                    minY = midY + 1;
                }
            }

            System.out.println(totalMoves(n, minY, ships));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int totalMoves(int n, int column, List<int[]> ships) {
        int moves = 0;
        Collections.sort(ships, Comparator.comparingInt(o -> o[0]));
        for (int i = 1; i <= n; i++) {
            int[] ship = ships.get(i - 1);
            int curRow = ship[0];
            int curCol = ship[1];
            int horizontalMove = Math.abs(column - curCol);
            int verticalMove = Math.abs(i - curRow);
            moves = moves + horizontalMove + verticalMove;
        }

        return moves;
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "1 1\n" +
                "2 1\n" +
                "3 1\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "1 1\n" +
                "1 2\n" +
                "1 3\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n" +
                "1 1\n" +
                "1 2\n" +
                "1 4\n" +
                "1 3\n");
        main(new String[0]);
        String expected = "10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("3\n" +
                "1 2\n" +
                "3 3\n" +
                "1 1\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_015() {
        provideConsoleInput("10\n" +
                "4 4\n" +
                "10 2\n" +
                "5 5\n" +
                "5 1\n" +
                "1 8\n" +
                "9 3\n" +
                "9 6\n" +
                "8 5\n" +
                "1 9\n" +
                "4 5\n");
        main(new String[0]);
        String expected = "23\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_021() {
        provideConsoleInput("20\n" +
                "16 8\n" +
                "17 15\n" +
                "9 3\n" +
                "15 16\n" +
                "10 20\n" +
                "19 4\n" +
                "4 2\n" +
                "6 10\n" +
                "18 18\n" +
                "15 7\n" +
                "11 14\n" +
                "2 13\n" +
                "20 13\n" +
                "18 3\n" +
                "6 18\n" +
                "15 6\n" +
                "8 3\n" +
                "14 9\n" +
                "2 12\n" +
                "17 7");
        main(new String[0]);
        String expected = "129\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_023() {
        provideConsoleInput("20\n" +
                "13 19\n" +
                "3 13\n" +
                "20 19\n" +
                "12 8\n" +
                "20 15\n" +
                "6 10\n" +
                "6 9\n" +
                "3 19\n" +
                "7 17\n" +
                "6 3\n" +
                "18 18\n" +
                "5 15\n" +
                "13 15\n" +
                "9 1\n" +
                "11 3\n" +
                "9 17\n" +
                "15 10\n" +
                "18 11\n" +
                "4 14\n" +
                "16 4\n");
        main(new String[0]);
        String expected = "108\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

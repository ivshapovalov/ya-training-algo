package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/*
https://contest.yandex.ru/contest/45468/problems/38/
Блохи

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На клеточном поле, размером NxM (2 ≤ N, M ≤ 250) сидит Q (0 ≤ Q ≤ 10000) блох в различных клетках. "Прием пищи" блохами
возможен только в кормушке - одна из клеток поля, заранее известная. Блохи перемещаются по полю странным образом, а
именно, прыжками, совпадающими с ходом обыкновенного шахматного коня. Длина пути каждой блохи до кормушки определяется
как количество прыжков. Определить минимальное значение суммы длин путей блох до кормушки или, если собраться блохам у
кормушки невозможно, то сообщить об этом. Сбор невозможен, если хотя бы одна из блох не может попасть к кормушке.

Формат ввода
В первой строке входного файла находится 5 чисел, разделенных пробелом: N, M, S, T, Q. N, M - размеры доски (отсчет
начинается с 1); S, T - координаты клетки - кормушки (номер строки и столбца соответственно), Q - количество блох на
доске. И далее Q строк по два числа - координаты каждой блохи.

Формат вывода
Содержит одно число - минимальное значение суммы длин путей или -1, если сбор невозможен.
*/
public class Task38 extends ContestTask {
    static int[][] field;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int N = input[0];
            int M = input[1];
            int S = input[2];
            int T = input[3];
            int Q = input[4];
            int sum = 0;
            field = new int[N + 1][M + 1];
            bfs(N, M, S, T);
            for (int i = 1; i <= Q; i++) {
                int[] flea = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int jumps = field[flea[0]][flea[1]];
                if (jumps == 0 && !(flea[0] == S && flea[1] == T)) {
                    sum = -1;
                    break;
                } else {
                    sum += jumps;
                }
            }
            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bfs(int N, int M, int S, int T) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{S, T, 0});
        int[][] dirs = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int curJumps = current[2];
            int previousVisit = field[row][col];
            if (curJumps == 0 || (previousVisit == 0 && !(row == S && col == T)) || previousVisit > curJumps) {
                field[row][col] = curJumps;
                for (int[] dir : dirs) {
                    int rowNext = row + dir[0];
                    int colNext = col + dir[1];
                    if (rowNext < 1 || rowNext > N || colNext < 1 || colNext > M || (field[rowNext][colNext] != 0 && field[rowNext][colNext] < curJumps + 1))
                        continue;
                    queue.offer(new int[]{rowNext, colNext, curJumps + 1});
                }
            }
        }
    }

    private static int bfsTL(int N, int M, int S, int T, int rowFlea, int colFlea) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{S, T, 0});
        int jumps = Integer.MAX_VALUE;
        int[][] dirs = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int curJumps = current[2];
            int previousVisit = field[row][col];
            if ((row == rowFlea && col == colFlea) || previousVisit != 0) {
                jumps = Math.min(jumps, curJumps);
            } else {
                if (curJumps == 0 || (previousVisit == 0 && !(row == S && col == T)) || previousVisit > curJumps) {
                    field[row][col] = curJumps;
                    for (int[] dir : dirs) {
                        int rowNext = row + dir[0];
                        int colNext = col + dir[1];
                        if (rowNext < 1 || rowNext > N || colNext < 1 || colNext > M || (field[rowNext][colNext] != 0 && field[rowNext][colNext] < curJumps + 1))
                            continue;
                        queue.offer(new int[]{rowNext, colNext, curJumps + 1});
                    }
                }

            }
        }
        return jumps == Integer.MAX_VALUE ? -1 : jumps;
    }

    @Test
    public void test_01() {
        provideConsoleInput("2 2 1 1 1\n" +
                "2 2\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 4 1 1 16\n" +
                "1 1\n" +
                "1 2\n" +
                "1 3\n" +
                "1 4\n" +
                "2 1\n" +
                "2 2\n" +
                "2 3\n" +
                "2 4\n" +
                "3 1\n" +
                "3 2\n" +
                "3 3\n" +
                "3 4\n" +
                "4 1\n" +
                "4 2\n" +
                "4 3\n" +
                "4 4\n");
        main(new String[0]);
        String expected = "42\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

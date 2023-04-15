package ru.algo.ya.c1.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27472/problems/I/
Сапер
*/

public class TaskI extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            List<Integer> input =
                    Arrays.stream(line.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            int rows = input.get(0);
            int cols = input.get(1);
            int minesAmount = input.get(2);
            String[][] board = new String[rows + 2][cols + 2];
            for (int i = 0; i < minesAmount; i++) {
                line = r.readLine();
                int[] coords = Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).toArray();
                board[coords[0]][coords[1]] = "*";
            }
            int[] dirX = {-1, 0, 1};
            int[] dirY = {-1, 0, 1};
            for (int row = 1; row <= rows; row++) {
                for (int col = 1; col <= cols; col++) {
                    if (!"*".equals(board[row][col])) {
                        int mineCounter = 0;
                        for (int x : dirX) {
                            for (int y : dirY) {
                                if (x == 0 && y == 0) continue;
                                if ("*".equals(board[row + y][col + x])) {
                                    mineCounter++;
                                }
                            }
                        }
                        board[row][col] = String.valueOf(mineCounter);
                    }
                    System.out.print(board[row][col] + ((col != cols) ? " " : ""));
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 2 2\n" +
                "1 1\n" +
                "2 2\n");
        main(new String[0]);
        String expected = "* 2\n" +
                "2 *\n" +
                "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2 2 0\n");
        main(new String[0]);
        String expected = "0 0\n" +
                "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4 4 4\n" +
                "1 3\n" +
                "2 1\n" +
                "4 2\n" +
                "4 4\n");
        main(new String[0]);
        String expected = "1 2 * 1\n" +
                "* 2 1 1\n" +
                "2 2 2 1\n" +
                "1 * 2 *\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

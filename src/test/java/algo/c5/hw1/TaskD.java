package algo.c5.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59539/problems/D/
Слоны и ладьи

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На шахматной доске стоят слоны и ладьи, необходимо посчитать, сколько клеток не бьется ни одной из фигур.

Шахматная доска имеет размеры 8 на 8. Ладья бьет все клетки горизонтали и вертикали, проходящих через клетку, где она
стоит, до первой встретившейся фигуры. Слон бьет все клетки обеих диагоналей, проходящих через клетку, где он стоит, до
первой встретившейся фигуры. Формат ввода

В первых восьми строках ввода описывается шахматная доска. Первые восемь символов каждой из этих строк описывают
состояние соответствующей горизонтали: символ B (заглавная латинская буква) означает, что в клетке стоит слон, символ R
— ладья, символ * — что клетка пуста. После описания горизонтали в строке могут идти пробелы, однако длина каждой строки
не превышает 250 символов. После описания доски в файле могут быть пустые строки. Формат вывода

Выведите количество пустых клеток, которые не бьются ни одной из фигур.

*/

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String[][] grid = new String[8][8];
            for (int i = 0; i < 8; i++) {
                String[] line = r.readLine().split("");
                grid[i] = line;
            }
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    //ладья
                    if (grid[row][col].equals("R")) {
                        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
                        for (int[] dir : dirs) {
                            checkDirection(grid, row, col, dir);
                        }
                    } else if (grid[row][col].equals("B")) {
                        int[][] dirs = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
                        for (int[] dir : dirs) {
                            checkDirection(grid, row, col, dir);
                        }
                    }

                }
            }
            System.out.println(Arrays.stream(grid).mapToLong(line -> Arrays.stream(line).filter(el -> el.equals("*")).count()).sum());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkDirection(String[][] grid, int row, int col, int[] dir) {
        while (true) {
            row = row + dir[0];
            col = col + dir[1];
            if (row > 7 || col > 7 || row < 0 || col < 0) break;
            if (!grid[row][col].equals("R") && !grid[row][col].equals("B")) {
                grid[row][col] = "+";
            } else {
                break;
            }
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("********\n" +
                "********\n" +
                "*R******\n" +
                "********\n" +
                "********\n" +
                "********\n" +
                "********\n" +
                "********\n");
        main(new String[0]);
        String expected = "49\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("********\n" +
                "********\n" +
                "******B*\n" +
                "********\n" +
                "********\n" +
                "********\n" +
                "********\n" +
                "********\n");
        main(new String[0]);
        String expected = "54\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("********\n" +
                "*R******\n" +
                "********\n" +
                "*****B**\n" +
                "********\n" +
                "********\n" +
                "********\n" +
                "********\n");
        main(new String[0]);
        String expected = "40\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

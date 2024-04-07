package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/*
https://contest.yandex.ru/contest/45468/problems/39/
Путь спелеолога

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Пещера представлена кубом, разбитым на N частей по каждому измерению (то есть на N3 кубических клеток). Каждая клетка
может быть или пустой, или полностью заполненной камнем. Исходя из положения спелеолога в пещере, требуется найти, какое
минимальное количество перемещений по клеткам ему требуется, чтобы выбраться на поверхность. Переходить из клетки в
клетку можно, только если они обе свободны и имеют общую грань.

Формат ввода
В первой строке содержится число N (1 ≤ N ≤ 30). Далее следует N блоков. Блок состоит из пустой строки и N строк по N
символов: # - обозначает клетку, заполненную камнями, точка - свободную клетку. Начальное положение спелеолога
обозначено заглавной буквой S. Первый блок представляет верхний уровень пещеры, достижение любой свободной его клетки
означает выход на поверхность. Выход на поверхность всегда возможен.

Формат вывода
Вывести одно число - длину пути до поверхности.
*/
public class Task39 extends ContestTask {
    static int[][][] field;
    static int N;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            N = input[0];
            field = new int[N][N][N];
            Block begin = new Block(0, 0, 0, 0);
            for (int layer = 0; layer < N; layer++) {
                reader.readLine();
                for (int row = 0; row < N; row++) {
                    String line = reader.readLine().trim();
                    for (int col = 0; col < line.length(); col++) {
                        char cur = line.charAt(col);
                        if (cur == 'S') {
                            field[layer][row][col] = -1;
                            begin = new Block(layer, row, col, 0);
                        } else {
                            field[layer][row][col] = (cur == '.' ? 0 : -1);
                        }
                    }
                }
            }

            int step = bfs(begin);
            System.out.println(step);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int bfs(Block begin) {
        Queue<Block> queue = new LinkedList<>();
        queue.add(begin);
        int[][] dirs = {{0, 0, -1}, {0, 0, 1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
        int step = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Block current = queue.poll();
            int prev = field[current.getLayer()][current.getRow()][current.getCol()];
            if (current.getStep() == 0 || prev == 0 || (prev != 0 && prev > current.getStep())) {
                field[current.getLayer()][current.getRow()][current.getCol()] = current.getStep();
                if (current.getLayer() == 0) {
                    step = Math.min(step, current.getStep());
                    field[current.getLayer()][current.getRow()][current.getCol()] = current.getStep();
                } else {
                    for (int[] dir : dirs) {
                        int newLayer = current.getLayer() + dir[0];
                        int newRow = current.getRow() + dir[1];
                        int newCol = current.getCol() + dir[2];
                        if (newLayer < 0 || newRow < 0 || newCol < 0 || newLayer >= N || newRow >= N || newCol >= N || field[newLayer][newRow][newCol] == -1)
                            continue;
                        queue.add(new Block(newLayer, newRow, newCol, current.getStep() + 1));
                    }
                }
            }
        }
        return step;
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "\n" +
                "###\n" +
                "###\n" +
                ".##\n" +
                "\n" +
                ".#.\n" +
                ".#S\n" +
                ".#.\n" +
                "\n" +
                "###\n" +
                "...\n" +
                "###\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Block {
        int layer;
        int row;
        int col;
        int step;

        public Block(int layer, int row, int col, int step) {
            this.layer = layer;
            this.row = row;
            this.col = col;
            this.step = step;
        }

        public int getLayer() {
            return layer;
        }

        public void setLayer(int layer) {
            this.layer = layer;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }
    }
}

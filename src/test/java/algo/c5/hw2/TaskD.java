package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://contest.yandex.ru/contest/59540/problems/D/
Шахматная доска

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Из шахматной доски по границам клеток выпилили связную (не распадающуюся на части) фигуру без дыр. Требуется определить
ее периметр.

Формат ввода
Сначала вводится число N (1 ≤ N ≤ 64) – количество выпиленных клеток. В следующих N строках вводятся координаты
выпиленных клеток, разделенные пробелом (номер строки и столбца – числа от 1 до 8). Каждая выпиленная клетка указывается
один раз.

Формат вывода
Выведите одно число – периметр выпиленной фигуры (сторона клетки равна единице).

*/

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            int[][] grid = new int[8 + 2][8 + 2];
            List<int[]> coorList = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                int[] coords = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
                grid[coords[0]][coords[1]] = 1;
                coorList.add(coords);
            }
            int perimeter = 0;
            for (int[] coords : coorList) {
                int adjCounter = countAdj(grid, coords);
                switch (adjCounter) {
                    case 1:
                        perimeter += 3;
                        break;
                    case 2:
                        perimeter += 2;
                        break;
                    case 3:
                        perimeter++;
                        break;
                    case 0:
                        perimeter += 4;
                        break;
                    default:
                        break;
                }
            }
            System.out.println(perimeter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countAdj(int[][] grid, int[] coords) {
        int row = coords[0];
        int col = coords[1];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int adjCounter = 0;
        for (int[] dir : dirs) {
            int rowAdj = row + dir[0];
            int colAdj = col + dir[1];
            if (grid[rowAdj][colAdj] == 1) {
                adjCounter++;
            }
        }
        return adjCounter;
    }


    @Test
    public void test_001() {
        provideConsoleInput("3\n" +
                "1 1\n" +
                "1 2\n" +
                "2 1\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("1\n" +
                "8 8\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

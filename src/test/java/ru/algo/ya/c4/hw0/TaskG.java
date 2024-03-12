package ru.algo.ya.c4.hw0;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;
import ru.algo.ya.c4.hw2.TaskC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53027/problems/G/
Кролик учит геометрию

Ограничение времени 	4 секунды
Ограничение памяти 	80Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Кролики очень любопытны. Они любят изучать геометрию, бегая по грядкам. Наш кролик как раз такой. Сегодня он решил
изучить новую фигуру — квадрат.

Кролик бегает по грядке — клеточному полю N × M клеток. В некоторых из них посеяны морковки, в некоторых нет.

Помогите кролику найти сторону квадрата наибольшей площади, заполненного морковками полностью.

Формат ввода
В первой строке даны два натуральных числа N и M (, ). Далее в N строках расположено по M чисел, разделенных пробелами
(число равно 0, если в клетке нет морковки или 1, если есть).

Формат вывода
Выведите одно число — сторону наибольшего квадрата, заполненного морковками.

*/

public class TaskG extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
            int N = input[0];
            int M = input[1];
            int[][] sum = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                int[] row = Arrays.stream(r.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.parseInt(el)).toArray();
                int[] rowSum = new int[M + 1];
                for (int j = 1; j <= row.length; j++) {
                    int val = row[j - 1];
                    if (j == 1) {
                        rowSum[j] = val;
                        sum[i][j] = sum[i - 1][j] + val;
                    } else {
                        rowSum[j] = rowSum[j - 1] + val;
                        if (i == 1) {
                            sum[i][j] = rowSum[j];
                        } else {
                            sum[i][j] = sum[i - 1][j] + rowSum[j];
                        }
                    }
                }
            }
            int result=0;
            for (int row = 1; row <= N; row++) {
                for (int col = 1; col <= M; col++) {
                    int len=binarySearch(sum,row,col);
                    result=Math.max(result,len);
                }
            }
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int binarySearch(int sum[][], int row, int col) {
        int rows=sum.length;
        int cols=sum[0].length;
        int left = 1;
        int right = Math.min(rows-row,cols-col);
        while (left <= right) {
            int mid = (int) Math.ceil(1.0 * (left + right) / 2);
            if (mid == 0) break;
            if (isQuadrateFull(sum, row,col, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left == 0 ? 0 : right;

    }

    private static boolean isQuadrateFull(int[][] sum, int rowLeftUp, int colLeftUp, int mid) {
        int rowRightDown = rowLeftUp+mid-1;
        int colRightDown = colLeftUp+mid-1;
        int actualSum = sum[rowRightDown][colRightDown] - sum[rowLeftUp - 1][colRightDown] - sum[rowRightDown][colLeftUp - 1] + sum[rowLeftUp - 1][colLeftUp - 1];
        return actualSum==mid*mid;
    }

    @Test
    public void test_01() {
        provideConsoleInput("4 5\n" +
                "0 0 0 1 0\n" +
                "0 1 1 1 0\n" +
                "0 0 1 1 0\n" +
                "1 0 1 0 0\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10 10\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n");
        main(new String[0]);
        String expected = "10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10 10\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

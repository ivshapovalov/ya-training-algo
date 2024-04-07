package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/45468/problems/9/
Сумма в прямоугольнике

Ограничение времени 	3 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вам необходимо ответить на запросы узнать сумму всех элементов числовой матрицы N×M в прямоугольнике с левым верхним
углом (x1, y1) и правым нижним (x2, y2)

Формат ввода
В первой строке находится числа N, M размеры матрицы (1 ≤ N, M ≤ 1000) и K — количество запросов (1 ≤ K ≤ 100000).
Каждая из следующих N строк содержит по M чисел`— элементы соответствующей строки матрицы (по модулю не превосходят
1000). Последующие K строк содержат по 4 целых числа, разделенных пробелом x1 y1 x2 y2 — запрос на сумму элементов
матрице в прямоугольнике (1 ≤ x1 ≤ x2 ≤ N, 1 ≤ y1 ≤ y2 ≤ M)

Формат вывода
Для каждого запроса на отдельной строке выведите его результат — сумму всех чисел в элементов матрице в прямоугольнике
(x1, y1), (x2, y2)

*/

public class Task09 extends ContestTask {
    private static final String TEST_PATH = "/src/test/java/algo/c3/task09/";

    public static void main2(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] vars = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = vars[0];
            int m = vars[1];
            int k = vars[2];
            int[][] mat = new int[n + 1][m + 1];
            int[][] rowSum = new int[n + 1][m + 1];
            int[][] colSum = new int[n + 1][m + 1];
            int[][] sum = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                int[] row = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                for (int j = 1; j <= row.length; j++) {
                    int val = row[j - 1];
                    mat[i][j] = val;
                    colSum[i][j] = colSum[i - 1][j] + val;
                    if (j == 1) {
                        rowSum[i][j] = val;
                        sum[i][j] = sum[i - 1][j] + val;
                    } else {
                        rowSum[i][j] = rowSum[i][j - 1] + val;
                        if (i == 1) {
                            sum[i][j] = rowSum[i][j];
                        } else {
                            sum[i][j] = sum[i - 1][j] + rowSum[i][j];
                        }
                    }
                }
            }
            for (int i = 0; i < k; i++) {
                int[] coords = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int row1 = coords[0];
                int col1 = coords[1];
                int row2 = coords[2];
                int col2 = coords[3];
                int res = 0;
                if (row1 == row2 && col1 == col2) {
                    res = mat[row1][col1];
                } else if (row1 == row2 && col1 != col2) {
                    res = rowSum[row2][col2] - rowSum[row1][col1] + mat[row1][col1];
                } else if (row1 != row2 && col1 == col2) {
                    res = colSum[row2][col2] - colSum[row1][col1] + mat[row1][col1];
                } else if (row1 != row2 && col1 != col2) {
                    res = sum[row2][col2] - sum[row1][col2] - sum[row2][col1] + sum[row1][col1];
                }
                System.out.println(res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            int k = input[2];
            int[][] sum = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                int[] row = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int[] rowSum = new int[m + 1];
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
            for (int i = 0; i < k; i++) {
                int[] coords = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int row1 = coords[0];
                int col1 = coords[1];
                int row2 = coords[2];
                int col2 = coords[3];
                int res = sum[row2][col2] - sum[row1 - 1][col2] - sum[row2][col1 - 1] + sum[row1 - 1][col1 - 1];
                System.out.println(res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 3 3\n" +
                "1 2 3\n" +
                "4 5 6\n" +
                "7 8 9\n" +
                "2 2 3 3\n" +
                "1 1 1 3\n" +
                "1 1 2 3\n");
        main(new String[0]);
        String expected = "28\n" +
                "6\n" +
                "21\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 4 4\n" +
                "1 2 3 4\n" +
                "5 6 7 8\n" +
                "9 10 11 12\n" +
                "13 14 15 16\n" +
                "2 2 3 3\n" +
                "1 2 1 3\n" +
                "2 2 2 2\n" +
                "1 2 3 2\n");
        main(new String[0]);
        String expected = "34\n" +
                "5\n" +
                "6\n" +
                "18\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10 20 15\n" +
                "-730 -468 -700 -997 518 561 263 399 852 564 -447 627 -838 735 -441 -181 536 75 465 449 \n" +
                "303 791 -687 653 -471 637 829 493 -555 -576 -414 -285 -486 -557 -725 -969 -996 538 -12 413 \n" +
                "-342 541 39 -180 276 156 197 -189 -770 -339 817 90 -549 -871 -257 -20 323 -872 -971 768 \n" +
                "-891 -384 -960 -377 -384 -685 -345 -380 411 643 -968 -932 -260 -371 -111 -985 785 -915 -617 572 \n" +
                "-254 757 -338 197 887 -38 734 209 90 764 -466 199 937 -426 823 -448 -111 -966 173 857 \n" +
                "677 -238 926 -584 391 -186 990 -268 -544 930 862 202 687 -477 956 130 42 246 -662 -869 \n" +
                "9 -571 -113 -498 3 -291 55 450 -257 785 306 977 -897 231 951 495 602 497 -774 -943 \n" +
                "-17 645 -184 -331 -833 -672 356 209 575 695 -103 -860 681 785 -357 -316 -950 255 691 -649 \n" +
                "-404 -4 885 -301 785 392 -807 -57 -112 978 -442 429 622 -69 655 347 260 11 -888 -609 \n" +
                "-738 -433 -469 943 -649 -269 -374 959 986 -684 -691 -862 -130 752 -162 -346 143 -412 154 589 \n" +
                "1 1 1 10\n" +
                "1 1 1 20\n" +
                "1 1 1 1\n" +
                "1 10 1 20\n" +
                "1 1 5 1\n" +
                "1 1 10 1\n" +
                "5 1 10 1\n" +
                "10 20 10 20\n" +
                "10 1 10 10\n" +
                "10 1 10 20\n" +
                "10 1 10 1\n" +
                "10 10 10 20\n" +
                "1 20 5 20\n" +
                "1 20 10 20\n" +
                "5 20 10 20\n");
        main(new String[0]);
        String expected = "262\n" +
                "1242\n" +
                "-730\n" +
                "1544\n" +
                "-1914\n" +
                "-2387\n" +
                "-727\n" +
                "589\n" +
                "-728\n" +
                "-1693\n" +
                "-738\n" +
                "-1649\n" +
                "3059\n" +
                "578\n" +
                "-1624\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f3() throws FileNotFoundException {
        String testNumber = "03";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f8() throws FileNotFoundException {
        String testNumber = "03";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

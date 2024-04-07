package algo.c1.hw5;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;


/*
https://contest.yandex.ru/contest/27794/problems/J/
Треугольники
*/
public class TaskJ extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine());
            int[][] points = new int[n][2];
            for (int i = 0; i < n; i++) {
                int[] point =
                        Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                points[i] = point;
            }
            int result = 0;
            for (int p1 = 0; p1 < points.length; p1++) {
                for (int p2 = p1 + 1; p2 < points.length; p2++) {
                    for (int p3 = p2 + 1; p3 < points.length; p3++) {
                        //Sabc=1/2 |(x2 – x1)(y3 –y1) – (x3 – x1)(y2 – y1)|.

                        double s = 0.5 * (Math.abs(((points[p2][0] - points[p1][0]) * (points[p3][1] - points[p1][1]))
                                - ((points[p3][0] - points[p1][0]) * (points[p2][1] - points[p1][1]))));
                        if (s <= 0) continue;
                        double edge12 =
                                Math.pow(points[p1][0] - points[p2][0], 2) + Math.pow(points[p1][1] - points[p2][1], 2);
                        double edge13 =
                                Math.pow(points[p1][0] - points[p3][0], 2) + Math.pow(points[p1][1] - points[p3][1], 2);
                        double edge23 =
                                Math.pow(points[p2][0] - points[p3][0], 2) + Math.pow(points[p2][1] - points[p3][1], 2);
                        if (edge12 == edge13 || edge12 == edge23 || edge23 == edge13) {
                            result++;
                        }
                    }
                }
            }
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "0 0\n" +
                "2 2\n" +
                "-2 2\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4\n" +
                "0 0\n" +
                "1 1\n" +
                "1 0\n" +
                "0 1\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f13() throws FileNotFoundException {
        String testNumber = "013";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "2690027168\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}

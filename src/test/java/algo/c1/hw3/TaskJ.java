package algo.c1.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://contest.yandex.ru/contest/27663/problems/J/
Пробежки по Манхеттену
*/

public class TaskJ extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int t = input[0];
            int d = input[1];
            int n = input[2];

            int[] posRect = {0, 0, 0, 0};
            for (int i = 0; i < n; i++) {
                extend(posRect, t);
                int[] nav =
                        Arrays.stream(r.readLine().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int[] navRect = {nav[0] + nav[1], nav[0] + nav[1], nav[0] - nav[1], nav[0] - nav[1]};
                extend(navRect, d);
                posRect = intersect(posRect, navRect);
            }
            List<int[]> coords = new ArrayList<>();
            for (int xPlusY = posRect[0]; xPlusY < posRect[1] + 1; xPlusY++) {
                for (int xMinusY = posRect[2]; xMinusY < posRect[3] + 1; xMinusY++) {
                    if ((xPlusY + xMinusY) % 2 == 0) {
                        int x = (xPlusY + xMinusY) / 2;
                        int y = xPlusY - x;
                        coords.add(new int[]{x, y});
                    }
                }
            }
            System.out.println(coords.size());
            coords.stream().forEach(el -> System.out.println(el[0] + " " + el[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void extend(int[] coords, int d) {
        coords[0] -= d;
        coords[1] += d;
        coords[2] -= d;
        coords[3] += d;
    }

    static int[] intersect(int[] rect1, int[] rect2) {
        int[] result = {
                Math.max(rect1[0], rect2[0]),
                Math.min(rect1[1], rect2[1]),
                Math.max(rect1[2], rect2[2]),
                Math.min(rect1[3], rect2[3])
        };
        return result;
    }

    @Test
    public void test_01() {
        provideConsoleInput("2 1 5\n" +
                "0 1\n" +
                "-2 1\n" +
                "-2 3\n" +
                "0 3\n" +
                "2 5\n");
        main(new String[0]);
        String expected = "2\n" +
                "1 5\n" +
                "2 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 1 1\n" +
                "0 0\n");
        main(new String[0]);
        String expected = "5\n" +
                "-1 0\n" +
                "0 -1\n" +
                "0 0\n" +
                "0 1\n" +
                "1 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1 10 1\n" +
                "0 0\n");
        main(new String[0]);
        String expected = "5\n" +
                "-1 0\n" +
                "0 -1\n" +
                "0 0\n" +
                "0 1\n" +
                "1 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

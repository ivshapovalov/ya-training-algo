package ru.algo.ya.c1.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
https://contest.yandex.ru/contest/27794/problems/C/
Туризм
*/
public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.valueOf(r.readLine());
            int[][] points = new int[N + 1][2];
            for (int i = 1; i < N + 1; i++) {
                int[] point =
                        Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                points[i] = point;
            }
            int M = Integer.valueOf(r.readLine());
            int[][] vertex = new int[M + 1][2];
            for (int i = 1; i < M + 1; i++) {
                vertex[i] =
                        Arrays.stream(r.readLine().trim().split(" "))
                                .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            }
            int[][] diff = new int[N + 1][2];
            diff[1] = new int[]{0, 0};
            for (int i = 2; i < N; i++) {
                diff[i][0] = diff[i - 1][0] + ((points[i][1] > points[i - 1][1]) ? points[i][1] - points[i - 1][1] :
                        0);
                diff[i][1] = diff[i - 1][1] + ((points[i][1] > points[i - 1][1]) ? 0 : points[i - 1][1] - points[i][1]);
            }
            for (int i = 1; i < M + 1; i++) {
                if (vertex[i][1] > vertex[i][0]) {
                    System.out.println(
                            diff[vertex[i][1]][0] - diff[vertex[i][0]][0] > 0 ?
                                    diff[vertex[i][1]][0] - diff[vertex[i][0]][0] : 0);
                } else {
                    System.out.println(diff[vertex[i][0]][1] - diff[vertex[i][1]][1] > 0 ?
                            diff[vertex[i][0]][1] - diff[vertex[i][1]][1] : 0);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_1() {
        provideConsoleInput("7\n" +
                "2 1\n" +
                "4 5\n" +
                "7 4\n" +
                "8 2\n" +
                "9 6\n" +
                "11 3\n" +
                "15 3\n" +
                "1\n" +
                "2 6\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("6\n" +
                "1 1\n" +
                "3 2\n" +
                "5 6\n" +
                "7 2\n" +
                "10 4\n" +
                "11 1\n" +
                "3\n" +
                "5 6\n" +
                "1 4\n" +
                "4 2\n");
        main(new String[0]);
        String expected = "0\n" +
                "5\n" +
                "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}

package ru.algo.ya.c1.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27393/problems/I/
Узник замка Иф
*/

public class TaskI extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int A = Integer.valueOf(r.readLine().trim());
            int B = Integer.valueOf(r.readLine().trim());
            int C = Integer.valueOf(r.readLine().trim());
            int D = Integer.valueOf(r.readLine().trim());
            int E = Integer.valueOf(r.readLine().trim());

            int holeMin = Math.min(D, E);
            int holeMax = Math.max(D, E);

            int[] brick = new int[]{A, B, C};
            Arrays.sort(brick);

            int brickMin = brick[0];
            int brickMid = brick[1];
            if (brickMin <= holeMin && brickMid <= holeMax) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n1\n1\n1\n1\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n2\n2\n1\n1\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

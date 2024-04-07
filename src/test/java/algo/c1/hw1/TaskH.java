package algo.c1.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27393/problems/H/
Метро
*/

public class TaskH extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int A = Integer.valueOf(r.readLine().trim());
            int B = Integer.valueOf(r.readLine().trim());
            int N = Integer.valueOf(r.readLine().trim());
            int M = Integer.valueOf(r.readLine().trim());

            //a*(n+1)+n
            //a*n+n
            //a*(n-1)+n

            int aMin = A * (N - 1) + N;
            int aMax = A * (N + 1) + N;
            int bMin = B * (M - 1) + M;
            int bMax = B * (M + 1) + M;

            if (aMax < bMin || aMin > bMax) {
                System.out.println("-1");
            } else {
                System.out.println(Math.max(aMin, bMin) + " " + Math.min(aMax, bMax));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n3\n3\n2\n");
        main(new String[0]);
        String expected = "5 7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n5\n1\n2\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

package ru.algo.ya.c1.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27393/problems/J/
Система линейных уравнений - 2
*/
public class TaskJ extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int A = Integer.valueOf(r.readLine().trim());
            int B = Integer.valueOf(r.readLine().trim());
            int C = Integer.valueOf(r.readLine().trim());
            int D = Integer.valueOf(r.readLine().trim());
            int E = Integer.valueOf(r.readLine().trim());
            int F = Integer.valueOf(r.readLine().trim());

            if (D * A == B * C || A == 0) {
                System.out.println(0);
            } else {
                int Y = (F * A - C * E) / (D * A - B - C);
                int X = (E - B * Y) / A;
            }


            //ax+by=e
            //cx+dy=f

            //x=(e-b*y)/a
            //c*(e-b*y)*x/a+dy=f

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        provideConsoleInput("1\n0\n0\n1\n3\n3\n");
        main(new String[0]);
        String expected = "2 3 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test2() {
        provideConsoleInput("1\n1\n2\n2\n1\n2\n");
        main(new String[0]);
        String expected = "1 -1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test3() {
        provideConsoleInput("0\n2\n0\n4\n1\n2\n");
        main(new String[0]);
        String expected = "4 0.5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

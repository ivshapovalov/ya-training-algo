package algo.c1.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27393/problems/B/
Треугольник
*/
public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.valueOf(reader.readLine());
            int b = Integer.valueOf(reader.readLine());
            int c = Integer.valueOf(reader.readLine());

            String result = "NO";
            if (a + b > c && a + c > b && c + b > a) {
                result = "YES";
            }
            System.out.print(result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "4\n" +
                "5\n" +
                "\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "5\n" +
                "4\n" +
                "\n" +
                "\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n" +
                "5\n" +
                "3\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

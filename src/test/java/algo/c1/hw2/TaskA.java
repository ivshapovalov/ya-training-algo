package algo.c1.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27472/problems/A/
Возрастает ли список?
*/

public class TaskA extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            List<Integer> digits = Arrays.stream(line.split(" ")).map(el -> Integer.valueOf(el)).collect(Collectors.toList());
            boolean failed = false;
            for (int i = 1; i < digits.size(); i++) {
                if (digits.get(i) <= digits.get(i - 1)) {
                    failed = true;
                    break;
                }
            }
            if (failed) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_01() {
        provideConsoleInput("1 7 9\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 9 7\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2 2 2\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

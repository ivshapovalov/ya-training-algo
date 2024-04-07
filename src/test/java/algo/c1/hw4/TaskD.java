package algo.c1.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27665/problems/D/
Клавиатура
*/

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(r.readLine());
            Integer[] limits =
                    Arrays.stream(r.readLine().split(" ")).map(el -> Integer.valueOf(el)).toArray(Integer[]::new);
            int k = Integer.valueOf(r.readLine());
            Integer[] buttons =
                    Arrays.stream(r.readLine().split(" ")).map(el -> Integer.valueOf(el)).toArray(Integer[]::new);

            for (int button : buttons) {
                limits[button - 1]--;
            }
            Arrays.stream(limits).forEach(el -> {
                if (el < 0) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "1 50 3 4 3\n" +
                "16\n" +
                "1 2 3 4 5 1 3 3 4 5 5 5 5 5 4 5\n");
        main(new String[0]);
        String expected = "YES\n" +
                "NO\n" +
                "NO\n" +
                "NO\n" +
                "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

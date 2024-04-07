package algo.c1.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://contest.yandex.ru/contest/27472/problems/B/
Определить вид последовательности
*/

public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            List<STATUS> statuses = new ArrayList<>();
            int counter = 0;
            int prev = 0;
            while (line != null) {
                int cur = Integer.valueOf(line);
                if (cur == -2E+9) break;
                if (counter != 0) {
                    if (cur == prev) {
                        statuses.add(STATUS.CONSTANT);
                    } else if (cur > prev) {
                        statuses.add(STATUS.ASCENDING);
                    } else if (cur < prev) {
                        statuses.add(STATUS.DESCENDING);
                    }
                    prev = cur;
                } else {
                    prev = cur;
                }
                line = r.readLine();
                counter++;
            }
            Set<STATUS> set = new HashSet<>(statuses);
            STATUS answer = STATUS.RANDOM;
            if (set.size() == 1) {
                STATUS status = new ArrayList<>(set).get(0);
                answer = status;
            } else if (set.size() == 2) {
                if (set.contains(STATUS.CONSTANT) && set.contains(STATUS.ASCENDING)) {
                    answer = STATUS.WEAKLY_ASCENDING;
                } else if (set.contains(STATUS.CONSTANT) && set.contains(STATUS.DESCENDING)) {
                    answer = STATUS.WEAKLY_DESCENDING;
                }
            }
            System.out.println(answer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("-530\n" +
                "-530\n" +
                "-530\n" +
                "-530\n" +
                "-530\n" +
                "-530\n" +
                "-2000000000\n");
        main(new String[0]);
        String expected = "CONSTANT\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("-530\n" +
                "-530\n" +
                "-530\n" +
                "-540\n" +
                "-530\n" +
                "-530\n" +
                "-2000000000\n");
        main(new String[0]);
        String expected = "RANDOM\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("-530\n" +
                "-530\n" +
                "-530\n" +
                "-530\n" +
                "-540\n" +
                "-550\n" +
                "-2000000000\n");
        main(new String[0]);
        String expected = "WEAKLY DESCENDING\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("5\n" +
                "4\n" +
                "3\n" +
                "-2000000000\n");
        main(new String[0]);
        String expected = "DESCENDING\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("3\n" +
                "4\n" +
                "4\n" +
                "6\n" +
                "-2000000000\n");
        main(new String[0]);
        String expected = "WEAKLY ASCENDING\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    enum STATUS {
        CONSTANT("CONSTANT"),
        ASCENDING("ASCENDING"),
        WEAKLY_ASCENDING("WEAKLY ASCENDING"),
        DESCENDING("DESCENDING"),
        WEAKLY_DESCENDING("WEAKLY DESCENDING"),
        RANDOM("RANDOM");

        String name;

        STATUS(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

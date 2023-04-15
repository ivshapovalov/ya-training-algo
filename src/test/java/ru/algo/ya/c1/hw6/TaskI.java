package ru.algo.ya.c1.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/I/
Субботник
*/

public class TaskI extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int r = input[1];
            int c = input[2];
            int[] students = new int[n];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int st = Integer.valueOf(reader.readLine().trim());
                students[i] = st;
                min = Math.min(min, st);
                max = Math.max(max, st);
            }
            Arrays.sort(students);

            int len = binarySearch(students, r, c, max - min);

            System.out.println(len);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int binarySearch(int[] students, int groupsExpected, int amountInGroup, int r) {
        int l = 0;
        while (l < r) {
            int diff = l + ((r - l) / 2);
            if (check(students, groupsExpected, amountInGroup, diff)) {
                r = diff;
            } else {
                l = diff + 1;
            }
        }
        return l;
    }

    private static boolean check(int[] students, int groupsExpected, int amountInGroup, int diff) {
        int groupsActual = 0;
        int i = 0;
        while (i < students.length - amountInGroup + 1) {
            if (students[i + amountInGroup - 1] - students[i] <= diff) {
                groupsActual++;
                i += amountInGroup;
            } else {
                i++;
            }
        }
        return groupsActual >= groupsExpected;
    }

    @Test
    public void test_1() {
        provideConsoleInput("8 2 3\n" +
                "170\n" +
                "205\n" +
                "225\n" +
                "190\n" +
                "260\n" +
                "130\n" +
                "225\n" +
                "160\n\n\n");
        main(new String[0]);
        String expected = "30\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("9 3 3\n" +
                "170\n" +
                "205\n" +
                "225\n" +
                "190\n" +
                "190\n" +
                "260\n" +
                "130\n" +
                "225\n" +
                "160\n\n\n");
        main(new String[0]);
        String expected = "40\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_3() {
        provideConsoleInput("9 3 3\n" +
                "100\n" +
                "130\n" +
                "190\n" +
                "190\n" +
                "205\n" +
                "225\n" +
                "250\n" +
                "250\n" +
                "260\n");
        main(new String[0]);
        String expected = "90\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f20() throws FileNotFoundException {
        String testNumber = "004";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "74\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}

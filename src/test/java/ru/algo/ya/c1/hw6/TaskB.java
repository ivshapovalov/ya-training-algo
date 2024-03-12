package ru.algo.ya.c1.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/B/
Приближенный двоичный поиск
*/

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int k = input[1];
            int[] arr1 =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .filter(el -> !el.isEmpty())
                            .mapToInt(el -> Integer.valueOf(el).intValue())
                            .toArray();
            int[] arr2 =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .filter(el -> !el.isEmpty())
                            .mapToInt(el -> Integer.valueOf(el).intValue())
                            .toArray();

            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < arr2.length; i++) {
                int value = binarySearch(arr1, arr2[i]);
                answer.append(value).append("\n");
            }
            System.out.print(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (r - l > 1) {
            int mid = (l + r + 1) / 2;
            if (target > arr[mid]) {
                l = mid;
            } else {
                r = mid;
            }
        }
        int leftDiff = target - arr[l];
        int rightDiff = arr[r] - target;
        return (leftDiff <= rightDiff) ? arr[l] : arr[r];
    }


    @Test
    public void test_01() {
        provideConsoleInput("5 5\n" +
                "1 3 5 7 9 \n" +
                "2 4 8 1 6 \n");
        main(new String[0]);
        String expected = "1\n" +
                "3\n" +
                "7\n" +
                "1\n" +
                "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("6 11\n" +
                "1 1 4 4 8 120 \n" +
                "1 2 3 4 5 6 7 8 63 64 65 \n");
        main(new String[0]);
        String expected = "1\n" +
                "1\n" +
                "4\n" +
                "4\n" +
                "4\n" +
                "4\n" +
                "8\n" +
                "8\n" +
                "8\n" +
                "8\n" +
                "120\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10 10\n" +
                "-5 1 1 3 5 5 8 12 13 16 \n" +
                "0 3 7 -17 23 11 0 11 15 7 \n");
        main(new String[0]);
        String expected = "1\n" +
                "3\n" +
                "8\n" +
                "-5\n" +
                "16\n" +
                "12\n" +
                "1\n" +
                "12\n" +
                "16\n" +
                "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("5 5\n" +
                "2 3 5 7 9 \n" +
                "1 4 8 1 10 \n");
        main(new String[0]);
        String expected = "2\n" +
                "3\n" +
                "7\n" +
                "2\n" +
                "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f20() throws FileNotFoundException {
        String testNumber = "020";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "2690027168\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

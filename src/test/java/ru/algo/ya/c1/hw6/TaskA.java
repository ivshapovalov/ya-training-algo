package ru.algo.ya.c1.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/A/
Двоичный поиск
*/

public class TaskA extends ContestTask {

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

            for (int i = 0; i < arr2.length; i++) {
                boolean res = binarySearch(arr1, arr2[i]);
                System.out.println(res ? "YES" : "NO");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean binarySearch(int[] arr1, int search) {
        int l = 0;
        int r = arr1.length - 1;
        while (l < r) {
            int mid = l + ((r - l) / 2);
            if (arr1[mid] == search) {
                return true;
            } else if (search < arr1[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return arr1[l] == search;
    }


    @Test
    public void test_01() {
        provideConsoleInput("10 10\n" +
                "1 61 126 217 2876 6127 39162 98126 712687 1000000000 \n" +
                "100 6127 1 61 200 -10000 1 217 10000 1000000000 \n\n");
        main(new String[0]);
        String expected = "NO\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "NO\n" +
                "NO\n" +
                "YES\n" +
                "YES\n" +
                "NO\n" +
                "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10 10\n" +
                "-8 -6 -4 -4 -2 -1 0 2 3 3 \n" +
                "8 3 -3 -2 2 -1 2 9 -8 0 \n\n");
        main(new String[0]);
        String expected = "NO\n" +
                "YES\n" +
                "NO\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "YES\n" +
                "NO\n" +
                "YES\n" +
                "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10 5\n" +
                "1 2 3 4 5 6 7 8 9 10 \n" +
                "-2 0 4 9 12 \n\n\n");
        main(new String[0]);
        String expected = "NO\n" +
                "NO\n" +
                "YES\n" +
                "YES\n" +
                "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

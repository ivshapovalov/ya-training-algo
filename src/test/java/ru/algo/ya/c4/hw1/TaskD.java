package ru.algo.ya.c4.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53029/problems/D/
Сортировка слиянием

Ограничение времени 	15 секунд
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Реализуйте сортировку слиянием, используя алгоритм из предыдущей задачи.

На каждом шаге делите массив на две части, сортируйте их независимо и сливайте с помощью уже реализованной функции.

Формат ввода
В первой строке входного файла содержится число N — количество элементов массива (0 ≤ N ≤ 106).
Во второй строке содержатся N целых чисел ai, разделенных пробелами (-109 ≤ ai ≤ 109).

Формат вывода
Выведите результат сортировки, то есть N целых чисел, разделенных пробелами, в порядке неубывания.

*/

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int N1 = Integer.parseInt(r.readLine().trim());
            int[] array1 = Arrays.stream(r.readLine().trim().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            if (array1.length == 0) {
                System.out.println("");
            } else {
                int[] result = mergeSort(array1);
                System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] mergeSort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(array, mid, array.length));
        return merge(left, right);
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int counter1 = 0;
        int counter2 = 0;
        while (counter1 != arr1.length && counter2 != arr2.length) {
            if (arr1[counter1] < arr2[counter2]) {
                merged[counter1 + counter2] = arr1[counter1];
                counter1++;
            } else {
                merged[counter1 + counter2] = arr2[counter2];
                counter2++;
            }
        }
        if (counter1 == arr1.length) {
            for (int i = counter2; i < arr2.length; i++) {
                merged[i + counter1] = arr2[i];
            }
        } else {
            for (int i = counter1; i < arr1.length; i++) {
                merged[i + counter2] = arr1[i];
            }
        }

        return merged;
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "1 5 2 4 3\n");
        main(new String[0]);
        String expected = "1 2 3 4 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("5\n" +
                "0 0 0 0 0\n");
        main(new String[0]);
        String expected = "0 0 0 0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("5\n" +
                "5 5 5 5 5\n");
        main(new String[0]);
        String expected = "5 5 5 5 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("0\n" +
                "\n");
        main(new String[0]);
        String expected = "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("1\n" +
                "1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_005() {
        provideConsoleInput("5\n" +
                "5 4 3 2 1\n");
        main(new String[0]);
        String expected = "1 2 3 4 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}

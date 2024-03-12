package ru.algo.ya.c4.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53029/problems/A/
Partition

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Базовым алгоритмом для быстрой сортировки является алгоритм partition, который разбивает набор элементов на две части
относительно заданного предиката. По сути элементы массива просто меняются местами так, что левее некоторой точки в нем
после этой операции лежат элементы, удовлетворяющие заданному предикату, а справа — не удовлетворяющие ему. Например,
при сортировке можно использовать предикат «меньше опорного», что при оптимальном выборе опорного элемента может разбить
массив на две примерно равные части.

Напишите алгоритм partition в качестве первого шага для написания быстрой сортировки.

Формат ввода

В первой строке входного файла содержится число N — количество элементов массива (0 ≤ N ≤ 106). Во второй строке
содержатся N целых чисел ai, разделенных пробелами (-109 ≤ ai ≤ 109). В третьей строке содержится опорный элемент x
(-109 ≤ x ≤ 109). Заметьте, что x не обязательно встречается среди ai. Формат вывода

Выведите результат работы вашего алгоритма при использовании предиката «меньше x»: в первой строке выведите число
элементов массива, меньших x, а во второй — количество всех остальных.

*/

public class TaskA extends ContestTask {
    private static int i = 0, j = 0;

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(r.readLine().trim());
            int[] nums = Arrays.stream(r.readLine().trim().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int pivot = Integer.parseInt(r.readLine().trim());
            if (nums.length == 0) {
                System.out.println("0");
                System.out.println("0");
            } else {
                partition(nums, 0, Math.max(0, nums.length - 1), pivot);
                int less = i == 0 ? (nums[i] < pivot ? 1 : 0) : i + 1;
                System.out.println(less);
                System.out.println(nums.length - less);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void partition(int arr[], int left, int right, int pivot) {
        if (right - left <= 1) {
            if (arr[right] < arr[left])
                swap(arr, right, left);
            i = left;
            j = right;
            return;
        }
        int mid = left;
        while (mid <= right) {
            if (arr[mid] < pivot)
                swap(arr, left++, mid++);
            else if (arr[mid] == pivot)
                mid++;
            else if (arr[mid] > pivot)
                swap(arr, mid, right--);
        }
        i = left - 1;
        j = mid;
    }
//    private static int partition(int[] arr, int l, int r, int pivot) {
//        int i = l;
//        int j = r;
//        while (i <= j) {
//            while (i<=arr.length-1 && arr[i] < pivot) {
//                i++;
//            }
//            while (j>=0 && arr[j] >= pivot) {
//                j--;
//            }
//            if (i >= j) {
//                break;
//            }
//            swap(arr, i++, j--);
//
//        }
//        return i;
//
//    }

    private static void swap(int[] arr, int ind1, int ind2) {
        int temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
    }


    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "1 9 4 2 3\n" +
                "3\n");
        main(new String[0]);
        String expected = "2\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("0\n" +
                "\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n" +
                "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1\n" +
                "0\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n" +
                "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("1\n" +
                "0\n" +
                "1\n");
        main(new String[0]);
        String expected = "1\n" +
                "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

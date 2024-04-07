package algo.c4.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53029/problems/B/
Быстрая сортировка

Ограничение времени 	10 секунд
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Реализуйте быструю сортировку, используя алгоритм из предыдущей задачи.

На каждом шаге выбирайте опорный элемент и выполняйте partition относительно него. Затем рекурсивно запуститесь от двух
частей, на которые разбился исходный массив. Формат ввода

В первой строке входного файла содержится число N — количество элементов массива (0 ≤ N ≤ 106). Во второй строке
содержатся N целых чисел ai, разделенных пробелами (-109 ≤ ai ≤ 109). Формат вывода

Выведите результат сортировки, то есть N целых чисел, разделенных пробелами.

*/

public class TaskB extends ContestTask {

    private static int i = 0, j = 0;

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(r.readLine().trim());
            String line = r.readLine();
            if (line == null || line.isEmpty()) {
                System.out.println();
                return;
            }
            int[] nums = Arrays.stream(line.split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();

            if (nums.length == 0) {
                System.out.println();
            } else {
                quickSort(nums, 0, nums.length - 1);
                System.out.println(Arrays.stream(nums).mapToObj(el -> String.valueOf(el)).collect(Collectors.joining(" ")));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void partition(int[] arr, int left, int right) {
        if (right - left <= 1) {
            if (arr[right] < arr[left])
                swap(arr, right, left);
            i = left;
            j = right;
            return;
        }
        int mid = left;
        int randomIndex = ThreadLocalRandom.current().nextInt(left, right + 1);
        int pivot = arr[randomIndex];
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

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right)
            return;
        partition(arr, left, right);
        quickSort(arr, left, i);
        quickSort(arr, j, right);
    }

    private static void quickSortOld(int[] arr, int lo, int hi) {
        if (hi <= lo)
            return;

        int lowerIndex = lo;
        int greaterIndex = hi;
        int element = arr[lo];
        int i = lo;

        while (i <= greaterIndex) {
            if (arr[i] < element)
                swap(arr, lowerIndex++, i++);
            else if (arr[i] > element)
                swap(arr, i, greaterIndex--);
            else
                i++;
        }

        quickSort(arr, lo, lowerIndex - 1);
        quickSort(arr, greaterIndex + 1, hi);
    }

    private static void swap(int[] arr, int ind1, int ind2) {
        int temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
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
    public void test_02() {
        provideConsoleInput("0\n");
        main(new String[0]);
        String expected = "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("4\n" +
                "0 0 0 0\n");
        main(new String[0]);
        String expected = "0 0 0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f20() throws FileNotFoundException {
        String testNumber = "20";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

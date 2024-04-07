package algo.c4.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53029/problems/C/
Слияние

Ограничение времени 	5 секунд
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Базовый алгоритм для сортировки слиянием — алгоритм слияния двух упорядоченных массивов в один упорядоченный массив. Эта
операция выполняется за линейное время с линейным потреблением памяти. Реализуйте слияние двух массивов в качестве
первого шага для написания сортировки слиянием. Формат ввода

В первой строке входного файла содержится число N — количество элементов первого массива (0 ≤ N ≤ 106). Во второй строке
содержатся N целых чисел ai, разделенных пробелами, отсортированные по неубыванию (-109 ≤ ai ≤ 109). В третьей строке
входного файла содержится число M — количество элементов второго массива (0 ≤ M ≤ 106). В третьей строке содежатся M
целых чисел bi, разделенных пробелами, отсортированные по неубыванию (-109 ≤ bi ≤ 109). Формат вывода

Выведите результат слияния этих двух массивов, то есть M + N целых чисел, разделенных пробелами, в порядке неубывания.

*/

public class TaskC extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int N1 = Integer.parseInt(r.readLine().trim());
            int[] array1 = Arrays.stream(r.readLine().trim().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int N2 = Integer.parseInt(r.readLine().trim());
            int[] array2 = Arrays.stream(r.readLine().trim().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();

            int[] result = merge(array1, array2);
            System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] merge(int[]... arrays) {
        int[] result = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            int[] arr2 = arrays[i];
            result = mergeTwoArrays(result, arr2);
        }
        return result;
    }

    private static int[] mergeTwoArrays(int[] arr1, int[] arr2) {
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
            System.arraycopy(arr2, counter2, merged, counter2 + counter1, arr2.length - counter2);
        } else {
            System.arraycopy(arr1, counter1, merged, counter1 + counter2, arr1.length - counter1);
        }

        return merged;
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "1 3 5 5 9\n" +
                "3\n" +
                "2 5 6\n");
        main(new String[0]);
        String expected = "1 2 3 5 5 5 6 9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n" +
                "0\n" +
                "0\n" +
                "\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("0\n" +
                "\n" +
                "1\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

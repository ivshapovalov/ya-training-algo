package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;

/*
https://contest.yandex.ru/contest/59540/problems/G/
Ни больше ни меньше

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан массив целых положительных чисел a длины n. Разбейте его на минимально возможное количество отрезков, чтобы каждое
число было не меньше длины отрезка которому оно принадлежит. Длиной отрезка считается количество чисел в нем.

Разбиение массива на отрезки считается корректным, если каждый элемент принадлежит ровно одному отрезку. Формат ввода

Первая строка содержит одно целое число t (1 ≤ t ≤ 1 000) — количество наборов тестовых данных. Затем следуют t наборов
тестовых данных.

Первая строка набора тестовых данных содержит одно целое число n (1 ≤ n ≤ 105) — длину массива.

Следующая строка содержит n целых чисел a1, a2, …, an (1 ≤ ai ≤ n) — массив a.

Гарантируется, что сумма n по всем наборам тестовых данных не превосходит 2 ⋅ 105. Формат вывода

Для каждого набора тестовых данных в первой строке выведите число k — количество отрезков в вашем разбиении.

Затем в следующей строке выведите k чисел len1, len2, …, lenk — длины отрезков в порядке слева направо.

Примечания

Ответы в примере соответствуют разбиениям:

{[1], [3, 3], [3, 2]}

{[1], [9, 8, 7, 6, 7, 8], [9, 9, 9, 9, 9, 9, 9, 9, 9]}

{[7, 2], [3, 4, 3], [2, 7]}

В первом наборе тестовых данных набор длин {1, 3, 1}, соответствующий разбиению {[1], [3, 3, 3], [2]}, также был бы корректным.


*/

public class TaskG extends ContestTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // количество наборов тестовых данных

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(); // длина массива
            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt(); // заполнение массива
            }

            int[] segments = findSegments(arr);
            System.out.println(segments.length); // вывод количества отрезков

            for (int len : segments) {
                System.out.print(len + " "); // вывод длин отрезков
            }
            System.out.println();
        }
    }

    public static int[] findSegments(int[] arr) {
        int n = arr.length;
        int[] segments = new int[n];
        int currentSegmentLength = 1;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            segments[i] = arr[i]; // каждый элемент начинает новый отрезок

            if (i > 0 && arr[i] == arr[i - 1]) {
                segments[i] = segments[i - 1]; // если текущий элемент равен предыдущему, то он принадлежит тому же отрезку
            } else if (arr[i] > currentSegmentLength) {
                currentSegmentLength = arr[i]; // обновляем текущую длину отрезка
            }
        }

        return segments;
    }

    @Test
    public void test_001() {
        provideConsoleInput("3\n" +
                "5\n" +
                "1 3 3 3 2\n" +
                "16\n" +
                "1 9 8 7 6 7 8 9 9 9 9 9 9 9 9 9\n" +
                "7\n" +
                "7 2 3 4 3 2 7\n");
        main(new String[0]);
        String expected = "3\n" +
                "1 2 2\n" +
                "3\n" +
                "1 6 9\n" +
                "3\n" +
                "2 3 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("10\n" +
                "9\n" +
                "1 1 9 2 9 9 9 5 8\n" +
                "10\n" +
                "10 9 9 10 3 4 1 8 2 7\n" +
                "10\n" +
                "10 10 10 1 5 8 4 8 9 8\n" +
                "10\n" +
                "1 3 10 4 6 4 3 7 6 10\n" +
                "10\n" +
                "4 3 8 3 7 1 10 5 1 4\n" +
                "10\n" +
                "5 2 5 5 10 10 10 1 6 3\n" +
                "10\n" +
                "9 2 1 4 1 9 8 3 1 1\n" +
                "7\n" +
                "4 1 6 4 7 1 7\n" +
                "2\n" +
                "2 2\n" +
                "2\n" +
                "1 2");
        main(new String[0]);
        String expected = "4\n" +
                "1 1 2 5 \n" +
                "5\n" +
                "4 2 1 2 1 \n" +
                "4\n" +
                "3 1 4 2 \n" +
                "4\n" +
                "1 3 3 3 \n" +
                "6\n" +
                "3 2 1 2 1 1 \n" +
                "4\n" +
                "2 5 1 2 \n" +
                "7\n" +
                "2 1 1 1 3 1 1 \n" +
                "5\n" +
                "1 1 3 1 1 \n" +
                "1\n" +
                "2 \n" +
                "2\n" +
                "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

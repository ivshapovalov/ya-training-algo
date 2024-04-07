package algo.c2.b.hw5;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/29075/problems/E/
Сумма трёх

Ограничение времени 	15 секунд
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или threesum.in
Вывод 	стандартный вывод или threesum.out

Даны три массива целых чисел A,B,C и целое число S.

Найдите такие i,j,k, что Ai+Bj+Ck=S.
Формат ввода
На первой строке число S (1≤S≤109). Следующие три строки содержат
описание массивов A,B,C в одинаковом формате: первое число задает длину n соответствующего массива (1≤n≤15000), затем
заданы n целых чисел от 1 до 109 — сам массив.

Формат вывода
Если таких i,j,k не существует, выведите единственное число
−1. Иначе выведите на одной строке три числа — i,j,k. Элементы массивов нумеруются с нуля. Если ответов несколько,
выведите лексикографически минимальный.
 */

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int sum = Integer.valueOf(reader.readLine().trim());
            int[] arrA = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int[] arrB = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int[] arrC = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Map<Integer, Integer> mapForC = new HashMap<>();
            for (int i = 1; i < arrC.length; i++) {
                if (mapForC.containsKey(arrC[i])) {
                    continue;
                }
                mapForC.put(arrC[i], i);
            }
            for (int i = 1; i < arrA.length; i++) {
                for (int j = 1; j < arrB.length; j++) {
                    if (mapForC.containsKey(sum - (arrA[i] + arrB[j]))) {
                        System.out.println((i - 1) + " " + (j - 1) + " " + (mapForC.get(sum - (arrA[i] + arrB[j])) - 1));
                        return;
                    }
                }
            }
            System.out.println(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "2 1 2\n" +
                "2 3 1\n" +
                "2 3 1\n");
        main(new String[0]);
        String expected = "0 1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10\n" +
                "1 5\n" +
                "1 4\n" +
                "1 3\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n" +
                "4 1 2 3 4\n" +
                "3 5 2 1\n" +
                "4 5 3 2 2\n");
        main(new String[0]);
        String expected = "0 1 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

package algo.c2.b.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29188/problems/E/
Покрытие K отрезками

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt
Даны n точек на прямой, нужно покрыть их k отрезками одинаковой длины ℓ.

Найдите минимальное ℓ.
Формат ввода
На первой строке n (1≤n≤105) и k (1≤k≤n). На второй n чисел xi (∣∣xi∣∣≤109).
Формат вывода
Минимальное такое ℓ, что точки можно покрыть k отрезками длины ℓ.

 */

public class TaskE extends ContestTask {

    private static int countSegments(int[] points, int step) {

        int count = 0;
        long end_of_segment = -1_000_000_001;
        for (int point : points) {
            if (point > end_of_segment) {
                count++;
                end_of_segment = point + step;
            }
        }
        return count;

    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el)).toArray();
            int n = input[0];
            int k = input[1];
            int[] points = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el)).toArray();
            Arrays.sort(points);

            int len = points[n - 1] - points[0] + 1;
            int lo = 0;
            int hi = (int) Math.ceil(len / k);

            while (lo < hi) {
                int m = (lo + ((hi - lo)) / 2);
                if (countSegments(points, m) > k) {
                    lo = m + 1;
                } else {
                    hi = m;
                }

            }
            System.out.println(lo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("6 2\n" +
                "1 2 3 9 8 7\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f018() throws FileNotFoundException {
        String testNumber = "018";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "1999975600\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

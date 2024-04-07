package algo.c4.hw0;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53027/problems/E/
Средний уровень

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В группе учатся n студентов, каждый из которых имеет свой рейтинг ai. Им нужно выбрать старосту; для этого студенты
 хотят выбрать старосту таким образом чтобы суммарный уровень недовольства группы был минимальный. Если выбрать j-го
 старостой, то уровень недовольства i-го студента равен ∣∣ai−aj∣∣.

Например, если в группе есть три студента с рейтингами 1, 3 и 4 и в качестве старосты выбирают второго, то уровень
недовольства группы будет равен |1−3|+|3−3|+|4−3|=3.

Вычислите уровень недовольства группы при выборе каждого из студентов старостой.

Формат ввода

В первой строке дано единственное целое число n (1≤n≤105)  — количество студентов в группе.

Во второй строке даны n целых чисел a1,a2,…,an, идущих по неубыванию (0≤a1≤a2≤…≤an≤104)  — рейтинги студентов.

Формат вывода
Выведите n чисел через пробел, i-е из которых будет обозначать уровень недовольства группы при выборе i-го
студента старостой.

*/

public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine().trim());
            int[] nums = Arrays.stream(r.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.parseInt(el)).toArray();
            int[] less = new int[n];
            int[] prefix = new int[n];
            prefix[0] = nums[0];
            less[0] = 0;
            for (int i = 1; i < n; i++) {
                prefix[i] = prefix[i - 1] + nums[i];
                less[i] = (i * nums[i]) - prefix[i - 1];
            }
            int[] greater = new int[n];
            prefix = new int[n];
            prefix[n - 1] = nums[n - 1];
            greater[n - 1] = 0;
            for (int i = n - 2; i >= 0; i--) {
                prefix[i] = prefix[i + 1] + nums[i];
                greater[i] = prefix[i + 1] - ((n - i - 1) * nums[i]);
            }
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = less[i] + greater[i];
            }
            System.out.println(Arrays.stream(result).mapToObj(num -> String.valueOf(num)).collect(Collectors.joining(" ")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "1 3 4\n");
        main(new String[0]);
        String expected = "5 3 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n" +
                "3 7 8 10 15\n");
        main(new String[0]);
        String expected = "28 16 15 17 32\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

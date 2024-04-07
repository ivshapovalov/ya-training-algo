package algo.c2.b.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28964/problems/B/
Встречалось ли число раньше

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Во входной строке записана последовательность чисел через пробел. Для каждого числа выведите слово YES (в отдельной
строке), если это число ранее встречалось в последовательности или NO, если не встречалось.

Формат ввода
Вводится список чисел. Все числа списка находятся на одной строке.

Формат вывода
Выведите ответ на задачу.
 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> list = Arrays.stream(reader.readLine().trim().split(" "))
                    .map(el -> Integer.valueOf(el)).collect(Collectors.toList());
            Set<Integer> result = new HashSet<>();
            for (int num : list) {
                if (result.contains(num)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                    result.add(num);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 2 3 2 3 4\n");
        main(new String[0]);
        String expected = "NO\n" +
                "NO\n" +
                "NO\n" +
                "YES\n" +
                "YES\n" +
                "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

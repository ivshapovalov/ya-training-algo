package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59540/problems/C/
Петя, Маша и верёвочки

Ограничение времени 	1 секунда
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На столе лежали две одинаковые верёвочки целой положительной длины.

Петя разрезал одну из верёвочек на N частей, каждая из которых имеет целую положительную длину, так что на столе стало
N+1 верёвочек. Затем в комнату зашла Маша и взяла одну из лежащих на столе верёвочек. По длинам оставшихся на столе N
верёвочек определите, какую наименьшую длину может иметь верёвочка, взятая Машей.

Формат ввода
Первая строка входных данных содержит одно целое число N — количество верёвочек, оставшихся на столе (2 ≤ N ≤ 1000). Во
второй строке содержится N целых чисел li — длины верёвочек (1 ≤ li ≤ 1000).

Формат вывода
Выведите одно целое число — наименьшую длину, которую может иметь верёвочка, взятая Машей.

*/

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(r.readLine());
            int[] ropes = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int sum = Arrays.stream(ropes).sum();
            int max = 0;
            for (int rope : ropes) {
                if (sum - rope < rope) {
                    max = rope;
                }
            }
            if (max == 0) {
                System.out.println(sum);
            } else {
                System.out.println(2 * max - sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("4\n" +
                "1 5 2 1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("4\n" +
                "5 12 4 3\n");
        main(new String[0]);
        String expected = "24\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

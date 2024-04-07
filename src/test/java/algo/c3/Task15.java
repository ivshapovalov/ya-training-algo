package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/45468/problems/15/
Великое Лайнландское переселение

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Лайнландия представляет из себя одномерный мир, являющийся прямой, на котором располагаются N городов, последовательно
пронумерованных от 0 до N - 1 . Направление в сторону от первого города к нулевому названо западным, а в обратную —
восточным.

Когда в Лайнландии неожиданно начался кризис, все были жители мира стали испытывать глубокое смятение. По всей
Лайнландии стали ходить слухи, что на востоке живётся лучше, чем на западе.

Так и началось Великое Лайнландское переселение. Обитатели мира целыми городами отправились на восток, покинув родные
улицы, и двигались до тех пор, пока не приходили в город, в котором средняя цена проживания была меньше, чем в родном.

Формат ввода
В первой строке дано одно число N (2≤N≤105) — количество городов в Лайнландии. Во второй строке дано N
чисел ai (0≤ai≤109) — средняя цена проживания в городах с нулевого по (N - 1)-ый соответственно.

Формат вывода
Для каждого города в порядке с нулевого по (N - 1)-ый выведите номер города, в который переселятся его изначальные
жители. Если жители города не остановятся в каком-либо другом городе, отправившись в Восточное Бесконечное Ничто,
выведите -1 .
*/
public class Task15 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[] cost = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < cost.length; i++) {
                while (!stack.isEmpty() && cost[stack.peek()] > cost[i]) {
                    Integer prev = stack.pop();
                    if (cost[prev] > cost[i]) {
                        res[prev] = i;
                    }
                }
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                Integer prev = stack.pop();
                res[prev] = -1;

            }
            System.out.println(Arrays.stream(res).mapToObj(el -> String.valueOf(el)).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("10\n" +
                "1 2 3 2 1 4 2 5 3 1\n");
        main(new String[0]);
        String expected = "-1 4 3 4 -1 6 9 8 9 -1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

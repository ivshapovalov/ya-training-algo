package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/45468/problems/26/
Самый дешевый путь

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В дощечке в один ряд вбиты гвоздики. Любые два гвоздика можно соединить ниточкой. Требуется соединить некоторые пары
гвоздиков ниточками так, чтобы к каждому гвоздику была привязана хотя бы одна ниточка, а суммарная длина всех ниточек
была минимальна.

Формат ввода
В первой строке входных данных записано число N — количество гвоздиков (2 ≤ N ≤ 100). В следующей строке заданы N чисел
— координаты всех гвоздиков (неотрицательные целые числа, не превосходящие 10000).

Формат вывода
Выведите единственное число — минимальную суммарную длину всех ниточек.
*/
public class Task25 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[] dp = new int[n + 2];
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Arrays.sort(input);
            int[] coords = new int[n + 2];
            System.arraycopy(input, 0, coords, 1, n);
            dp[1] = coords[2] - coords[1];
            dp[2] = coords[2] - coords[1];
            for (int i = 3; i <= n; i++) {
                dp[i] = Math.min(dp[i - 2], dp[i - 1]) + coords[i] - coords[i - 1];
            }
            System.out.print(dp[n] + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("6\n" +
                "3 13 12 4 14 6\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

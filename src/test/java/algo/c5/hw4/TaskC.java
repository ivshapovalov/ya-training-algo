package algo.c5.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59542/problems/C/
Саруман

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Как известно, Саруман Радужный очень любит порядок. Поэтому все полки его войска стоят друг за другом, причем каждый
следующий полк содержит количество орков не меньше, чем предыдущий.

Перед тем как напасть на Хельмову Падь, Саруман решил провести несколько вылазок для разведки. Чтобы его отряды никто не
заметил, он решил каждый раз отправлять несколько подряд идущих полков так, чтобы суммарное количество орков в них было
равно определенному числу. Так как это всего лишь разведка, каждый полк после вылазки возвращается на свое место. Задачу
выбрать нужные полки он поручил Гриме Змеиному Языку. А Грима не поскупится на вознаграждение, если вы ему поможете.

Формат ввода
В первой строке входного файла находится два целых числа: n (1 ≤ n ≤ 2⋅105) — количество полков и m (1 ≤ m ≤ 2⋅105) –
количество предстоящих вылазок.

В следующей строке записано n чисел ai, где ai — число орков в i-ом полке (1 ≤ ai ≤ 109, ai ≤ ai+1).

Далее в m строках записаны запросы вида: количество полков l (1 ≤ l ≤ n), которые должны будут отправиться в эту
вылазку, и суммарное количество орков в этих полках s (1 ≤ s ≤ 2⋅1016)

Формат вывода
Для каждого запроса выведите номер полка, с которого начнутся те l, которые необходимо отправить на вылазку. Если таких
полков несколько, выведите любой. Если же так выбрать полки нельзя, выведите -1.

*/

public class TaskC extends ContestTask {
    private static int findStartPosition(long[] prefixSum, int l, long s) {
        int left = 0;
        int right = prefixSum.length - l - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sum = prefixSum[mid + l] - prefixSum[mid];
            if (sum >= s) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left < prefixSum.length - l && prefixSum[left + l] - prefixSum[left] == s) {
            return left;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int n = input[0];
            int m = input[1];

            long[] orcs = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToLong(el -> Long.parseLong(el)).toArray();

            long[] prefixSum = new long[n + 1];
            prefixSum[0] = 0;
            for (int i = 1; i <= n; i++) {
                prefixSum[i] = prefixSum[i - 1] + orcs[i - 1];
            }

            for (int i = 0; i < m; i++) {
                long[] trip = Arrays.stream(r.readLine()
                        .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToLong(el -> Long.parseLong(el)).toArray();
                int l = (int) trip[0];
                long s = trip[1];
                int start = findStartPosition(prefixSum, l, s);
                System.out.println(start == -1 ? -1 : start + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_042() {
        provideConsoleInput("5 2\n" +
                "1 3 5 7 9\n" +
                "2 4\n" +
                "1 3\n");
        main(new String[0]);
        String expected = "1\n" +
                "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

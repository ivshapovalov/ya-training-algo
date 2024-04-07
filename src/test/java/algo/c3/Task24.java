package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://contest.yandex.ru/contest/45468/problems/24/
Покупка билетов

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

За билетами на премьеру нового мюзикла выстроилась очередь из N человек, каждый из которых хочет купить 1 билет. На всю
очередь работала только одна касса, поэтому продажа билетов шла очень медленно, приводя «постояльцев» очереди в
отчаяние. Самые сообразительные быстро заметили, что, как правило, несколько билетов в одни руки кассир продаёт быстрее,
чем когда эти же билеты продаются по одному. Поэтому они предложили нескольким подряд стоящим людям отдавать деньги
первому из них, чтобы он купил билеты на всех.

Однако для борьбы со спекулянтами кассир продавала не более 3-х билетов в одни руки, поэтому договориться таким образом
между собой могли лишь 2 или 3 подряд стоящих человека.

Известно, что на продажу i-му человеку из очереди одного билета кассир тратит Ai секунд, на продажу двух билетов — Bi
секунд, трех билетов — Ci секунд. Напишите программу, которая подсчитает минимальное время, за которое могли быть
обслужены все покупатели.

Обратите внимание, что билеты на группу объединившихся людей всегда покупает первый из них. Также никто в целях
ускорения не покупает лишних билетов (то есть билетов, которые никому не нужны).

Формат ввода
На вход программы поступает сначала число N — количество покупателей в очереди (1 ≤ N ≤ 5000). Далее идет N троек
натуральных чисел Ai, Bi, Ci. Каждое из этих чисел не превышает 3600. Люди в очереди нумеруются, начиная от кассы.

Формат вывода
Требуется вывести одно число — минимальное время в секундах, за которое могли быть обслужены все покупатели.
*/
public class Task24 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[][] dp = new int[n + 2][5];
            int[][] queue = new int[n + 2][5];
            for (int i = 1; i <= n; i++) {
                int[] human = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                System.arraycopy(human, 0, queue[i], 1, human.length);
            }
            dp[1][1] = queue[1][1];
            dp[1][2] = queue[1][2];
            dp[1][3] = queue[1][3];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{1, 1});
            q.offer(new int[]{1, 2});
            q.offer(new int[]{1, 3});
            while (!q.isEmpty()) {
                int[] human = q.poll();
                int index = human[0];
                int amount = human[1];
                if (index + amount > n) {
                    if (dp[n][amount] > dp[index][amount]) {
                        dp[n][amount] = dp[index][amount];
                    }
                    continue;
                }
                int case1 = dp[index][amount] + queue[index + amount][1];
                if (dp[index + amount][1] == 0 || dp[index + amount][1] > case1) {
                    dp[index + amount][1] = case1;
                    q.add(new int[]{index + amount, 1});
                }
                int case2 = dp[index][amount] + queue[index + amount][2];
                if (dp[index + amount][2] == 0 || dp[index + amount][2] > case2) {
                    dp[index + amount][2] = case2;
                    q.add(new int[]{index + amount, 2});
                }

                int case3 = dp[index][amount] + queue[index + amount][3];
                if (dp[index + amount][3] == 0 || dp[index + amount][3] > case3) {
                    dp[index + amount][3] = case3;
                    q.add(new int[]{index + amount, 3});
                }
            }
            Arrays.sort(dp[n]);
            System.out.print(dp[n][2] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "5 10 15\n" +
                "2 10 15\n" +
                "5 5 5\n" +
                "20 20 1\n" +
                "20 1 1\n");
        main(new String[0]);
        String expected = "12\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

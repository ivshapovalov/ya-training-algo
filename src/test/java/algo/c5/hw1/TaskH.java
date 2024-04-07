package algo.c5.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59539/problems/H/
Забег по стадиону
Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

 Стадион представляет собой окружность длинойL метров, на которой отмечена точка старта. По стадиону бегают Кирилл и
 Антон. У каждого мальчика есть своя точка старта (она представляет собой расстояние в метрах от старта, отсчитанное по
 часовой стрелке) и своя скорость в метрах в секунду (положительная скорость означает, что мальчик бежит по часовой
 стрелке, отрицательная — что бежит против часовой, а нулевая — что он стоит на месте).

Вам нужно сказать, через какое минимальное время мальчики окажутся на одинаковом расстоянии от точки старта. Обратите
внимание, что в этот момент они могли находиться в разных точках. Расстоянием от точки A до точки B называется
минимальное из расстояний, которое нужно пробежать из точки A по или против часовой стрелки, чтобы оказаться в B.

Формат ввода
В единственной строке вводится 5 целых чисел L,x1,v1,x2,v2 (1≤L≤109, 0≤x1,x2<L, ∣∣v1∣∣,∣∣v2∣∣≤109) — длины
стадиона в метрах, начальная точка Кирилла, скорость Кирилла, начальная точка Антона, скорость Антона.

Формат вывода
В первой строке выведите слово «YES», если случится момент, когда мальчики будут на одинаковом расстоянии от старта, или
«NO», если такого момента не произойдёт.

Если ответ «YES», то во второй строке выведите одно вещественное число — через какое минимальное количество времени
мальчики окажутся на одинаковом расстоянии от старта.

Вы можете выводить каждую букву в любом регистре (строчную или заглавную). Например, строки «yEs», «yes», «Yes» и «YES»
будут приняты как положительный ответ.

Ваш ответ будет считаться правильным, если его абсолютная или относительная ошибка не превосходит 10−9.

Формально, пусть ваш ответ равен a, а ответ жюри равен b. Ваш ответ будет зачтен, если и только если
|a−b|max(1,|b|)≤10−9.

*/

public class TaskH extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int L = input[0];
            int x1 = input[1];
            int v1 = input[2];
            int x2 = input[3];
            int v2 = input[4];

            double[] result = {Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int i = 0; i < 2; i++) {
                if (x2 > x1) {
                    if (v1 > 0 && v2 > 0) {
                        //t*v1=t*v2+(x2-x1)
                        //t=x2-x1/v1-v2
                        result[i] = (x2 - x1) / (v1 - v2);
                    } else if (v1 < 0 && v2 < 0) {
                        //t*v1+(x2-x1)=t*v2
                        //t=x2-x1/v1-v2
                        result[i] = (x2 - x1) / (v1 - v2);
                    } else if (v1 > 0 && v2 < 0) {
                        //t*v1+t*v2=x2-x1;
                        //t=x2-x1/v1+v2
                        result[i] = (x2 - x1) / (v1 + v2);
                    } else if (v1 < 0 && v2 > 0) {
                        //t*v1+t*v2=L-(x2-x1)
                        //t=L-(x2-x1) / v2+v1
                        result[i] = (L - (x2 - x1)) / (v2 + v1);
                    }
                } else if (x2 == x1) {
                    result[i] = 0;
                } else {
                    if (v1 > 0 && v2 > 0) {
                        //t=x1-x2/v2-v1
                        result[i] = (x1 - x2) / (v2 - v1);
                    } else if (v1 < 0 && v2 < 0) {
                        //t=x1-x2/v2-v1
                        result[i] = (x1 - x2) / (v2 - v1);
                    } else if (v2 > 0 && v1 < 0) {
                        //t=x1-x2/v2+v1
                        result[i] = (x1 - x2) / (v2 + v1);
                    } else if (v2 < 0 && v1 > 0) {
                        //t=L-(x1-x2) / v1+v2
                        result[i] = (L - (x1 - x2)) / (v1 + v2);
                    }
                }
//                v2=-v2;
//                x2=(-x2+L)%L;
            }
            if (result[0] == Integer.MAX_VALUE && result[1] == Integer.MAX_VALUE) {
                System.out.println("NO");
            } else {
                double res = Math.min(result[0], result[1]);
                System.out.println("YES");
                System.out.println(result);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("6 3 1 1 1\n");
        main(new String[0]);
        String expected = "YES\n" +
                "1.0000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("12 8 10 5 20\n");
        main(new String[0]);
        String expected = "YES\n" +
                "0.3000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("5 0 0 1 2\n");
        main(new String[0]);
        String expected = "YES\n" +
                "2.0000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("10 7 -3 1 4\n");
        main(new String[0]);
        String expected = "YES\n" +
                "0.8571428571\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

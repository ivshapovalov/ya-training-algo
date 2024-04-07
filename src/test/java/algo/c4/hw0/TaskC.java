package algo.c4.hw0;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53027/problems/C/
Путешествие по Москве

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Мэрия Москвы основательно подготовилась к празднованию тысячелетия города в 2147 году, построив под столицей бесконечную
асфальтированную площадку, чтобы заменить все существующие в городе автомобильные дороги. В память о кольцевых и
радиальных дорогах разрешили двигаться по площадке только двумя способами:

    1) В сторону точки начала координат или от неё. При этом из точки начала координат разрешено двигаться в любом
    направлении.
    2) Вдоль окружности с центром в начале координат и радиусом, который равен текущему расстоянию до начала
    координат. Двигаться вдоль такой окружности разрешается в любом направлении (по или против часовой стрелки).

Вам, как ведущему программисту ответственной инстанции поручено разработать модуль, который будет определять кратчайший
путь из точки A, с координатами (xA, yA) в точку B с координатами (xB, yB). Считайте, что менять направление движения
можно произвольное количество раз, но оно должно всегда соответствовать одному из двух описанных выше вариантов.

Формат ввода
В первой строке ввода заданы четыре целых числа xA, yA, xB и yB, по модулю не превосходящие 106.

Формат вывода
Выведите одно число — минимальное расстояние, которое придётся преодолеть по пути из точки A в точку B, если не нарушать
правил дорожного движения. Ваш ответ будет принят, если его абсолютная или относительная погрешность не превосходит
10-6.

*/

public class TaskC extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
            long xA = input[0];
            long yA = input[1];
            long xB = input[2];
            long yB = input[3];

            double radiusA = Math.sqrt(Math.pow(xA, 2) + Math.pow(yA, 2));
            double radiusB = Math.sqrt(Math.pow(xB, 2) + Math.pow(yB, 2));

            double angleA = Math.atan2(yA, xA);
            double angleB = Math.atan2(yB, xB);
            double angle = Math.abs(angleA - angleB);

            if (angle > 1.5 * Math.PI) {
                angle -= 2 * Math.PI;
            } else if (angle > Math.PI) {
                angle -= Math.PI;
            }

            double result = 0;
            if (Math.abs(angleA - angleB) < 0.000001) {
                result = Math.abs(radiusA - radiusB);
            } else {
                if (radiusA == 0 || radiusB == 0) {
                    result = radiusA + radiusB;
                } else {

                    double distanceRadius = radiusA + radiusB;
                    double distanceFull = Math.abs(Math.abs(radiusA - radiusB) + ((Math.min(radiusA, radiusB) * angle)));
                    result = Math.min(distanceRadius, distanceFull);

                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("0 5 4 3\n");
        main(new String[0]);
        String expected = "4.636476090008\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("0 5 4 -3\n");
        main(new String[0]);
        String expected = "10.000000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("-10 -1 -10 1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("-10 1 -10 -1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("-1 -10 1 -10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("1 -10 -1 -10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_006() {
        provideConsoleInput("1 10 -1 10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_007() {
        provideConsoleInput("1 -10 -1 -10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_005() {
        provideConsoleInput("3 4 6 8\n");
        main(new String[0]);
        String expected = "5.0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("0 0 888888 666666\n");
        main(new String[0]);
        String expected = "5.0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_18() {
        provideConsoleInput("1000000 1000000 1000000 999999\n");
        main(new String[0]);
        String expected = "1.414213385427\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

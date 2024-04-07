package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
https://contest.yandex.ru/contest/59541/problems/H/
Спички детям не игрушка!

Ограничение времени 	3 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вася любит решать головоломки со спичками. Чаще всего они формулируется следующим образом: дано изображение A,
составленное из спичек; переложите в нем минимальное количество спичек так, чтобы получилось изображение B.

Например, из номера текущего командного чемпионата школьников Санкт-Петербурга по программированию, можно получить ромб
с диагональю, переложив всего три спички.
https://contest.yandex.ru/testsys/statement-image?imageId=19e8753f7af3e8177364a572d3f003010c7aebc5ad2417c8bf27b39b5ddc2437

Головоломки, которые решает Вася, всегда имеют решение. Это значит, что набор спичек, используемый в изображении A,
совпадает с набором спичек, используемым в изображении B. Кроме того, в одном изображении никогда не встречаются две
спички, у которых есть общий участок ненулевой длины (то есть спички могут пересекаться, но не могут накладываться друг
на друга).

Вася устал решать головоломки вручную, и теперь он просит вас написать, программу, которая будет решать головоломки за
него. Программа будет получать описания изображений A и B и должна найти минимальное количество спичек, которые надо
переложить в изображении A, чтобы полученная картинка получалась из B параллельным переносом.

Формат ввода
В первой строке входного файла содержится целое число n — количество спичек в каждом из изображений (1 ≤ n ≤ 1000).

В следующих n строках записаны координаты концов спичек на изображении A. Спичка номер i описывается целыми числами x1i,
y1i, x2i, y2i — координатами ее концов. Следующие n строк содержат описание изображения B в таком же формате. Набор длин
этих спичек совпадает с набором длин спичек с изображения A.

Все координаты по абсолютной величине не превосходят 104. Все спички имеют ненулевую длину, то есть x1i ≠ x2i или y1i ≠
y2i.

Формат вывода
Выведите в выходной файл минимальное количество спичек, которые следует переложить, чтобы изображение A совпало с
изображением B, с точностью до параллельного переноса

*/

public class TaskH extends ContestTask {

    static int solve(int n, Point[] a, Point[] b) {
        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();

        for (int i = 0; i < n; i++) {
            setA.add(a[i].x + " " + a[i].y);
            setB.add(b[i].x + " " + b[i].y);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            int dx = b[i].x - a[i].x;
            int dy = b[i].y - a[i].y;
            if (!setA.contains((a[i].x + dx) + " " + (a[i].y + dy)) && setB.contains((a[i].x + dx) + " " + (a[i].y + dy))) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] a = new Point[n];
        Point[] b = new Point[n];

        for (int i = 0; i < n; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            a[i] = new Point(x1, y1);
            b[i] = new Point(x2, y2);
        }

        int result = solve(n, a, b);
        System.out.println(result);
    }

    @Test
    public void test_001() {
        provideConsoleInput("5\n" +
                "0 0 1 2\n" +
                "1 0 0 2\n" +
                "2 0 2 2\n" +
                "4 0 3 2\n" +
                "4 0 5 2\n" +
                "9 -1 10 1\n" +
                "10 1 9 3\n" +
                "8 1 10 1\n" +
                "8 1 9 -1\n" +
                "8 1 9 3\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("1\n" +
                "3 4 7 9\n" +
                "-1 3 3 8\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("1\n" +
                "-4 5 2 -3\n" +
                "-12 4 -2 4\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

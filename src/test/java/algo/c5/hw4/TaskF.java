package algo.c5.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/*
https://contest.yandex.ru/contest/59542/problems/F/
Велодорожки

Ограничение времени 	4 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

 Мэр одного города очень любит следить за тенденциями и воспроизводить их в своём городе. До него дошла новость о
 популярности велодорожек. Теперь он хочет проложить велодорожки в своём городе и сделать это лучше, чем в других
 городах! Поэтому он решил сделать велодорожки даже на главной площади города.

Главная площадь представляет собой прямоугольник шириной w и высотой h, замощённый квадратными плитками со стороной 1.
Мэр хочет, чтобы было проложено две велодорожки одинаковой ширины: одна горизонтальная и одна вертикальная. К сожалению,
ремонт на площади проводился достаточно давно и на некоторых плитках уже появились трещины. Мэр хочет проложить
велодорожки так, чтобы после этого на площади остались только целые плитки. При строительстве велодорожек плитки на их
месте убираются. Можно только убирать плитки с площади и нельзя менять местами или добавлять новые. Чтобы потратить
меньше денег, мэр хочет сделать велодорожки наименьшей возможной ширины, при этом ширина дорожек должна быть целым
числом. Определите, какой должна быть ширина велодорожек.

Формат ввода
В первой строке входных данных содержатся три
целых числа w,h,n (1≤w,h≤109, 1≤n≤min(w×h,3⋅105))  — ширина и высота площади и количество потрескавшихся плиток
соответственно.

В следующих n строках содержится по 2 целых числа xi,yi (1≤xi≤w, 1≤yi≤h)  — координаты потрескавшихся плиток.
(xi,yi)≠(xj,yj) при i≠j.

Формат вывода
Выведите единственное число c (1≤c≤min(w,h))  — наименьшую возможную ширину
велодорожек.

https://contest.yandex.ru/testsys/statement-image?imageId=bcb7434301c02a4a5dea832a3d38b7de2d9b720fd9294bfc6fbbf564a9759242
*/

public class TaskF extends ContestTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int w = scanner.nextInt(); // ширина площади
        int h = scanner.nextInt(); // высота площади
        int n = scanner.nextInt(); // количество потрескавшихся плиток

        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            xCoords.add(x);
            yCoords.add(y);
        }

        int minBikeLaneWidth = binarySearchMinWidth(w, h, xCoords, yCoords);

        System.out.println(minBikeLaneWidth);
    }

    private static int binarySearchMinWidth(int w, int h, List<Integer> xCoords, List<Integer> yCoords) {
        int left = 0;
        int right = Math.min(w, h);

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (canLayBikeLanes(w, h, xCoords, yCoords, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private static boolean canLayBikeLanes(int w, int h, List<Integer> xCoords, List<Integer> yCoords, int width) {
        Set<Integer> crackedTiles = new HashSet<>();
        for (int i = 0; i < xCoords.size(); i++) {
            int x = xCoords.get(i);
            int y = yCoords.get(i);
            crackedTiles.add(x * (h + 1) + y);
        }

        for (int x = 0; x <= w - width; x++) {
            for (int y = 0; y <= h - width; y++) {
                boolean canLayBikeLane = true;
                for (int i = x; i < x + width; i++) {
                    for (int j = y; j < y + width; j++) {
                        if (crackedTiles.contains(i * (h + 1) + j)) {
                            canLayBikeLane = false;
                            break;
                        }
                    }
                    if (!canLayBikeLane) {
                        break;
                    }
                }
                if (canLayBikeLane) {
                    return true;
                }
            }
        }
        return false;
    }


    @Test
    public void test_001() {
        provideConsoleInput("5 6 5\n" +
                "5 4\n" +
                "2 6\n" +
                "4 1\n" +
                "2 3\n" +
                "1 4\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("4 3 4\n" +
                "1 1\n" +
                "4 3\n" +
                "4 1\n" +
                "1 3\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f03() throws FileNotFoundException {
        String testNumber = "03";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators("63\n", getOutput());
    }

}

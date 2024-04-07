package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/*
https://contest.yandex.ru/contest/59540/problems/J/
Два прямоугольника

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Недавно один известный художник-абстракционист произвел на свет новый шедевр — картину «Два черных непересекающихся
прямоугольника». Картина представляет собой прямоугольник m× n, разбитый на квадраты 1× 1, некоторые из которых
закрашены любимым цветом автора — черным. Федя — не любитель абстрактных картин, однако ему стало интересно,
действительно ли на картине изображены два непересекающихся прямоугольника. Помогите ему это узнать. Прямоугольники не
пересекаются в том смысле, что они не имеют общих клеток.

Формат ввода
Первая строка входного файла содержит числа m и n (1 ≤ m, n ≤ 200). Следующие m строк содержат описание рисунка. Каждая
строка содержит ровно n символов. Символ «.» обозначает пустой квадрат, а символ «#» — закрашенный.

Формат вывода
Если рисунок можно представить как два непересекающихся прямоугольника, выведите в первой строке «YES», а в следующих m
строках выведите рисунок в том же виде, в каком он задан во входном файле, заменив квадраты, соответствующие первому
прямоугольнику на символ «a», а второму — на символ «b». Если решений несколько, выведите любое.

Если же этого сделать нельзя, выведите в выходной файл «NO».

*/

public class TaskJ extends ContestTask {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine(); // Считываем символ новой строки после ввода n

        char[][] image = new char[m][n];

        // Считываем изображение
        for (int i = 0; i < m; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                image[i][j] = line.charAt(j);
            }
        }

        // Поиск двух непересекающихся прямоугольников
        boolean found = false;
        outerLoop:
        for (int x1 = 0; x1 < m; x1++) {
            for (int y1 = 0; y1 < n; y1++) {
                if (image[x1][y1] == '.') {
                    continue;
                }
                for (int x2 = 0; x2 < m; x2++) {
                    for (int y2 = 0; y2 < n; y2++) {
                        if (x2 == x1 && y2 == y1) {
                            continue;
                        }
                        if (image[x2][y2] == '.' || (x2 == x1 && y2 != y1) || (x2 != x1 && y2 == y1)) {
                            continue;
                        }
                        // Проверка на пересечение
                        boolean intersects = false;
                        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                                if (image[i][j] == '.') {
                                    intersects = true;
                                    break;
                                }
                            }
                            if (intersects) {
                                break;
                            }
                        }
                        if (!intersects) {
                            found = true;
                            // Заполняем символами 'a' и 'b' найденные прямоугольники
                            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                                for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                                    if (i == x1 && j == y1) {
                                        image[i][j] = 'a';
                                    } else {
                                        image[i][j] = 'b';
                                    }
                                }
                            }
                            break outerLoop;
                        }
                    }
                }
            }
        }

        if (found) {
            System.out.println("YES");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(image[i][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("NO");
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("2 1\n" +
                "#\n" +
                ".\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("2 2\n" +
                "..\n" +
                "##\n");
        main(new String[0]);
        String expected = "YES\n" +
                "..\n" +
                "ab\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("1 3\n" +
                "###\n");
        main(new String[0]);
        String expected = "YES\n" +
                "abb\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("1 5\n" +
                "####.\n");
        main(new String[0]);
        String expected = "YES\n" +
                "abbb.\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

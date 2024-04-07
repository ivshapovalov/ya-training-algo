package algo.c5.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

/*
https://contest.yandex.ru/contest/59539/problems/F/
Миша и математика

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

 Миша сидел на занятиях математики в Высшей школе экономики и решал следующую задачу: дано n целых чисел и нужно
 расставить между ними знаки + и × так, чтобы результат полученного арифметического выражения был нечётным (например,
 между числами 5, 7, 2, можно расставить арифметические знаки следующим образом: 5×7+2=37). Так как примеры становились
 все больше и больше, а Миша срочно убегает в гости, от вас требуется написать программу решающую данную задачу.

Формат ввода
В первой строке содержится единственное число n (2≤n≤105). Во второй строке содержится n целых чисел ai,
разделённых пробелами (−109≤ai≤109). Гарантируется, что решение существует.

Формат вывода
В одной строке выведите n−1
символ + или ×, в результате применения которых получается нечётный результат. (Для вывода используйте соответственно
знаки «+» (ASCII код—43) и «x» (ASCII код—120), без кавычек).

*/


public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            int[] nums = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty())
                    .mapToInt(el -> Integer.parseInt(el)).toArray();
            Map<Integer, Queue<Point>> map = new HashMap<>();
            Queue<Point> q = new ArrayDeque<>();
            q.add(new Point((nums[0] & 1) == 0, ""));
            int stage = 0;
            map.put(stage, q);
            while (stage < nums.length - 1) {
                Queue<Point> curQueue = map.getOrDefault(stage, new ArrayDeque<>());
                stage++;
                Queue<Point> nextQueue = map.getOrDefault(stage, new ArrayDeque<>());
                while (!curQueue.isEmpty()) {
                    Point prev = curQueue.poll();
                    boolean curIsChetnoe = (nums[stage] & 1) == 0;
                    if (prev.isChetnoe() && curIsChetnoe) {
                        //+
                        nextQueue.add(new Point(true, prev.result + "+"));
                        //*
                        nextQueue.add(new Point(true, prev.result + "x"));
                    } else if (prev.isChetnoe() && !curIsChetnoe) {
                        //+
                        nextQueue.add(new Point(false, prev.result + "+"));
                        //*
                        nextQueue.add(new Point(true, prev.result + "x"));
                    } else if (!prev.isChetnoe() && curIsChetnoe) {
                        //+
                        nextQueue.add(new Point(false, prev.result + "+"));
                        //*
                        nextQueue.add(new Point(true, prev.result + "x"));
                    } else if (!prev.isChetnoe() && !curIsChetnoe) {
                        //+
                        nextQueue.add(new Point(true, prev.result + "+"));
                        //*
                        nextQueue.add(new Point(false, prev.result + "x"));
                    }
                    map.put(stage, nextQueue);
                }
            }
            Point point = new ArrayList<>(map.get(nums.length - 1)).stream().filter(el -> !el.isChetnoe).findAny().get();
            System.out.println(point.result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("3\n" + "5 7 2\n");
        main(new String[0]);
        String expected = "x+\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("2\n" + "4 -5\n");
        main(new String[0]);
        String expected = "+\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_006() {
        provideConsoleInput("6\n" + "-76959846 -779700294 380306679 -340361999 58979764 -392237502");
        main(new String[0]);
        String expected = "++x++\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f09() throws FileNotFoundException {
        String testNumber = "09";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        //        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        //        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators("13024153355\n", getOutput());
    }

    record Point(boolean isChetnoe, String result) {
    }

}

package algo.c4.hw0;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;

/*
https://contest.yandex.ru/contest/53027/problems/F/
Лифт

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Тридцатого декабря все сотрудники известной IT-компании отправляются праздновать Новый год! На парковке офиса
сотрудников уже ожидают автобусы, чтобы отвезти их в ресторан. Известно, что на i-м этаже работает ai сотрудников, а
парковка расположена на нулевом этаже. Изначально лифт расположен на этаже с парковкой. Какое минимальное количество
времени лифт будет перевозить всех людей на парковку? Известно, что лифт движется со скоростью один этаж в секунду, а
посадка и высадка происходит мгновенно.

Формат ввода
В первой строке дано единственное целое число k(1≤k≤109)  — количество людей, которое вмещает лифт за одну
поездку. Во второй строке дано единственное целое число n  — количество этажей в здании. В следующих n(1≤n≤105) строках
дано по одному целому неотрицательному числу ai(0≤ai≤109), которое обозначает количество сотрудников на этаже номер i.

Формат вывода
Выведите единственное целое число  — минимальное количество секунд, которое необходимо, чтобы все
сотрудники оказались на парковке.

*/

public class TaskF extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            long capacity = Long.parseLong(r.readLine().trim());
            int floorsNumber = Integer.parseInt(r.readLine().trim());
            long[] floors = new long[floorsNumber + 1];
            for (int floorIndex = 1; floorIndex <= floorsNumber; floorIndex++) {
                floors[floorIndex] = Integer.parseInt(r.readLine().trim());
            }

            BigDecimal time = BigDecimal.ZERO;
            long goneWithGreaterFloor = 0;
            for (int floorIndex = floorsNumber; floorIndex >= 1; floorIndex--) {
                if (floors[floorIndex] == 0) continue;
                if (floors[floorIndex] > goneWithGreaterFloor) {
                    long amountOnCurrentFloor = floors[floorIndex] - goneWithGreaterFloor;
                    time = time.add(BigDecimal.valueOf((long) (Math.ceil(amountOnCurrentFloor * 1.0 / capacity) * (floorIndex * 2L))));
                    if (amountOnCurrentFloor % capacity != 0) {
                        goneWithGreaterFloor = capacity - (amountOnCurrentFloor % capacity);
                    } else {
                        goneWithGreaterFloor = 0;
                    }
                } else {
                    goneWithGreaterFloor -= floors[floorIndex];
                }
            }
            System.out.println(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "3\n" +
                "3\n" +
                "0\n" +
                "1\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("3\n" +
                "3\n" +
                "14\n" +
                "14\n" +
                "14\n");
        main(new String[0]);
        String expected = "58\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("3\n" +
                "3\n" +
                "0\n" +
                "0\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("1\n" +
                "3\n" +
                "5\n" +
                "5\n" +
                "5\n");
        main(new String[0]);
        String expected = "60\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("1\n" +
                "3\n" +
                "1\n" +
                "1\n" +
                "1\n");
        main(new String[0]);
        String expected = "12\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_005() {
        provideConsoleInput("10\n" +
                "1\n" +
                "5\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_006() {
        provideConsoleInput("10\n" +
                "1\n" +
                "1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_007() {
        provideConsoleInput("10\n" +
                "1\n" +
                "11\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_008() {
        provideConsoleInput("10\n" +
                "5\n" +
                "10\n" +
                "00\n" +
                "00\n" +
                "00\n" +
                "10\n");
        main(new String[0]);
        String expected = "12\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_009() {
        provideConsoleInput("1000000000\n" +
                "5\n" +
                "1000000000\n" +
                "00\n" +
                "00\n" +
                "00\n" +
                "1000000000\n");
        main(new String[0]);
        String expected = "12\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_010() {
        provideConsoleInput("1000000000\n" +
                "5\n" +
                "1000000000\n" +
                "1000000000\n" +
                "00\n" +
                "1000000000\n" +
                "1000000000\n");
        main(new String[0]);
        String expected = "24\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_011() {
        provideConsoleInput("1000000000\n" +
                "5\n" +
                "0\n" +
                "0\n" +
                "00\n" +
                "0\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_012() {
        provideConsoleInput("1000000000\n" +
                "5\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_013() {
        provideConsoleInput("1000000000\n" +
                "5\n" +
                "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5\n");
        main(new String[0]);
        String expected = "10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_014() {
        provideConsoleInput("10\n" +
                "5\n" +
                "1\n" +
                "5\n" +
                "4\n" +
                "5\n" +
                "1\n");
        main(new String[0]);
        String expected = "14\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_015() {
        provideConsoleInput("10\n" +
                "1\n" +
                "4\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_016() {
        provideConsoleInput("10\n" +
                "5\n" +
                "10\n" +
                "10\n" +
                "10\n" +
                "10\n" +
                "10\n");
        main(new String[0]);
        String expected = "30\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f21() throws FileNotFoundException {
        String testNumber = "21";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "10000099749854235032\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

package algo.c5.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/59539/problems/C/
Форматирование файла

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

 Петя - начинающий программист. Сегодня он написал код из n строк.

К сожалению оказалось, что этот код трудно читать. Петя решил исправить это, добавив в различные места пробелы. А
точнее, для i-й строки ему нужно добавить ровно ai пробелов.

Для добавления пробелов Петя выделяет строку и нажимает на одну из трёх клавиш: Space, Tab, и Backspace. При нажатии на
Space в строку добавляется один пробел. При нажатии на Tab в строку добавляются четыре пробела. При нажатии на Backspace
в строке удаляется один пробел.

Ему хочется узнать, какое наименьшее количество клавиш придётся нажать, чтобы добавить необходимое количество пробелов в
каждую строку. Помогите ему!

Формат ввода Первая строка входных данных содержит одно целое положительное число n(1≤n≤105) – количество строк в файле.

Каждая из следующих n строк содержит одно целое неотрицательное число ai(0≤ai≤109) – количество пробелов, которые нужно
добавить в i-ю строку файла. Формат вывода Выведите одно число – минимальное количество нажатий, чтобы добавить в каждой
строке необходимое количество пробелов.

*/

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            long result = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(r.readLine());
                result += num / 4;
                int remainder = num % 4;
                if (remainder != 0) {
                    map.put(remainder, map.getOrDefault(remainder, 0) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                result += ((long) countClick(entry.getKey()) * entry.getValue());
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countClick(int remainder) {
        int result = 0;
        switch (remainder) {
            case 1:
                result += 1;
                break;
            case 2:
                result += 2;
                break;
            case 3:
                result += 2;
                break;
        }
        return result;
    }


    @Test
    public void test_001() {
        provideConsoleInput("5\n" +
                "1\n" +
                "4\n" +
                "12\n" +
                "9\n" +
                "0\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f07() throws FileNotFoundException {
        String testNumber = "07";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators("13024153355\n", getOutput());
    }

}

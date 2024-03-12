package ru.algo.ya.c1.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;

/*
https://contest.yandex.ru/contest/27794/problems/I/
Робот
*/
public class TaskI extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.valueOf(reader.readLine());
            String operations = reader.readLine().trim();

            long result = 0;
            long prevlen = 0;
            for (int i = k; i < operations.length(); i++) {
                if (operations.charAt(i) == operations.charAt(i - k)) {
                    prevlen++;
                } else {
                    prevlen = 0;
                }
                result += prevlen;
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "zabacabab\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n" +
                "abc\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f26() throws FileNotFoundException {
        String testNumber = "026";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "2690027168\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}

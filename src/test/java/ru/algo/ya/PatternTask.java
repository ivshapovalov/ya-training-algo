package ru.algo.ya;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternTask extends ContestTask {

    public static void main(String[] args) {

        //input - System.in
        //output - System.out
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.valueOf(reader.readLine().trim());
            int[] point = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

            System.out.print("answer\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //input - File
        //output - File
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("input.txt"))) {
            FileWriter writer = new FileWriter("output.txt");
            writer.write("");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test_console() {
        provideConsoleInput("3\n" +
                "1 1\n" +
                "1 10\n" +
                "5 5\n");
        PatternTask.main(new String[0]);
        String expected = "1 1 5 10\r\n";
        assertEquals(expected, getOutput());
    }

    @Test
    public void test_file() throws FileNotFoundException {
        String testNumber = "010";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

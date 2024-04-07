package algo.c1.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


/*
https://contest.yandex.ru/contest/27663/problems/F/
Инопланетный геном
*/
public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String gen1 = r.readLine();
            String gen2 = r.readLine();
            Map<String, Integer> map1 = new HashMap<>();
            Map<String, Integer> map2 = new HashMap<>();
            for (int i = 0; i < gen2.length() - 1; i++) {
                String sub = gen2.substring(i, i + 2);
                map2.put(sub, map2.getOrDefault(sub, 0) + 1);
            }
            for (int i = 0; i < gen1.length() - 1; i++) {
                String sub = gen1.substring(i, i + 2);
                map1.put(sub, map1.getOrDefault(sub, 0) + 1);
            }
            BigInteger result = BigInteger.valueOf(0);
            for (Map.Entry<String, Integer> entry : map1.entrySet()) {
//                if (map2.containsKey(entry.getKey())) {
                result =
                        result.add(BigInteger.valueOf(entry.getValue()).multiply(BigInteger.valueOf(map2.getOrDefault(entry.getKey(), 0))));
//                }
            }
            System.out.println(result.longValue());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_00() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1E+5; i++) {
            sb1.append('A');
            sb2.append('A');
        }
        provideConsoleInput(sb1 + "\n" +
                sb2 + "\n");
        main(new String[0]);
        String expected = "9999800001\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_01() {
        provideConsoleInput("ABBACAB\n" +
                "B\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("A\n" +
                "B\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("\n" +
                "B\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("\n" +
                "\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("ABBACAB\n" +
                "\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_1() {
        provideConsoleInput("ABBACAB\n" +
                "BCABB\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("ABBACAB\n" +
                "BCABAB\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

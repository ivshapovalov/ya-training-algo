package ru.algo.ya.c1.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27472/problems/H/
Наибольшее произведение трех чисел
*/

public class TaskH extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            List<BigInteger> pos = new ArrayList<>();
            List<BigInteger> neg = new ArrayList<>();
            Arrays.stream(r.readLine().split(" ")).forEach(el -> {
                BigInteger cur = new BigInteger(el);
                if (cur.compareTo(BigInteger.valueOf(0)) >= 0) {
                    pos.add(cur);
                } else {
                    neg.add(cur);
                }
            });
            String result = "";
            if (neg.size() + pos.size() == 3) {
                Collections.sort(pos);
                Collections.sort(neg);
                result =
                        neg.stream().map(el -> String.valueOf(el)).collect(Collectors.joining(" "))
                                + ((neg.size() != 0 && pos.size() != 0) ? " " : "") +
                                pos.stream().map(el -> String.valueOf(el)).collect(Collectors.joining(" "));
            } else {
                Collections.sort(pos, Comparator.reverseOrder());
                Collections.sort(neg);

                if (neg.size() <= 1) {
                    result = pos.get(2).longValue() + " " + pos.get(1).longValue() + " " + pos.get(0).longValue();
                } else if (pos.size() == 0) {
                    result = neg.get(neg.size() - 3).longValue()
                            + " " + neg.get(neg.size() - 2).longValue()
                            + " " + neg.get(neg.size() - 1).longValue();
                } else if (pos.size() == 1) {
                    result = neg.get(0).longValue()
                            + " " + neg.get(1).longValue()
                            + " " + pos.get(0).longValue();
                } else if (pos.size() == 2) {
                    result = neg.get(0).longValue() + " " + neg.get(1).longValue() + " " + pos.get(0).longValue();
                } else {
                    BigInteger neg2pos1Multi = neg.get(0).multiply(neg.get(1)).multiply(pos.get(0));
                    BigInteger pos3Multi = pos.get(0).multiply(pos.get(1)).multiply(pos.get(2));
                    if (neg2pos1Multi.compareTo(pos3Multi) > 0) {
                        result = neg.get(0).longValue() + " " + neg.get(1).longValue() + " " + pos.get(0).longValue();
                    } else {
                        result = pos.get(2).longValue() + " " + pos.get(1).longValue() + " " + pos.get(0).longValue();
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("-10 5 6 8 9\n");
        main(new String[0]);
        String expected = "6 8 9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("-10 -5 6 8 9\n");
        main(new String[0]);
        String expected = "-10 -5 9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("-10 -5 -6 -8 -9 -11\n");
        main(new String[0]);
        String expected = "-8 -6 -5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("1 2 3 4 5 6 7\n");
        main(new String[0]);
        String expected = "5 6 7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("-6 -5 4 5 6 7\n");
        main(new String[0]);
        String expected = "5 6 7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("-6 -5 -4 -5 -6 7\n");
        main(new String[0]);
        String expected = "-6 -6 7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07() {
        provideConsoleInput("-6 -5 -4 -3 6 7\n");
        main(new String[0]);
        String expected = "-6 -5 7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_08() {
        provideConsoleInput("-6 -4 -3 5 7\n");
        main(new String[0]);
        String expected = "-6 -4 7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_09() {
        provideConsoleInput("-6 -4 -3\n");
        main(new String[0]);
        String expected = "-6 -4 -3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_10() {
        provideConsoleInput("1 2 3\n");
        main(new String[0]);
        String expected = "1 2 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_11() {
        provideConsoleInput("-6 -5 3\n");
        main(new String[0]);
        String expected = "-6 -5 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_12() {
        provideConsoleInput("-6 3 5\n");
        main(new String[0]);
        String expected = "-6 3 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_13() {
        provideConsoleInput("-6 -5 -3 -1\n");
        main(new String[0]);
        String expected = "-5 -3 -1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_14() {
        provideConsoleInput("-6 -5 -3 1\n");
        main(new String[0]);
        String expected = "-6 -5 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_15() {
        provideConsoleInput("-6 -5 1 3\n");
        main(new String[0]);
        String expected = "-6 -5 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_16() {
        provideConsoleInput("-3 2 1 3\n");
        main(new String[0]);
        String expected = "1 2 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_17() {
        provideConsoleInput("-3 -2 1 5 6\n");
        main(new String[0]);
        String expected = "-3 -2 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_18() {
        provideConsoleInput("-9 -8 1 5 6\n");
        main(new String[0]);
        String expected = "-9 -8 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_19() {
        provideConsoleInput("3 5 1 7 9 0 9 -3 10\n");
        main(new String[0]);
        String expected = "9 9 10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_20() {
        provideConsoleInput("-5 -30000 -12\n");
        main(new String[0]);
        String expected = "-30000 -12 -5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_21() {
        provideConsoleInput("1 2 3\n");
        main(new String[0]);
        String expected = "1 2 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

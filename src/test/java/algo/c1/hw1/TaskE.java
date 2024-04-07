package algo.c1.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27393/problems/E/
Скорая помощь
*/

public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = r.readLine().split(" ");
            int K1 = Integer.valueOf(input[0]);
            int M = Integer.valueOf(input[1]);
            int K2 = Integer.valueOf(input[2]);
            int P2 = Integer.valueOf(input[3]);
            int N2 = Integer.valueOf(input[4]);

            boolean found = false;
            for (int flatsPerFloor = 1; flatsPerFloor < Math.min(K1, K2); flatsPerFloor++) {
                int globalFloor2 = K2 / flatsPerFloor + 1;
                int globalFloor1 = K1 / flatsPerFloor + 1;
                if (globalFloor2 == (P2 - 1) * M + (N2 - 1) + 1) {
                    int P1 = K1 / (M * flatsPerFloor) + 1;
                    int N1 = (K1 % (M * flatsPerFloor)) / flatsPerFloor + 1;
                    System.out.println(P1 + " " + N1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.print("-1 -1 ");
            }

//
//            //P1 N1
//            int flatsPerFloor = 0;
//            if (P2 == 1 && N2 == 1) {
//                System.out.println("0 1");
//            } else {
//                flatsPerFloor = (K2 / (((P2 - 1) * M) + (N2 - 1)));
//
//                int globalFloor2 = K2 % flatsPerFloor != 0 ? K2 / flatsPerFloor + 1 : K2 / flatsPerFloor;
//                if (globalFloor2 < ((P2 - 1) * M) + (N2 - 1)+1) {
//                    System.out.println("-1 -1 ");
//                } else {
//                    int globalFloor1 = K1 % flatsPerFloor != 0 ? K1 / flatsPerFloor + 1 : K1 / flatsPerFloor;
//                    int P1 = globalFloor1 / M + 1;
//                    int N1 = globalFloor1 % M;
//                    System.out.println(P1 + " " + N1);
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("89 20 41 1 11\n");
        main(new String[0]);
        String expected = "2 3";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("11 1 1 1 1\n");
        main(new String[0]);
        String expected = "0 1";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3 2 2 2 1\n");
        main(new String[0]);
        String expected = "-1 -1";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

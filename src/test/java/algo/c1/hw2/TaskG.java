package algo.c1.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27472/problems/G/
Наибольшее произведение двух чисел
*/

public class TaskG extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            List<BigInteger> nums =
                    Arrays.stream(r.readLine().split(" ")).map(el -> new BigInteger(el)).collect(Collectors.toList());
            Collections.sort(nums);
            BigInteger minMulti = nums.get(0).multiply(nums.get(1));
            BigInteger maxMulti = nums.get(nums.size() - 1).multiply(nums.get(nums.size() - 2));
            if (minMulti.compareTo(maxMulti) > 0) {
                System.out.println(nums.get(0).longValue() + " " + nums.get(1).longValue());
            } else {
                System.out.println(nums.get(nums.size() - 2).longValue() + " " + nums.get(nums.size() - 1).longValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("-4 -5 5 4 3 2 1 -2\n");
        main(new String[0]);
        String expected = "4 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 3 5 2 5\n");
        main(new String[0]);
        String expected = "5 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("-4 3 -5 2 5\n");
        main(new String[0]);
        String expected = "-5 -4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("12288 -10075 29710 15686 -18900 -17715 15992 24431 6220 28403 -23148 18480 -22905 5411 -7602 15560 -26674 11109 -4323 6146 -1523 4312 10666 -15343 -17679 7284 20709 -7103 24305 14334 -12281 17314 26061 25616 17453 16618 -24230 -19788 21172 11339 2202 -22442 -20997 1879 -8773 -8736 5310 -23372 12621 -25596 -28609 -13309 -13 10336 15812 -21193 21576 -1897 -12311 -6988 -25143 -3501 23231 26610 12618 25834 -29140 21011 23427 1494 15215 23013 -15739 8325 5359 -12932 18111 -72 -12509 20116 24390 1920 17487 25536 24934 -6784 -16417 -2222 -16569 -25594 4491 14249 -28927 27281 3297 5998 6259 4577 12415 3779 -8856 3994 19941 11047 2866 -24443 -17299 -9556 12244 6376 -13694 -14647 -22225 21872 7543 -6935 17736 -2464 9390 1133 18202 -9733 -26011 13474 29793 -26628 -26124 27776 970 14277 -23213 775 -9318 29014 -5645 -27027 -21822 -17450 -5 -655 22807 -20981 16310 27605 -18393 914 7323 599 -12503 -28684 5835 -5627 25891 -11801 21243 -21506 22542 -5097 8115 178 10427 25808 10836 -11213 18488 21293 14652 12260 42 21034 8396 -27956 13670 -296 -757 18076 -15597 4135 -25222 -19603 8007 6012 2704 28935 16188 -20848 13502 -11950 -24466 5440 26348 27378 7990 -11523 -26393 \n");
        main(new String[0]);
        String expected = "29710 29793\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}

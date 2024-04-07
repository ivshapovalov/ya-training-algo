package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
https://contest.yandex.ru/contest/45468/problems/7/
SNTP

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Для того чтобы компьютеры поддерживали актуальное время, они могут обращаться к серверам точного времени SNTP (Simple
Network Time Protocol). К сожалению, компьютер не может просто получить время у сервера, потому что информация по сети
передаётся не мгновенно: пока сообщение с текущим временем дойдёт до компьютера, оно потеряет свою актуальность.
Протокол взаимодействия клиента (компьютера, запрашивающего точное время) и сервера (компьютера, выдающего точное время)
выглядит следующим образом:

1. Клиент отправляет запрос на сервер и запоминает время отправления A (по клиентскому времени).

2. Сервер получает запрос в момент времени B (по точному серверному времени) и отправляет клиенту сообщение, содержащее
время B.

3. Клиент получает ответ на свой запрос в момент времени C (по клиентскому времени) и запоминает его. Теперь клиент, из
предположения, что сетевые задержки при передаче сообщений от клиента серверу и от сервера клиенту одинаковы, может
определить и установить себе точное время, используя известные значения A, B, C.

Вам предстоит реализовать алгоритм, с точностью до секунды определяющий точное время для установки на клиенте по
известным A, B и C. При необходимости округлите результат до целого числа секунд по правилам арифметики (в меньшую
сторону, если дробная часть числа меньше 1/2, иначе в большую сторону).

Возможно, что, пока клиент ожидал ответа, по клиентскому времени успели наступить новые сутки, однако известно, что
между отправкой клиентом запроса и получением ответа от сервера прошло менее 24 часов.

Формат ввода
Программа получает на вход три временные метки A, B, C, по одной в каждой строке. Все временные метки представлены в
формате «hh:mm:ss», где «hh» – это часы, «mm» – минуты, «ss» – секунды. Часы, минуты и секунды записываются ровно двумя
цифрами каждое (возможно, с дополнительными нулями в начале числа).

Формат вывода
Программа должна вывести одну временную метку в формате, описанном во входных данных, – вычисленное точное время для
установки на клиенте. В выводе не должно быть пробелов, пустых строк в начале вывода.
*/

public class Task07 extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String A = reader.readLine().trim();
            String B = reader.readLine().trim();
            String C = reader.readLine().trim();

            LocalTime cliTimeRequest = LocalTime.parse(A);
            LocalTime srvTimeResponse = LocalTime.parse(B);
            LocalTime cliTimeResponse = LocalTime.parse(C);
            long diff = 0L;
            if (cliTimeResponse.isAfter(cliTimeRequest)) {
                diff = (cliTimeResponse.toNanoOfDay() - cliTimeRequest.toNanoOfDay()) / 2;
            } else {
                diff = (LocalTime.MAX.toNanoOfDay() + 1 - cliTimeRequest.toNanoOfDay() + cliTimeResponse.toNanoOfDay()) / 2;
            }
            LocalTime plus = srvTimeResponse.plusNanos(diff);
            plus = plus.plusSeconds((int) Math.ceil((double) plus.getNano() / 1000000000)).withNano(0);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            System.out.println(plus.format(timeFormatter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("15:01:00\n" +
                "18:09:45\n" +
                "15:01:40\n");
        main(new String[0]);
        String expected = "18:10:05\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("23:59:00\n" +
                "01:10:05\n" +
                "00:01:00\n");
        main(new String[0]);
        String expected = "01:11:05\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("23:59:00\n" +
                "23:59:59\n" +
                "00:03:00\n");
        main(new String[0]);
        String expected = "00:01:59\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("18:00:00\n" +
                "15:00:00\n" +
                "16:00:00\n");
        main(new String[0]);
        String expected = "02:00:00\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("17:00:00\n" +
                "15:00:00\n" +
                "09:00:00\n");
        main(new String[0]);
        String expected = "23:00:00\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("11:37:00\n" +
                "23:51:00\n" +
                "23:59:59\n");
        main(new String[0]);
        String expected = "06:02:30\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

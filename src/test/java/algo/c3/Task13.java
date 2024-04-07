package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
https://contest.yandex.ru/contest/45468/problems/13/
Постфиксная запись

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В постфиксной записи (или обратной польской записи) операция записывается после двух операндов. Например, сумма двух
чисел A и B записывается как A B +. Запись B C + D * обозначает привычное нам (B + C) * D, а запись A B C + D * +
означает A + (B + C) * D. Достоинство постфиксной записи в том, что она не требует скобок и дополнительных соглашений о
приоритете операторов для своего чтения.

Формат ввода
В единственной строке записано выражение в постфиксной записи,
содержащее цифры и операции +, -, *. Цифры и операции разделяются пробелами. В конце строки может быть произвольное
количество пробелов.

Формат вывода
Необходимо вывести значение записанного выражения.

*/

public class Task13 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine().trim();
            System.out.println(calclulate(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calclulate(String line) {
        Stack<Integer> stack = new Stack<>();
        String[] vals = line.split(" ");

        for (int i = 0; i < vals.length; i++) {
            try {
                Integer num = Integer.valueOf(vals[i]);
                stack.push(num);
            } catch (NumberFormatException e) {
                Integer val1 = stack.pop();
                Integer val2 = stack.pop();
                String command = vals[i];
                int res = 0;
                switch (command) {
                    case "+":
                        res = val1 + val2;
                        break;
                    case "*":
                        res = val1 * val2;
                        break;
                    case "-":
                        res = val2 - val1;
                        break;
                    case "/":
                        res = val2 / val1;
                        break;
                }
                stack.push(res);
            }
        }
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            return 0;
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("8 9 + 1 7 - *\n\n");
        main(new String[0]);
        String expected = "-102\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

package algo.open.summer2023.week2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/50242/problems/C/
Замена слов

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

С целью экономии чернил в картридже принтера было принято решение укоротить некоторые слова в тексте. Для этого был составлен словарь слов, до которых можно сокращать более длинные слова. Слово из текста можно сократить, если в словаре найдется слово, являющееся началом слова из текста. Например, если в списке есть слово "лом", то слова из текста "ломбард", "ломоносов" и другие слова, начинающиеся на "лом", можно сократить до "лом".

Если слово из текста можно сократить до нескольких слов из словаря, то следует сокращать его до самого короткого слова.

Формат ввода
В первой строке через пробел вводятся слова из словаря, слова состоят из маленьких латинских букв. Гарантируется, что словарь не пуст и количество слов в словаре не превышет 1000, а длина слов — 100 символов.
Во второй строке через пробел вводятся слова текста (они также состоят только из маленьких латинских букв). Количество слов в тексте не превосходит 105, а суммарное количество букв в них — 106.

Формат вывода
Выведите текст, в котором осуществлены замены
 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Set<String> dict = Arrays.stream(reader.readLine().trim().split(" ")).map(el -> el).collect(Collectors.toSet());
            String[] words = reader.readLine().trim().split(" ");

            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                int len = Math.min(word.length(), 101);
                for (int i = 1; i <= len; i++) {
                    String sub = word.substring(0, i);
                    if (dict.contains(sub)) {
                        words[j] = sub;
                        break;
                    }
                }
            }
            System.out.println(Arrays.stream(words).collect(Collectors.joining(" ")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("a b\n" +
                "abdafb basrt casds dsasa a");
        main(new String[0]);
        String expected = "a b casds dsasa a\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("aa bc aaa\n" +
                "a aa aaa bcd abcd\n");
        main(new String[0]);
        String expected = "a aa aa bc abcd\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

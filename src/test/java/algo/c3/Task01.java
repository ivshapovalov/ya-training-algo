package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/*
https://contest.yandex.ru/contest/45468/problems/1/
Гистограмма

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вовочка ломает систему безопасности Пентагона. Для этого ему понадобилось узнать, какие символы в секретных
зашифрованных посланиях употребляются чаще других. Для удобства изучения Вовочка хочет получить графическое
представление встречаемости символов. Поэтому он хочет построить гистограмму количества символов в сообщении.
Гистограмма — это график, в котором каждому символу, встречающемуся в сообщении хотя бы один раз, соответствует столбик,
высота которого пропорциональна количеству этих символов в сообщении.

Формат ввода
Входной файл содержит зашифрованный текст сообщения. Он содержит строчные и прописные латинские буквы,
цифры, знаки препинания («.», «!», «?», «:», «-», «,», «;», «(», «)»), пробелы и переводы строк. Размер входного файла
не превышает 10000 байт. Текст содержит хотя бы один непробельный символ. Все строки входного файла не длиннее 200
символов.Для каждого символа c кроме пробелов и переводов строк выведите столбик из символов «#», количество которых
должно быть равно количеству символов c в данном тексте. Под каждым столбиком напишите символ, соответствующий ему.
Отформатируйте гистограмму так, чтобы нижние концы столбиков были на одной строке, первая строка и первый столбец были
непустыми. Не отделяйте столбики друг от друга. Отсортируйте столбики в порядке увеличения кодов символов.

Формат вывода
Для каждого символа c кроме пробелов и переводов строк выведите столбик из символов «#», количество
которых должно быть равно количеству символов c в данном тексте. Под каждым столбиком напишите символ, соответствующий
ему. Отформатируйте гистограмму так, чтобы нижние концы столбиков были на одной строке, первая строка и первый столбец
были непустыми. Не отделяйте столбики друг от друга. Отсортируйте столбики в порядке увеличения кодов символов.

*/

public class Task01 extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine().trim();
            StringBuilder input = new StringBuilder();
            while (line != null) {
                input.append(line).append("\n");
                line = r.readLine();
            }
            Map<Character, Integer> freq = new TreeMap<>(Comparator.comparingInt(o -> o.charValue()));
            int max = 0;
            for (char ch : input.toString().toCharArray()) {
                if (ch == ' ' || ch == '\n') continue;
                int cur = freq.getOrDefault(ch, 0) + 1;
                max = Math.max(max, cur);
                freq.put(ch, cur);
            }
            AtomicInteger maxLine = new AtomicInteger(max);
            while (maxLine.get() > 0) {
                freq.entrySet().forEach(el -> {
                    if (el.getValue() >= maxLine.get()) {
                        System.out.print("#");
                    } else {
                        System.out.print(" ");
                    }
                });
                maxLine.decrementAndGet();
                System.out.println();
            }
            freq.entrySet().forEach(el -> System.out.print(el.getKey()));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("Hello, world! \n");
        main(new String[0]);
        String expected = "     #   \n" + "     ##  \n" + "#########\n" + "!,Hdelorw\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("Twas brillig, and the slithy toves \n" + "Did gyre and gimble in the wabe; \n" + "All mimsy were the borogoves, \n" + "And the mome raths outgrabe.");
        main(new String[0]);
        String expected = "         #              \n" + "         #              \n" + "         #              \n" + "         #              \n" + "         #              \n" + "         #         #    \n" + "         #  #      #    \n" + "      #  # ###  ####    \n" + "      ## ###### ####    \n" + "      ##############    \n" + "      ##############  ##\n" + "#  #  ############## ###\n" + "########################\n" + ",.;ADTabdeghilmnorstuvwy\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

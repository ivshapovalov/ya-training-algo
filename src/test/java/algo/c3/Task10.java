package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.stream.IntStream;

/*
https://contest.yandex.ru/contest/45468/problems/10/
Скучная лекция

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt
Лёша сидел на лекции. Ему было невероятно скучно. Голос лектора казался таким далеким и незаметным...

Чтобы окончательно не уснуть, он взял листок и написал на нём свое любимое слово. Чуть ниже он повторил своё любимое
слово, без первой буквы. Ещё ниже он снова написал своё любимое слово, но в этот раз без двух первых и последней буквы.

Тут ему пришла в голову мысль — времени до конца лекции все равно ещё очень много, почему бы не продолжить выписывать
всеми возможными способами это слово без какой-то части с начала и какой-то части с конца?

После лекции Лёша рассказал Максу, как замечательно он скоротал время. Максу стало интересно посчитать, сколько букв
каждого вида встречается у Лёши в листочке. Но к сожалению, сам листочек куда-то запропастился.

Макс хорошо знает любимое слово Лёши, а ещё у него не так много свободного времени, как у его друга, так что помогите
ему быстро восстановить, сколько раз Лёше пришлось выписать каждую букву.

Формат ввода
На вход подаётся строка, состоящая из строчных латинских букв — любимое слово Лёши.

Длина строки лежит в пределах от 5 до 100000 символов.

Формат вывода
Для каждой буквы на листочке Лёши, выведите её, а затем через двоеточие и пробел сколько раз она встретилась в
выписанных Лёшей словах (см. формат вывода в примерах). Буквы должны следовать в алфавитном порядке. Буквы, не
встречающиеся на листочке, выводить не нужно.
*/

public class Task10 extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine().trim();
            long[] map = new long[26];
            int len = str.length();
            long sum = len;
            for (int i = 0; i < len; i++) {
                char cur = str.charAt(i);
                sum += (i == 0) ? 0 : len - i * 2L;
                map[cur - 'a'] += sum;
            }

            IntStream.rangeClosed(0, 25).filter(ind -> map[ind] != 0).forEachOrdered(ind -> {
                System.out.println((char) (ind + 'a') + ": " + map[ind]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("hello\n\n\n");
        main(new String[0]);
        String expected = "e: 8\n" +
                "h: 5\n" +
                "l: 17\n" +
                "o: 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("abacaba\n");
        main(new String[0]);
        String expected = "a: 44\n" +
                "b: 24\n" +
                "c: 16\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f9() throws FileNotFoundException {
        String testNumber = "09";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}

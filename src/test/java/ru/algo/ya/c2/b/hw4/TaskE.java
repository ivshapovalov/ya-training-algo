package ru.algo.ya.c2.b.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/28970/problems/E/
Форум

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Клуб Юных Хакеров организовал на своем сайте форум. Форум имеет следующую структуру: каждое сообщение либо начинает
новую тему, либо является ответом на какое-либо предыдущее сообщение и принадлежит той же теме.

После нескольких месяцев использования своего форума юных хакеров заинтересовал вопрос - какая тема на их форуме
наиболее популярна. Помогите им выяснить это.

Формат ввода
В первой строке вводится целое число N - количество сообщений в форуме (1 <= N <= 1000). Следующие строки содержат
описание сообщений в хронологическом порядке.

Описание сообщения, которое представляет собой начало новой темы, состоит из трех строк. Первая строка содержит число 0.
Вторая строка содержит название темы. Длина названия не превышает 30 символов. Третья строка содержит текст сообщения.

Описание сообщения, которое является ответом на другое сообщение, состоит из двух строк. Первая строка содержит целое
число - номер сообщения, ответом на которое оно является. Сообщения нумеруются, начиная с единицы. Ответ всегда
появляется позже, чем сообщение, ответом на которое он является. Вторая строка содержит текст сообщения.

Длина каждого из сообщений не превышает 100 символов.

Формат вывода
Выведите название темы, к которой относится наибольшее количество сообщений. Если таких тем несколько, то выведите
первую в хронологическом порядке

 */

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine().trim());
            Map<String, Map<Integer, String>> forum = new LinkedHashMap<>();
            Map<Integer, String> messages = new HashMap<>();
            int newMessageId = 0;
            for (int i = 0; i < N; i++) {
                int prevMessageId = Integer.parseInt(reader.readLine().trim());
                if (prevMessageId == 0) {
                    newMessageId++;
                    String topic = reader.readLine().trim();
                    String message = reader.readLine().trim();
                    Map<Integer, String> topicHistory = forum.getOrDefault(topic, new LinkedHashMap<>());
                    topicHistory.put(newMessageId, message);
                    forum.put(topic, topicHistory);
                    messages.put(newMessageId, topic);
                } else {
                    newMessageId++;
                    String message = reader.readLine().trim();
                    String topic = messages.get(prevMessageId);
                    Map<Integer, String> topicHistory = forum.getOrDefault(topic, new HashMap<>());
                    topicHistory.put(newMessageId, message);
                    forum.put(topic, topicHistory);
                    messages.put(newMessageId, topic);

                }
            }
            String result = forum.entrySet().stream().sorted(new Comparator<Map.Entry<String, Map<Integer, String>>>() {
                @Override
                public int compare(Map.Entry<String, Map<Integer, String>> o1, Map.Entry<String, Map<Integer, String>> o2) {
                    return o1.getValue().size() == o2.getValue().size() ? 0 :
                            o2.getValue().size() - o1.getValue().size();
                }
            }).map(entry -> entry.getKey()).findFirst().get();
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("7\n" +
                "0\n" +
                "Олимпиада по информатике\n" +
                "Скоро третья командная олимпиада?\n" +
                "0\n" +
                "Новая компьютерная игра\n" +
                "Вышла новая крутая игра!\n" +
                "1\n" +
                "Она пройдет 24 ноября\n" +
                "1\n" +
                "В Санкт-Петербурге и Барнауле\n" +
                "2\n" +
                "Где найти?\n" +
                "4\n" +
                "Примет участие более 50 команд\n" +
                "6\n" +
                "Интересно, какие будут задачи\n");
        main(new String[0]);
        String expected = "Олимпиада по информатике\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n" +
                "0\n" +
                "topic 1\n" +
                "body 1\n");
        main(new String[0]);
        String expected = "topic 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f008() throws FileNotFoundException {
        String testNumber = "008";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "@Qm&u>uA9&|i+V3gsa?3;KG/`63,H\\\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f009() throws FileNotFoundException {
        String testNumber = "009";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
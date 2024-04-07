package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/45468/problems/19/
Хипуй

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В этой задаче вам необходимо самостоятельно (не используя соответствующие классы и функции стандартной библиотеки)
организовать структуру данных Heap для хранения целых чисел, над которой определены следующие операции: a) Insert(k) –
добавить в Heap число k ; b) Extract достать из Heap наибольшее число (удалив его при этом).

Формат ввода
В первой строке содержится количество команд N (1 ≤ N ≤ 100000), далее следуют N команд, каждая в своей строке. Команда
может иметь формат: “0 <число>” или “1”, обозначающий, соответственно, операции Insert(<число>) и Extract.
Гарантируется, что при выполнении команды Extract в структуре находится по крайней мере один элемент.

Формат вывода
Для каждой команды извлечения необходимо отдельной строкой вывести число, полученное при выполнении команды Extract.
*/
public class Task19 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            MaxHeap heap = new MaxHeap(100000);
            for (int i = 0; i < n; i++) {
                int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                if (input[0] == 0) {
                    heap.insert(input[1]);
                } else {
                    int res = heap.extract();
                    System.out.print(res + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "0 10000\n" +
                "1\n");
        main(new String[0]);
        String expected = "10000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("14\n" +
                "0 1\n" +
                "0 345\n" +
                "1\n" +
                "0 4346\n" +
                "1\n" +
                "0 2435\n" +
                "1\n" +
                "0 235\n" +
                "0 5\n" +
                "0 365\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n");
        main(new String[0]);
        String expected = "345\n" +
                "4346\n" +
                "2435\n" +
                "365\n" +
                "235\n" +
                "5\n" +
                "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


    static public class MaxHeap {
        private final int[] Heap;
        private final int maxsize;
        private int size;

        public MaxHeap(int maxsize) {
            this.maxsize = maxsize;
            this.size = 0;
            Heap = new int[this.maxsize];
        }

        private int parent(int pos) {
            return (pos - 1) / 2;
        }

        private int leftChild(int pos) {
            return (2 * pos) + 1;
        }

        private int rightChild(int pos) {
            return (2 * pos) + 2;
        }

        private boolean isLeaf(int pos) {
            return pos > (size / 2) && pos <= size;
        }

        private void swap(int fpos, int spos) {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        private void maxHeapify(int pos) {
            if (isLeaf(pos))
                return;

            if (Heap[pos] < Heap[leftChild(pos)]
                    || Heap[pos] < Heap[rightChild(pos)]) {

                if (Heap[leftChild(pos)]
                        > Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }

        public void insert(int element) {
            Heap[size] = element;

            int current = size;
            while (Heap[current] > Heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
            size++;
        }

        public int extract() {
            int popped = Heap[0];
            Heap[0] = Heap[--size];
            Heap[size] = 0;
            maxHeapify(0);
            return popped;
        }
    }
}

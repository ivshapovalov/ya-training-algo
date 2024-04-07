package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/46304/problems/E/
Царь Леонид на тракторе

Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
Все языки 	5 секунд 	1Gb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
Python 3.9 (PyPy 7.3.11) 	10 секунд 	1Gb
Python 3.7.3 	20 секунд 	1Gb
Python 3.6 	20 секунд 	1Gb

Царь Леонид ездит на тракторе по прямоугольному клетчатому полю размером H × W. Трактор у Леонида модели «только вперёд»
и не имеет руля. Когда трактор всё-таки нужно повернуть, то на помощь Леониду приходят спартанцы — они поднимают трактор
и поворачивают его в одном из 8 направлений (по вертикали, горизонтали или двум диагоналям).

Местостность в окрестностях Спарты каменистая и некоторые клетки непроходимы даже на тракторе. Царь Леонид хочет
торжественно проехать из одной точки поля в другую, сделав при этом наименьшее количество поворотов (силы спартанцев
надо беречь для боя с Ксерксом). Изначально трактор лежит в начальной точке вверх колесами, чтобы его направить
куда-либо нужно привлечь спартанцев. Направление трактора в конечной точке не имеет значения.

Формат ввода
В первой строке входного файла заданы целые числа H, W (1 ≤ H, W ≤ 1000) — высота и ширина поля.

В последующих H строках задано поле, блокированные клетки обозначены латинскими буквами "X", а свободные — точками.

После поля следует строка с двумя целыми числами sx, sy (1 ≤ sx ≤ W, 1 ≤ sy ≤ H) — координаты стартового положения
трактора.

Последней строкой идут два целых числа tx, ty (1 ≤ tx ≤ W, 1 ≤ ty ≤ H) — координаты конечного положения трактора.

Координаты отсчитываются от нижнего левого угла поля. Стартовое положение не совпадает с конечным. Стартовая и конечная
клетки не являются заблокированными.

Формат вывода
Если пути не существует — выведите одно число -1. Иначе выведите единственное натуральное число — минимальное количество
поворотов, которые спартанцы должны будут сделать по пути к конечной клетке.

*/
public class Task45 extends ContestTask {

    public static void main1(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int h = input[0];
            int w = input[1];
            String BLOCKED = "X";
            String[][] board = new String[h + 2][w + 2];
            VisitedCell[][] visited = new VisitedCell[h + 2][w + 2];
            Arrays.fill(board[0], BLOCKED);
            Arrays.fill(board[h + 1], BLOCKED);
            for (int i = 1; i <= h; i++) {
                Arrays.fill(board[i], BLOCKED);
                for (int j = 0; j < visited[i].length; j++) {
                    visited[i][j] = new VisitedCell(0, new Dir(0, 0));
                }
                String[] row = reader.readLine().trim().split("");
                System.arraycopy(row, 0, board[i], 1, w);
            }
            int[] coordFrom = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int rowFrom = h - coordFrom[1] + 1;
            int colFrom = coordFrom[0];
            int[] coordTo = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int rowTo = h - coordTo[1] + 1;
            int colTo = coordTo[0];
            Deque<String[]> q = new ArrayDeque<>();
            q.offer(new String[]{String.valueOf(rowFrom), String.valueOf(colFrom), "1", "0", "0"});
            Dir[] dirs = {new Dir(0, 1), new Dir(1, 1), new Dir(1, 0), new Dir(1, -1),
                    new Dir(0, -1), new Dir(-1, -1), new Dir(-1, 0), new Dir(-1, 1)};
            int result = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                String[] cur = q.poll();
                int row = Integer.valueOf(cur[0]);
                int col = Integer.valueOf(cur[1]);
                int step = Integer.valueOf(cur[2]);
                int dirRow = Integer.valueOf(cur[3]);
                int dirCol = Integer.valueOf(cur[4]);
                Dir dirNew = new Dir(dirRow, dirCol);

                VisitedCell visitedCell = visited[row][col];
                int prevStep = visitedCell.step;
                Set<Dir> prevDirs = visitedCell.dirs;
                if (prevStep == 0
                        || prevStep > step
                        || (prevStep == step && !prevDirs.contains(Arrays.asList(dirNew, dirNew.opposite())))) {
                    visitedCell.addDir(dirNew);
                    visitedCell.step = step;
                    if (row == rowTo && col == colTo) {
                        result = Math.min(result, step);
                    }
                    for (Dir dir : dirs) {
                        int diffRow = dir.row;
                        int diffCol = dir.col;
                        if (!board[row + diffRow][col + diffCol].equals(BLOCKED)) {
                            visitedCell = visited[row + diffRow][col + diffCol];
                            prevStep = visitedCell.step;
                            prevDirs = visitedCell.dirs;
                            if (prevStep == 0 || prevStep > step || (prevStep == step && !prevDirs.contains(Arrays.asList(dirNew, dirNew.opposite())))) {
                                if ((diffCol == dirCol && diffRow == dirRow) || (dirCol == 0 && dirRow == 0)) {
                                    q.addFirst(
                                            new String[]{
                                                    String.valueOf(row + diffRow),
                                                    String.valueOf(col + diffCol),
                                                    String.valueOf(step),
                                                    String.valueOf(diffRow),
                                                    String.valueOf(diffCol)
                                            });
                                } else {
                                    q.addLast(
                                            new String[]{
                                                    String.valueOf(row + diffRow),
                                                    String.valueOf(col + diffCol),
                                                    String.valueOf(step + 1),
                                                    String.valueOf(diffRow),
                                                    String.valueOf(diffCol)
                                            });
                                }
                            }
                        }
                    }
                }

            }
            System.out.print((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int h = input[0];
            int w = input[1];
            String BLOCKED = "X";
            String[][] board = new String[h + 2][w + 2];
            VisitedCell[][] visited = new VisitedCell[h + 2][w + 2];
            Arrays.fill(board[0], BLOCKED);
            Arrays.fill(board[h + 1], BLOCKED);
            for (int i = 1; i <= h; i++) {
                Arrays.fill(board[i], BLOCKED);
                for (int j = 0; j < visited[i].length; j++) {
                    visited[i][j] = new VisitedCell(0, new Dir(0, 0));
                }
                String[] row = reader.readLine().trim().split("");
                System.arraycopy(row, 0, board[i], 1, w);
            }
            int[] coordFrom = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int rowFrom = h - coordFrom[1] + 1;
            int colFrom = coordFrom[0];
            int[] coordTo = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int rowTo = h - coordTo[1] + 1;
            int colTo = coordTo[0];
            Deque<Action> q = new ArrayDeque<>();
            q.offer(new Action(rowFrom, colFrom, 1, new Dir(0, 0)));
            visited[rowFrom][colFrom] = new VisitedCell(-1, new Dir(0, 0));
            Dir[] dirs = {new Dir(0, 1), new Dir(1, 1), new Dir(1, 0), new Dir(1, -1),
                    new Dir(0, -1), new Dir(-1, -1), new Dir(-1, 0), new Dir(-1, 1)};
            int result = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                Action action = q.poll();

                VisitedCell visitedCell = visited[action.row][action.col];
                int prevStep = visitedCell.step;
                Set<Dir> prevDirs = visitedCell.dirs;
                if (prevStep <= 0
                        || prevStep > action.step
                        || (prevStep == action.step
                        && !prevDirs.contains(action.dir) && !prevDirs.contains(action.dir.opposite()))) {
                    if (prevStep == -1) {
                        for (Dir dir : dirs) {
                            visitedCell.addDir(dir);
                        }
                    } else {
                        visitedCell.addDir(action.dir);
                    }
                    visitedCell.step = action.step;

                    if (action.row == rowTo && action.col == colTo) {
                        result = Math.min(result, action.step);
                    }
                    for (Dir dir : dirs) {
                        int newDirRow = dir.row;
                        int newDirCol = dir.col;
                        if (!board[action.row + newDirRow][action.col + newDirCol].equals(BLOCKED)) {
                            VisitedCell neigCell = visited[action.row + newDirRow][action.col + newDirCol];
                            int neigCellPreviousStep = neigCell.step;
                            Set<Dir> neigCellPreviousDirs = neigCell.dirs;
                            int currentStep = action.step + 1;
                            if ((newDirCol == action.dir.col && newDirRow == action.dir.row) || (action.dir.row == 0 && action.dir.col == 0)) {
                                currentStep = action.step;
                            }
                            if (neigCellPreviousStep <= 0
                                    || neigCellPreviousStep > currentStep
                                    || (neigCellPreviousStep == currentStep
                                    && !neigCellPreviousDirs.contains(action.dir) && !neigCellPreviousDirs.contains(action.dir.opposite()))) {
                                if (currentStep == action.step) {
                                    q.addFirst(
                                            new Action(
                                                    action.row + newDirRow,
                                                    action.col + newDirCol,
                                                    currentStep,
                                                    new Dir(newDirRow, newDirCol)
                                            ));
                                } else {
                                    q.addLast(
                                            new Action(
                                                    action.row + newDirRow,
                                                    action.col + newDirCol,
                                                    action.step + 1,
                                                    new Dir(newDirRow, newDirCol)
                                            ));
                                }
                            }
                        }
                    }
                }

            }
            System.out.print((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_1() {
        provideConsoleInput("5 7\n" +
                "XX....X\n" +
                "X.XXX..\n" +
                "..XXX.X\n" +
                "X.X...X\n" +
                "....XXX\n" +
                "1 1\n" +
                "6 5\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("4 3\n" +
                ".XX\n" +
                ".XX\n" +
                "XX.\n" +
                "X..\n" +
                "1 3\n" +
                "3 2\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_3() {
        provideConsoleInput("4 3\n" +
                ".XX\n" +
                ".XX\n" +
                "X..\n" +
                "X..\n" +
                "1 3\n" +
                "3 2\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_4() {
        provideConsoleInput("4 3\n" +
                ".XX\n" +
                ".XX\n" +
                "X..\n" +
                "X..\n" +
                "1 2\n" +
                "2 2\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
//
//    @Test
//    public void test_f5() throws FileNotFoundException {
//        testIn = new ByteArrayInputStream(
//                getFileContent(class, "/src/main/java/algo.c3/t_final/tE/05.txt").getBytes());
//        String expected = getFileContent(class, "/src/main/java/algo.c3/t_final/tE/05.a.txt");
//        System.setIn(testIn);
//        main(new String[0]);
//        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
//    }
//
//    @Test
//    public void test_f3() throws FileNotFoundException {
//        testIn = new ByteArrayInputStream(
//                getFileContent(class, "/src/main/java/algo.c3/t_final/tE/03.txt").getBytes());
//        String expected = getFileContent(class, "/src/main/java/algo.c3/t_final/tE/03.a.txt.txt");
//        System.setIn(testIn);
//        main(new String[0]);
//        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
//    }
//
//    @Test
//    public void test_f6() throws FileNotFoundException {
//        testIn = new ByteArrayInputStream(
//                getFileContent(class, "/src/main/java/algo.c3/t_final/tE/06.txt").getBytes());
//        String expected = "3\n";
//        System.setIn(testIn);
//        main(new String[0]);
//        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
//    }
//
//    @Test
//    public void test_f16() throws FileNotFoundException {
//        testIn = new ByteArrayInputStream(
//                getFileContent(class, "/src/main/java/algo.c3/t_final/tE/16.txt").getBytes());
//        String expected = "2\n";
//        System.setIn(testIn);
//        main(new String[0]);
//        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
//    }

    static class Action {
        int row;
        int col;
        int step;
        Dir dir;

        public Action(int row, int col, int step, Dir dir) {
            this.row = row;
            this.col = col;
            this.step = step;
            this.dir = dir;
        }
    }

    static class Dir {
        int row;
        int col;

        public Dir(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Dir opposite() {
            return new Dir(-1 * this.row, -1 * this.col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dir dir = (Dir) o;
            return row == dir.row && col == dir.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    static class VisitedCell {
        int step;
        Set<Dir> dirs;

        public VisitedCell(int step, Dir dir) {
            this.step = step;
            this.dirs = new HashSet<>(Collections.singletonList(dir));
        }

        public void addDir(Dir dir) {
            this.dirs.add(dir);
        }
    }

}

package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/59541/problems/I/
Играйте в футбол!

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt



Ася Вуткина — известный футбольный комментатор. Будучи профессионалом своего дела, Ася тщательно следит за всеми матчами
всех европейских чемпионатов.

Благодаря накопленной информации, Ася может во время трансляции матча сообщить какую-нибудь интересную статистику,
например: «Индзаги третий матч подряд забивает гол на 9-й минуте» или «Матерацци никогда не открывает счет в матче».

Но мозг Аси не безграничен, а помнить всю историю футбола просто невозможно. Поэтому Ася попросила вас написать
программу, которая собирает статистику матчей и умеет отвечать на некоторые запросы, касающиеся истории футбола.

Информация о матче сообщается программе в следующей форме:

"<Название 1-й команды>" - "<Название 2-й команды>" <Счет 1-й команды>:<Счет 2-й команды>

<Автор 1-го забитого мяча 1-й команды> <Минута, на которой был забит мяч>'

<Автор 2-го забитого мяча 1-й команды> <Минута, на которой был забит мяч>'

...

<Автор последнего забитого мяча 1-й команды> <Минута, на которой был забит мяч>'

<Автор 1-го забитого мяча 2-й команды> <Минута, на которой был забит мяч>'

...

<Автор последнего забитого мяча 2-й команды> <Минута, на которой был забит мяч>'

Запросы к программе бывают следующих видов:

Total goals for <Название команды>

— количество голов, забитое данной командой за все матчи.

Mean goals per game for <Название команды>

— среднее количество голов, забиваемое данной командой за один матч. Гарантирутся, что к моменту подачи такого запроса
команда уже сыграла хотя бы один матч.

Total goals by <Имя игрока>

— количество голов, забитое данным игроком за все матчи.

Mean goals per game by <Имя игрока>

— среднее количество голов, забиваемое данным игроком за один матч его команды.

Гарантирутся, что к моменту подачи такого запроса игрок уже забил хотя бы один гол.

Goals on minute <Минута> by <Имя игрока>

— количество голов, забитых данным игроком ровно на указанной минуте матча.

Goals on first <T> minutes by <Имя игрока>

— количество голов, забитых данным игроком на минутах с первой по T-ю включительно.

Goals on last <T> minutes by <Имя игрока>

— количество голов, забитых данным игроком на минутах с (91 - T)-й по 90-ю включительно.

Score opens by <Название команды>

— сколько раз данная команда открывала счет в матче.

Score opens by <Имя игрока>

— сколько раз данный игрок открывал счет в матче.

Формат ввода
Входной файл содержит информацию о матчах и запросы в том порядке, в котором они поступают в программу Аси Вуткиной.

Во входном файле содержится информация не более чем о 100 матчах, в каждом из которых забито не более 10 голов. Всего в
чемпионате участвует не более 20 команд, в каждой команде не более 10 игроков забивают голы.

Все названия команд и имена игроков состоят только из прописных и строчных латинских букв и пробелов, а их длина не
превышает 30. Прописные и строчные буквы считаются различными. Имена и названия не начинаются и не оканчиваются
пробелами и не содержат двух пробелов подряд. Каждое имя и название содержит хотя бы одну букву.

Минута, на которой забит гол — целое число от 1 до 90 (про голы, забитые в дополнительное время, принято говорить, что
они забиты на 90-й минуте).

Для простоты будем считать, что голов в собственные ворота в европейских чемпионатах не забивают, и на одной минуте
матча может быть забито не более одного гола (в том числе на 90-й). Во время чемпионата игроки не переходят из одного
клуба в другой.

Количество запросов во входном файле не превышает 500.

Формат вывода
Для каждого запроса во входном файле выведите ответ на этот запрос в отдельной строке. Ответы на запросы,
подразумевающие нецелочисленный ответ, должны быть верны с точностью до трех знаков после запятой.

*/

public class TaskI extends ContestTask {
    static Map<String, Player> players = new HashMap<>();
    static Map<String, Team> teams = new HashMap<>();

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String totalGoalsFor = "Total goals for";
            String meanGoalsPerGameFor = "Mean goals per game for";
            String totalGoalsBy = "Total goals by";
            String meanGoalsPerGameBy = "Mean goals per game by";
            String goalsOnMinute = "Goals on minute";
            String goalsOnFirst = "Goals on first";
            String goalsOnLast = "Goals on last";
            String scoreOpensBy = "Score opens by";
            Function<String, Object> getTotalGoalsForTeam = (String teamName) -> getTotalGoalsForTeam(teamName);
            Function<String, Object> getMeanGoalsPerGameForTeam = (String teamName) -> getMeanGoalsPerGameForTeam(teamName);
            Function<String, Object> getTotalGoalsByPlayer = (String playerName) -> getTotalGoalsByPlayer(playerName);
            Function<String, Object> getMeanGoalsPerGameByPlayer = (String playerName) -> getMeanGoalsPerGameByPlayer(playerName);
            BiFunction<Integer, String, Object> getGoalsOnMinuteByPlayer = (Integer minute, String playerName) -> getGoalsOnMinuteByPlayer(minute, playerName);
            BiFunction<Integer, String, Object> getGoalsOnFirstByPlayer = (Integer minute, String playerName) -> getGoalsOnFirstByPlayer(minute, playerName);
            BiFunction<Integer, String, Object> getGoalsOnLastByPlayer = (Integer minute, String playerName) -> getGoalsOnLastByPlayer(minute, playerName);
            Function<String, Object> getScoreOpensBy = (String entityName) -> getScoreOpensBy(entityName);
            Map<String, Object> requests = Map.ofEntries(
                    Map.entry(totalGoalsFor, getTotalGoalsForTeam),
                    Map.entry(meanGoalsPerGameFor, getMeanGoalsPerGameForTeam),
                    Map.entry(totalGoalsBy, getTotalGoalsByPlayer),
                    Map.entry(meanGoalsPerGameBy, getMeanGoalsPerGameByPlayer),
                    Map.entry(goalsOnMinute, getGoalsOnMinuteByPlayer),
                    Map.entry(goalsOnFirst, getGoalsOnFirstByPlayer),
                    Map.entry(goalsOnLast, getGoalsOnLastByPlayer),
                    Map.entry(scoreOpensBy, getScoreOpensBy));
            Map<Integer, String> currentPlayers = new LinkedHashMap<>();
            Map<String, Integer> currentTeams = new LinkedHashMap<>();
            while (true) {

                String line = r.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                } else if (requests.keySet().stream().anyMatch(req -> line.startsWith(req))) {
                    //handle request
                    if (!currentTeams.isEmpty()) {
                        updateMatchStatistic(currentPlayers, currentTeams);
                        currentPlayers.clear();
                        currentTeams.clear();
                    }

                    Optional<Map.Entry<String, Object>> found = requests.entrySet().stream().filter(entry -> line.startsWith(entry.getKey()))
                            .findFirst();
                    if (found.isPresent()) {
                        Object function = found.get().getValue();
                        String prefix = found.get().getKey();
                        Object result = null;
                        if (function instanceof BiFunction<?, ?, ?>) {
                            //<T> minutes by <Имя игрока>
                            String remainder = line.substring(prefix.length()).strip();
                            Integer minute = Integer.parseInt(remainder.substring(0, remainder.indexOf(' ')));
                            String playerName = remainder.substring(remainder.indexOf("by") + 3).strip();
                            result = ((BiFunction<Integer, String, Object>) function).apply(minute, playerName);

                        } else {
                            String arg = line.substring(prefix.length()).strip();
                            if (!prefix.equals(scoreOpensBy)) {
                                arg = arg.replaceAll("\"", "");
                            } else {
                                int a;
                            }
                            result = ((Function<String, Object>) function).apply(arg);
                        }
                        System.out.println(result);
//                        System.out.println(line + " " + result);
                    }

                } else {
                    //handle input
                    //"Juventus" - "Milan" 3:1

                    if (line.contains("-")) {
                        if (!currentTeams.isEmpty()) {
                            updateMatchStatistic(currentPlayers, currentTeams);
                            currentPlayers.clear();
                            currentTeams.clear();
                        }
                        //match
                        String[] parts = line.split("\"");
                        String teamName1 = parts[1].strip();
                        String teamName2 = parts[3].strip();
                        int[] scores = Arrays.stream(parts[4].split(":")).mapToInt(el -> Integer.parseInt(el.strip())).toArray();
                        int score1 = scores[0];
                        int score2 = scores[1];

                        currentTeams.put(teamName1, score1);
                        currentTeams.put(teamName2, score2);

                    } else {
                        //goal
                        String[] partsName = line.split("\\d");
                        String playerName = partsName[0].strip();
                        String[] partsMinute = line.split(" ");
                        int minute = Integer.parseInt(partsMinute[partsMinute.length - 1].substring(0, partsMinute[partsMinute.length - 1].length() - 1).strip());

                        currentPlayers.put(minute, playerName);
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getScoreOpensBy(String entityName) {
        if (entityName.contains("\"")) {
            entityName = entityName.replaceAll("\"", "");
            if (teams.containsKey(entityName)) {
                Team team = teams.get(entityName);
                return team.getScoreOpensCount();
            }
            return 0;
        } else {
            if (players.containsKey(entityName)) {
                Player player = players.get(entityName);
                return player.getScoreOpensCount();
            }
            return 0;
        }
    }

    private static Object getGoalsOnLastByPlayer(Integer minute, String playerName) {
        if (players.containsKey(playerName)) {
            Player player = players.get(playerName);
            Integer goals = player.getGoalsOnMinute().entrySet().stream()
                    .filter(entry -> entry.getKey() >= 91 - minute && entry.getKey() <= 90)
                    .mapToInt(entry -> entry.getValue()).sum();
            return goals;
        }
        return 0;
    }

    private static Object getGoalsOnFirstByPlayer(Integer minute, String playerName) {
        if (players.containsKey(playerName)) {
            Player player = players.get(playerName);
            Integer goals = player.getGoalsOnMinute().entrySet().stream()
                    .filter(entry -> entry.getKey() <= minute)
                    .mapToInt(entry -> entry.getValue()).sum();
            return goals;
        }
        return 0;
    }

    private static Object getGoalsOnMinuteByPlayer(Integer minute, String playerName) {
        if (players.containsKey(playerName)) {
            Player player = players.get(playerName);
            Integer goals = player.getGoalsOnMinute().getOrDefault(minute, 0);
            return goals;
        }
        return 0;
    }

    private static Object getMeanGoalsPerGameByPlayer(String playerName) {
        if (players.containsKey(playerName)) {
            Player player = players.get(playerName);
            return player.getGoalsAvg();
        }
        return 0;
    }

    private static Object getTotalGoalsByPlayer(String playerName) {
        if (players.containsKey(playerName)) {
            Player player = players.get(playerName);
            return player.getGoalsAmount();
        }
        return 0;
    }

    private static Object getMeanGoalsPerGameForTeam(String teamName) {
        if (teams.containsKey(teamName)) {
            Team team = teams.get(teamName);
            return team.getGoalsAvg();
        }
        return 0;
    }

    private static Object getTotalGoalsForTeam(String teamName) {
        if (teams.containsKey(teamName)) {
            Team team = teams.get(teamName);
            return team.getGoalsAmount();
        }
        return 0;
    }

    private static void updateMatchStatistic(Map<Integer, String> currentPlayers, Map<String, Integer> currentTeams) {
        List<Map.Entry<String, Integer>> collectTeams = currentTeams.entrySet().stream().collect(Collectors.toList());
        Map.Entry<String, Integer> entry1 = collectTeams.get(0);
        Map.Entry<String, Integer> entry2 = collectTeams.get(1);
        String teamName1 = entry1.getKey();
        Integer score1 = entry1.getValue();

        String teamName2 = entry2.getKey();
        Integer score2 = entry2.getValue();

        Team team1 = teams.getOrDefault(teamName1, new Team(teamName1));
        team1.addResult(score1);
        teams.put(teamName1, team1);

        Team team2 = teams.getOrDefault(teamName2, new Team(teamName2));
        team2.addResult(score2);
        teams.put(teamName2, team2);

        List<Map.Entry<Integer, String>> collectPlayers = currentPlayers.entrySet().stream().collect(Collectors.toList());
        int counter = 0;
        int firstGoal = Integer.MAX_VALUE;
        String firstGoalPlayer = "";
        Team firstGoalTeam = null;
        Map<String, String> playerTeams = new HashMap<>();
        for (Map.Entry<Integer, String> entryPlayer : collectPlayers) {
            counter++;
            Integer minuteGoal = entryPlayer.getKey();
            String playerName = entryPlayer.getValue();

            if (counter > score1) {
                //2 команда первый гол
                if (minuteGoal < firstGoal) {
                    firstGoal = minuteGoal;
                    firstGoalPlayer = playerName;
                    firstGoalTeam = team2;
                }
                team2.addPlayer(playerName);
                playerTeams.put(playerName, teamName2);
            } else {
                if (minuteGoal < firstGoal) {
                    firstGoal = minuteGoal;
                    firstGoalPlayer = playerName;
                    firstGoalTeam = team1;
                }
                team1.addPlayer(playerName);
                playerTeams.put(playerName, teamName1);
            }
        }

        if (firstGoalTeam != null) {
            firstGoalTeam.scoreOpensCount = firstGoalTeam.scoreOpensCount + 1;
        }
        Map<String, List<Integer>> playersWithMinutes = currentPlayers.entrySet().stream()
                .collect(Collectors.groupingBy(el -> el.getValue(),
                        Collectors.flatMapping(el -> List.of(el.getKey()).stream(), Collectors.toList())));
        List<String> playerNames = new ArrayList<>(playersWithMinutes.keySet());
        for (String playerName : playerNames) {
            Player player = players.getOrDefault(playerName, new Player(playerName));
            player.addResult(playersWithMinutes.getOrDefault(playerName, new ArrayList<>()), firstGoalPlayer.equals(playerName));
            Team playerTeam = teams.get(playerTeams.get(playerName));
            player.setTeam(playerTeam);
            player.setGameAmount(playerTeam.gameAmount);
            players.put(playerName, player);
        }
        team1.playerNames.stream().filter(playerName -> !playerNames.contains(playerName)).forEach(playerName -> {
            Player player = players.get(playerName);
            player.setTeam(team1);
            player.addResult(new ArrayList<>(), false);
            player.setGameAmount(team1.gameAmount);
        });
        team2.playerNames.stream().filter(playerName -> !playerNames.contains(playerName)).forEach(playerName -> {
            Player player = players.get(playerName);
            player.setTeam(team2);
            player.addResult(new ArrayList<>(), false);
            player.setGameAmount(team2.gameAmount);
        });
    }

    @Test
    public void test_001() {
        provideConsoleInput("\"Juventus\" - \"Milan\" 3:1\n" +
                "Inzaghi 45'\n" +
                "Del Piero 67'\n" +
                "Del Piero 90'\n" +
                "Shevchenko 34'\n" +
                "Total goals for \"Juventus\"\n" +
                "Total goals by Pagliuca\n" +
                "Mean goals per game by Inzaghi\n" +
                "\"Juventus\" - \"Lazio\" 0:0\n" +
                "Mean goals per game by Inzaghi\n" +
                "Mean goals per game by Shevchenko\n" +
                "Score opens by Inzaghi\n");
        main(new String[0]);
        String expected = "3\n" +
                "0\n" +
                "1.0\n" +
                "0.5\n" +
                "1.0\n" +
                "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("Total goals by Arshavin\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f03() throws FileNotFoundException {
        String testNumber = "03";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f09() throws FileNotFoundException {
        String testNumber = "09";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Team {
        Set<String> playerNames = new HashSet<>();
        private final String name;
        private int goalsAmount;
        private int gameAmount;
        private double goalsAvg;
        private int scoreOpensCount;

        public Team(String name) {
            this.name = name;
        }

        public void addPlayer(String playerName) {
            playerNames.add(playerName);
        }

        public void addResult(int goals) {
            this.goalsAmount = this.goalsAmount + goals;
            this.gameAmount = this.gameAmount + 1;
            this.goalsAvg = 1.0 * this.goalsAmount / this.gameAmount;
        }

        public int getGoalsAmount() {
            return goalsAmount;
        }

        public void setGoalsAmount(int goalsAmount) {
            this.goalsAmount = goalsAmount;
        }

        public int getGameAmount() {
            return gameAmount;
        }

        public void setGameAmount(int gameAmount) {
            this.gameAmount = gameAmount;
        }

        public double getGoalsAvg() {
            return goalsAvg;
        }

        public void setGoalsAvg(double goalsAvg) {
            this.goalsAvg = goalsAvg;
        }

        public int getScoreOpensCount() {
            return scoreOpensCount;
        }

        public void setScoreOpensCount(int scoreOpensCount) {
            this.scoreOpensCount = scoreOpensCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Team team = (Team) o;
            return Objects.equals(name, team.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    static class Player {
        private final String name;
        private int goalsAmount;
        private int gameAmount;
        private double goalsAvg;
        private int scoreOpensCount;
        private Map<Integer, Integer> goalsOnMinute = new HashMap<>();
        private Team team;

        public Player(String name) {
            this.name = name;
        }

        public void addResult(List<Integer> minutes, boolean scoreOpen) {
            this.goalsAmount = this.goalsAmount + minutes.size();
            this.gameAmount = this.gameAmount + 1;
            this.goalsAvg = 1.0 * this.goalsAmount / this.gameAmount;
            for (int minute : minutes) {
                goalsOnMinute.put(minute, goalsOnMinute.getOrDefault(minute, 0) + 1);
            }
            if (scoreOpen) {
                scoreOpensCount++;
            }
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public int getGameAmount() {
            return gameAmount;
        }

        public void setGameAmount(int gameAmount) {
            this.gameAmount = gameAmount;
            this.goalsAvg = 1.0 * this.goalsAmount / this.gameAmount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player player = (Player) o;
            return Objects.equals(name, player.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public int getGoalsAmount() {
            return goalsAmount;
        }

        public void setGoalsAmount(int goalsAmount) {
            this.goalsAmount = goalsAmount;
        }

        public double getGoalsAvg() {
            return goalsAvg;
        }

        public void setGoalsAvg(double goalsAvg) {
            this.goalsAvg = goalsAvg;
        }

        public int getScoreOpensCount() {
            return scoreOpensCount;
        }

        public void setScoreOpensCount(int scoreOpensCount) {
            this.scoreOpensCount = scoreOpensCount;
        }

        public Map<Integer, Integer> getGoalsOnMinute() {
            return goalsOnMinute;
        }

        public void setGoalsOnMinute(Map<Integer, Integer> goalsOnMinute) {
            this.goalsOnMinute = goalsOnMinute;
        }
    }

}

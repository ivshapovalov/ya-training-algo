package ru.algo.ya.c1;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupChars {

    private List<List<String>> group(String[] input) {
        Map<String, List<String>> result = new HashMap<>();
        for (String str : input) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> strings = result.getOrDefault(key, new ArrayList<>());
            strings.add(str);
            Collections.sort(strings);
            result.put(key, strings);
        }
        return result.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());

    }

    @Test
    public void test_1() {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> actual = group(input);
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("ate", "eat", "tea"),
                Arrays.asList("bat"),
                Arrays.asList("nat", "tan")
        );
        assertEquals(expected, actual);
    }
}

package com.example.task04;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task04Main {
    private static final Pattern WORD = Pattern.compile("[\\p{L}\\p{Nd}]+");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Map<String, Long> frequencies = scanner.findAll(WORD)
                .map(MatchResult::group)
                .map(word -> word.toLowerCase(Locale.ROOT))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        frequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .map(Map.Entry::getKey)
                .forEachOrdered(word -> System.out.print(word + "\n"));

    }

}

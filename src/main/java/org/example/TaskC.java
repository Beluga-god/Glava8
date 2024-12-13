package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskC {

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static List<String> splitTextIntoSentences(String text) {
        String[] sentences = text.split("(?<=[.!?])\\s+"); // Разбиваем по окончаниям предложений
        return Arrays.stream(sentences).filter(s -> !s.trim().isEmpty()).collect(Collectors.toList());
    }

    public static Set<String> extractWords(String sentence) {
        // Очищаем предложение от всего, кроме букв и цифр, и разделяем на слова
        String cleanedSentence = sentence.replaceAll("[^a-zA-ZА-Яа-я0-9\\s]", "");
        String[] words = cleanedSentence.split("\\s+");

        // Создаем множество, добавляем в него слова в нижнем регистре
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordSet.add(word.toLowerCase()); // Приводим все слова к нижнему регистру
            }
        }

        return wordSet;
    }


    public static boolean hasCommonWord(Set<String> words1, Set<String> words2) {
        for (String word : words1) {
            if (words2.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> minimizeSentences(List<String> sentences) {
        List<Set<String>> sentenceWords = new ArrayList<>();

        // Преобразуем каждое предложение в множество слов
        for (String sentence : sentences) {
            sentenceWords.add(extractWords(sentence));
        }

        // Проверяем общие слова и минимизируем количество удалений
        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            boolean hasConnection = false;
            for (int j = 0; j < sentences.size(); j++) {
                if (i != j && hasCommonWord(sentenceWords.get(i), sentenceWords.get(j))) {
                    hasConnection = true;
                    break;
                }
            }
            if (!hasConnection) {
                toRemove.add(i);
            }
        }

        // Удаляем минимальное количество предложений
        Set<Integer> removeSet = new HashSet<>(toRemove);
        List<String> remainingSentences = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            if (!removeSet.contains(i)) {
                remainingSentences.add(sentences.get(i));
            }
        }

        return remainingSentences;
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/text.txt";
        String text = readFile(filePath);

        List<String> sentences = splitTextIntoSentences(text);

        System.out.println("Исходный текст:");
        sentences.forEach(System.out::println);

        List<String> minimizedSentences = minimizeSentences(sentences);

        System.out.println("\nОставшиеся предложения после минимизации:");
        minimizedSentences.forEach(System.out::println);
    }
}

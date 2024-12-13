package org.example;

import java.io.*;
import java.util.*;
import java.util.regex.*;

class Word {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}

class Sentence {
    private String text;

    public Sentence(String text) {
        this.text = text;
    }

    public boolean isQuestion() {
        return text.trim().endsWith("?"); // Проверяем, заканчивается ли предложение вопросительным знаком
    }

    public List<String> extractWords() {
        // Удаляем все символы, кроме букв и чисел, и разбиваем на слова
        String cleanedText = text.replaceAll("[^a-zA-ZА-Яа-я0-9\\s]", "");
        String[] wordsArray = cleanedText.split("\\s+");
        List<String> words = new ArrayList<>();
        for (String word : wordsArray) {
            if (!word.isEmpty()) {
                words.add(word.toLowerCase()); // Приводим все слова к нижнему регистру
            }
        }
        return words;
    }
}

class Paragraph {
    private List<Sentence> sentences;

    public Paragraph() {
        sentences = new ArrayList<>();
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public List<Sentence> getQuestionSentences() {
        List<Sentence> questionSentences = new ArrayList<>();
        for (Sentence sentence : sentences) {
            if (sentence.isQuestion()) {
                questionSentences.add(sentence);
            }
        }
        return questionSentences;
    }
}

public class TaskB {

    public static String normalizeText(String text) {
        return text.replaceAll("\\s+", " ").trim(); // Заменяем все последовательности пробелов и табуляции на один пробел
    }

    public static List<Paragraph> parseText(String text) {
        // Разбиваем текст на абзацы
        String[] paragraphsText = text.split("\n+");
        List<Paragraph> paragraphs = new ArrayList<>();

        for (String pText : paragraphsText) {
            if (pText.trim().isEmpty()) continue; // Пропускаем пустые абзацы

            Paragraph paragraph = new Paragraph();
            String[] sentencesText = pText.split("(?<=[.!?])\\s+"); // Разбиваем текст по завершению предложений
            for (String sText : sentencesText) {
                if (!sText.trim().isEmpty()) {
                    paragraph.addSentence(new Sentence(sText));
                }
            }
            paragraphs.add(paragraph);
        }

        return paragraphs;
    }

    public static Set<String> findWordsInQuestions(List<Paragraph> paragraphs, int length) {
        Set<String> wordsFound = new HashSet<>();

        for (Paragraph paragraph : paragraphs) {
            for (Sentence sentence : paragraph.getQuestionSentences()) {
                for (String word : sentence.extractWords()) {
                    if (word.length() == length) {
                        wordsFound.add(word); // Добавляем только те слова, которые соответствуют длине
                    }
                }
            }
        }

        return wordsFound;
    }

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Файл не существует: " + filePath);
            return ""; // Возвращаем пустую строку, если файл не существует
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return "";
        }
        return normalizeText(content.toString());
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/text.txt";
        String text = readFile(filePath);

        System.out.println("Текст был считан корректно:");
        System.out.println(text);

        List<Paragraph> paragraphs = parseText(text);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину слова, которое нужно найти: ");
        int wordLength = scanner.nextInt();

        Set<String> wordsFound = findWordsInQuestions(paragraphs, wordLength);

        if (wordsFound.isEmpty()) {
            System.out.println("Слова не найдены.");
        } else {
            System.out.println("Найденные слова:");
            for (String word : wordsFound) {
                System.out.println(word);
            }
        }

        scanner.close();
    }
}

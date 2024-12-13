package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TaskBTest {

    @Test
    public void testFindWordsInQuestions() {
        String text = "How are you today?\nDo you like Java?\nI hope so!";

        // Разбираем текст и извлекаем вопросительные предложения
        List<Paragraph> paragraphs = TaskB.parseText(text);

        // Ищем все слова длиной 2 в вопросительных предложениях
        Set<String> words = TaskB.findWordsInQuestions(paragraphs, 3); // Мы ищем слова длиной 3, так как "you" имеет длину 3

        Assertions.assertTrue(words.contains("you"), "Ожидалось слово 'you', но оно не найдено.");
        Assertions.assertTrue(words.contains("are"), "Ожидалось слово 'are', но оно не найдено.");
    }

    @Test
    public void testParseText() {
        String text = "This is a test.\nHow are you today?\n\nI hope so!"; // Убедитесь, что есть хотя бы один пустой абзац

        List<Paragraph> paragraphs = TaskB.parseText(text);

        Assertions.assertEquals(3, paragraphs.size(), "Ожидалось 3 абзаца, но получено: " + paragraphs.size());
    }

}

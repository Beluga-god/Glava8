package org.example;

import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TaskCTest {

    @Test
    public void testReadFile() {
        String filePath = "src/test/resources/testFile.txt";
        String expectedContent = "Hello world.\n";

        String content = TaskC.readFile(filePath);

        assertNotNull(content, "Текст не должен быть null");
        assertEquals(expectedContent, content, "Содержимое файла не соответствует ожидаемому.");
    }

    @Test
    public void testSplitTextIntoSentences() {
        String text = "This is a test. Hello world! How are you?";

        List<String> sentences = TaskC.splitTextIntoSentences(text);

        assertNotNull(sentences, "Список предложений не должен быть null.");
        assertEquals(3, sentences.size(), "Ожидалось 3 предложения.");
        assertTrue(sentences.contains("This is a test."), "Ожидалось предложение 'This is a test.'");
        assertTrue(sentences.contains("Hello world!"), "Ожидалось предложение 'Hello world!'");
        assertTrue(sentences.contains("How are you?"), "Ожидалось предложение 'How are you?'");
    }



    @Test
    public void testHasCommonWord() {
        Set<String> words1 = new HashSet<>(Arrays.asList("hello", "world", "java"));
        Set<String> words2 = new HashSet<>(Arrays.asList("java", "python", "ruby"));

        boolean result = TaskC.hasCommonWord(words1, words2);

        assertTrue(result, "Ожидалось, что слова будут общими.");

        words2 = new HashSet<>(Arrays.asList("python", "ruby"));

        result = TaskC.hasCommonWord(words1, words2);

        assertFalse(result, "Ожидалось, что общих слов не будет.");
    }

    @Test
    public void testMinimizeSentences() {
        List<String> sentences = Arrays.asList(
                "This is a test.",
                "Hello world!",
                "How are you?",
                "Are you sure?"
        );

        List<String> minimizedSentences = TaskC.minimizeSentences(sentences);

        assertNotNull(minimizedSentences, "Минимизированный список предложений не должен быть null.");
        assertTrue(minimizedSentences.size() < sentences.size(), "После минимизации должно быть меньше предложений.");
        assertTrue(minimizedSentences.contains("How are you?"), "Предложение 'How are you?' должно остаться.");
    }
}

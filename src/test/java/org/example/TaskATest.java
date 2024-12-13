package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskATest {


    @Test
    public void testInsertSubstring() {
        String result = TaskA.insertSubstring("Hello Java", 10, "World");
        Assertions.assertEquals("Hello JavaWorld", result);
    }


    @Test
    public void testReadTextFromFile() throws IOException {
        File file = new File("src/main/resources/testFile.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("Test content");
        writer.close();

        String result = TaskA.readTextFromFile(file.getPath());
        assertEquals("Test content\n", result);

        file.delete();
    }

}

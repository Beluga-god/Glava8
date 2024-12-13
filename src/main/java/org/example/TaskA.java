package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskA {
    public static String insertSubstring(String original, int position, String toInsert) {
        return original.substring(0, position) + toInsert + original.substring(position);
    }

    public static String readTextFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String text = readTextFromFile("src/main/resources/text.txt");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите индекс k: ");
            int k = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Введите подстроку для вставки: ");
            String substring = scanner.nextLine();

            String result = insertSubstring(text, k, substring);
            System.out.println("Результат: ");
            System.out.println(result);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Файл text.txt не найден.");
        }
    }
}


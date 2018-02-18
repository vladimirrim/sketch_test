package ru.spbau.egorov.tee;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private final String str = "abc";

    @Test
    public void teeOneFile() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(str.getBytes()));
        Main.main(new String[]{"test.txt"});

        assertEquals(str + '\n', outContent.toString());

        Scanner scanner = new Scanner(new FileInputStream(new File("test.txt")));
        assertEquals(str, scanner.nextLine());
    }

    @Test
    public void teeThreeFiles() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(str.getBytes()));
        Main.main(new String[]{"a.txt", "b.txt", "c.txt"});

        assertEquals(str + '\n', outContent.toString());
        {
            Scanner scanner = new Scanner(new FileInputStream(new File("a.txt")));
            assertEquals(str, scanner.nextLine());
        }
        {
            Scanner scanner = new Scanner(new FileInputStream(new File("b.txt")));
            assertEquals(str, scanner.nextLine());
        }
        {
            Scanner scanner = new Scanner(new FileInputStream(new File("c.txt")));
            assertEquals(str, scanner.nextLine());
        }
    }

    @Test
    public void teeAppendThreeFiles() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(str.getBytes()));
        Main.main(new String[]{"a.txt", "b.txt", "c.txt"});
        System.setIn(new ByteArrayInputStream(str.getBytes()));
        Main.main(new String[]{"-a", "a.txt", "b.txt", "c.txt"});
        assertEquals(str + '\n' + str + '\n', outContent.toString());
        {
            Scanner scanner = new Scanner(new FileInputStream(new File("a.txt")));
            assertEquals(str, scanner.nextLine());
            assertEquals(str, scanner.nextLine());
        }
        {
            Scanner scanner = new Scanner(new FileInputStream(new File("b.txt")));
            assertEquals(str, scanner.nextLine());
            assertEquals(str, scanner.nextLine());
        }
        {
            Scanner scanner = new Scanner(new FileInputStream(new File("c.txt")));
            assertEquals(str, scanner.nextLine());
            assertEquals(str, scanner.nextLine());
        }
    }
}
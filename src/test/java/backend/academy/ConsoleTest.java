package backend.academy;

import backend.academy.ui.Console;
import backend.academy.ui.GallowsStages;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleTest {
    private Console console;
    private Scanner scanner;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        scanner = mock(Scanner.class);
        console = new Console(scanner);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testGetValidInput() {
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt()).thenReturn(5);

        int result = console.getValidInput("Введите число > ", 10);

        assertEquals(5, result);
    }

    @Test
    void testGetValidInputNotInteger() {
        when(scanner.hasNextInt())
            .thenReturn(false)
            .thenReturn(true);

        when(scanner.nextInt()).thenReturn(5);

        int result = console.getValidInput("Введите число > ", 10);

        String output = outputStream.toString();
        assertTrue(output.contains(" ! Некорректный ввод: ожидается число"));

        assertEquals(5, result);
    }

    @Test
    void testGetValidInputOutOfRange() {
        when(scanner.hasNextInt()).thenReturn(true);
        when(scanner.nextInt())
            .thenReturn(100)
            .thenReturn(5);

        int result = console.getValidInput("Введите число > ", 10);

        String output = outputStream.toString();
        assertTrue(output.contains(" ! Некорректный ввод: число не в диапазоне от 0 до 10"));

        assertEquals(5, result);
    }

    @Test
    void testGetStringInput() {
        when(scanner.next()).thenReturn("тест");

        String result = console.getStringInput("Введите слово > ");

        assertEquals("тест", result);
    }

    @Test
    void testPrintlnWithMessage() {
        String message = "тест";

        console.println(message);

        String output = outputStream.toString();
        assertEquals(message + System.lineSeparator(), output);
    }

    @Test
    void testPrintWithMessage() {
        String message = "тест";

        console.print(message);

        String output = outputStream.toString();
        assertEquals(message, output);
    }

    @Test
    void testPrintHealth() {
        int healthNum = 5;

        console.printHealth(healthNum);

        String output = outputStream.toString();
        assertEquals("Осталось жизней: ❤ ❤ ❤ ❤ ❤ " + System.lineSeparator(), output);
    }

    @Test
    void testPrintHealthNegative() {
        int healthNum = -5;

        console.printHealth(healthNum);

        String output = outputStream.toString();
        assertEquals("Осталось жизней: " + System.lineSeparator(), output);
    }

    @Test
    void testPrintGallows() {
        double percent = 3. / 8;

        console.printGallows(percent);

        String output = outputStream.toString();
        assertEquals(GallowsStages.values()[2].stage() + System.lineSeparator(), output);
    }

    @Test
    void testPrintGallowsOverRange() {
        double percent = 10;

        console.printGallows(percent);

        String output = outputStream.toString();
        assertEquals(GallowsStages.values()[7].stage() + System.lineSeparator(), output);
    }

    @Test
    void testPrintGallowsBelowRange() {
        double percent = -10;

        console.printGallows(percent);

        String output = outputStream.toString();
        assertEquals(GallowsStages.values()[0].stage() + System.lineSeparator(), output);
    }

    @Test
    void printSecretWord() {
        char[] word = {'t', ' ', 'e', ' ', 's', 't'};

        console.printSecretWord(word);

        String output = outputStream.toString();
        assertEquals("Загаданное слово: t _ e _ s t " + System.lineSeparator(), output);
    }

    @Test
    void printSecretWordEmpty() {
        char[] word = {};

        console.printSecretWord(word);

        String output = outputStream.toString();
        assertEquals("Загаданное слово: " + System.lineSeparator(), output);
    }

    @Test
    void printHint() {
        boolean isUsedHint = false;
        String hint = "test";

        console.printHint(isUsedHint, hint);

        String output = outputStream.toString();
        assertNotEquals("Подсказка: test" + System.lineSeparator(), output);
    }

    @Test
    void printHintUsedHint() {
        boolean isUsedHint = true;
        String hint = "test";

        console.printHint(isUsedHint, hint);

        String output = outputStream.toString();
        assertEquals("Подсказка: test" + System.lineSeparator(), output);
    }
}

package backend.academy;

import backend.academy.ui.Console;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}

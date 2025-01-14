package backend.academy;

import backend.academy.ui.Console;
import backend.academy.ui.GallowsStages;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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
        outputStream = new ByteArrayOutputStream();
        console = new Console(scanner, new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
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

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"тест"})
    void testPrintlnWithMessage(String message) {
        console.println(message);

        String output = outputStream.toString();
        assertEquals(message + System.lineSeparator(), output);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"тест"})
    void testPrintWithMessage(String message) {
        console.print(message);

        String output = outputStream.toString();
        assertEquals(message, output);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 0, -5})
    void testPrintHealth(int healthNum) {
        console.printHealth(healthNum);

        String expectedOutput = "Осталось жизней: " + "❤ ".repeat(Math.max(0, healthNum)) + System.lineSeparator();
        String output = outputStream.toString();
        assertEquals(expectedOutput, output);
    }

    @ParameterizedTest
    @CsvSource({
        "0.375, 2",
        "10, 7",
        "-10, 0"
    })
    void testPrintGallows(double percent, int expectedStageIndex) {
        console.printGallows(percent);

        String output = outputStream.toString();

        assertEquals(GallowsStages.values()[expectedStageIndex].stage() + System.lineSeparator(), output);
    }

    @ParameterizedTest
    @MethodSource("provideSecretWordForPrint")
    void testPrintSecretWord(char[] word, String expectedOutput) {
        console.printSecretWord(word);

        String output = outputStream.toString();

        assertEquals(expectedOutput + System.lineSeparator(), output);
    }

    static Stream<Arguments> provideSecretWordForPrint() {
        return Stream.of(
            Arguments.of(new char[] {'t', ' ', 'e', ' ', 's', 't'}, "Загаданное слово: t _ e _ s t "),
            Arguments.of(new char[] {'h', 'e', 'l', 'l', 'o'}, "Загаданное слово: h e l l o "),
            Arguments.of(new char[] {'_', ' ', '_', ' ', '_'}, "Загаданное слово: _ _ _ _ _ "),
            Arguments.of(new char[] {}, "Загаданное слово: ")
        );
    }

    @ParameterizedTest
    @CsvSource({
        "false, test, Подсказка: test",
        "true, test, Подсказка:",
        "true, , Подсказка:"
    })
    void printHint(boolean isUsedHint, String hint, String expectedOutput) {
        console.printHint(isUsedHint, hint);

        String output = outputStream.toString();
        assertNotEquals(expectedOutput + System.lineSeparator(), output);
    }
}

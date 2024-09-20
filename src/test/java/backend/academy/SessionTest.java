package backend.academy;

import backend.academy.dictionary.Word;
import backend.academy.game.Session;
import backend.academy.ui.Console;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessionTest {
    private Session session;
    private Console console;

    @BeforeEach
    void setUp() {
        console = mock(Console.class);
        Word secretWord = mock(Word.class);
        when(secretWord.word()).thenReturn("test");
        session = new Session(secretWord, 5, console);
    }

    @Test
    void testIsGameOverInit() {
        assertFalse(session.isGameOver());
    }

    @Test
    void testIsWordGuessedInit() {
        assertFalse(session.isWordGuessed());
    }

    @Test
    void testGameOverAfterMaxAttempts() {
        session.guessLetter('x');
        session.guessLetter('x');
        session.guessLetter('x');
        session.guessLetter('x');
        session.guessLetter('x');
        assertFalse(session.isWordGuessed());
        assertTrue(session.isGameOver());
    }

    @Test
    void testGameOverAfterGuessed() {
        session.guessLetter('t');
        session.guessLetter('e');
        session.guessLetter('s');
        session.guessLetter('t');
        assertTrue(session.isWordGuessed());
        assertTrue(session.isGameOver());
    }

    @ParameterizedTest
    @MethodSource("provideCommandHint")
    void testHandleCommandHint(String[] commands, String expectedOutput) {
        for (String command : commands) {
            session.handleCommand(command.trim());
        }

        verify(console).println(expectedOutput);
    }

    static Stream<Arguments> provideCommandHint() {
        return Stream.of(
            Arguments.of(new String[] {"-hint", "-hint"}, " ! Подсказка уже активирована"),
            Arguments.of(new String[] {"-hint"}, "Использована подсказка!")
        );
    }

    @Test
    void testHandleCommandAllCorrect() {
        when(console.getStringInput(anyString())).thenReturn("test");
        session.handleCommand("-all");
        assertTrue(session.isWordGuessed());
        assertTrue(session.isGameOver());
    }

    @Test
    void testHandleCommandAllIncorrect() {
        when(console.getStringInput(anyString())).thenReturn("not test");
        session.handleCommand("-all");
        assertFalse(session.isWordGuessed());
        assertTrue(session.isGameOver());
    }
}

package backend.academy;

import backend.academy.dictionary.Word;
import backend.academy.game.Session;
import backend.academy.ui.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertTrue(session.isGameOver());
    }

    @Test
    void testGameOverAfterGuessed() {
        session.guessLetter('t');
        session.guessLetter('e');
        session.guessLetter('s');
        session.guessLetter('t');
        assertTrue(session.isGameOver());
    }

    @Test
    void testHandleCommandHint() {
        session.handleCommand("-hint");
        verify(console).println("Использована подсказка!");
    }

    @Test
    void testHandleCommandHintAgain() {
        session.handleCommand("-hint");
        session.handleCommand("-hint");
        verify(console).println(" ! Подсказка уже активирована");
    }

    @Test
    void testHandleCommandAllCorrect() {
        when(console.getStringInput(anyString())).thenReturn("test");
        session.handleCommand("-all");
        assertTrue(session.isWordGuessed());
    }

    @Test
    void testHandleCommandAllIncorrect() {
        when(console.getStringInput(anyString())).thenReturn("not test");
        session.handleCommand("-all");
        assertFalse(session.isWordGuessed());
        assertTrue(session.isGameOver());
    }
}

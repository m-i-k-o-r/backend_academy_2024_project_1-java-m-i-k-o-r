package backend.academy;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Difficulty;
import backend.academy.game.Settings;
import backend.academy.ui.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SettingsTest {
    private Console console;
    private Settings settings;

    @BeforeEach
    void setUp() {
        console = mock(Console.class);
        settings = new Settings(console);
    }

    @Test
    void testSelectCategory() {
        Category category1 = new Category("category1", new ArrayList<>());
        Category category2 = new Category("category2", new ArrayList<>());

        List<Category> categories = Arrays.asList(
            category1,
            category2
        );

        when(console.getValidInput(anyString(), anyInt())).thenReturn(2);
        Category result = settings.selectCategory(categories);

        assertEquals(category2, result);
    }

    @Test
    void testSelectDifficulty() {
        Difficulty difficulty1 = new Difficulty(1, new ArrayList<>());
        Difficulty difficulty2 = new Difficulty(2, new ArrayList<>());

        List<Difficulty> difficulties = Arrays.asList(
            difficulty1,
            difficulty2
        );

        when(console.getValidInput(anyString(), anyInt())).thenReturn(2);
        Difficulty result = settings.selectDifficulty(difficulties);

        assertEquals(difficulty2, result);
    }

    @Test
    void testSelectAttempts() {
        when(console.getValidInput(anyString(), anyInt())).thenReturn(0);
        int result = settings.selectAttempts(5, 2);

        assertEquals(7, result);
    }
}

package backend.academy;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Dictionary;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.Word;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DictionaryTest {
    Dictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary("test.json");
    }

    @Test
    void testInit() {
        List<Category> categories = dictionary.categories();
        assertNotNull(categories);
        assertEquals(1, categories.size());
        Category category = categories.getFirst();
        assertEquals("vegetables", category.name());

        List<Difficulty> difficulties = category.difficulties();
        assertNotNull(difficulties);
        assertEquals(1, difficulties.size());
        Difficulty difficulty = difficulties.getFirst();
        assertEquals(1, difficulty.level());

        List<Word> words = difficulty.words();
        assertNotNull(words);
        assertEquals(1, words.size());
        Word word = words.getFirst();
        assertEquals("onion", word.word());
        assertEquals("cry", word.hint());
    }

    @Test
    void testInitNonexistentFile() {
        assertThrows(IllegalArgumentException.class, () -> new Dictionary("nonexistent.json"));
    }
}

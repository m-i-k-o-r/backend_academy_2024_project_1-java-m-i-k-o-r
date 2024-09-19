package backend.academy.game;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Dictionary;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.Word;
import backend.academy.ui.Console;

public class Game {
    private final Console console = new Console();

    public void startGame(Dictionary dictionary) {
        console.println("--===| Настройка игры |===--");

        Settings settings = new Settings();
        Category category = settings.selectCategory(dictionary.categories());
        Difficulty difficulty = settings.selectDifficulty(category.difficulties());
        Word secretWord = settings.selectWord(difficulty.words());
        int maxAttempts = settings.selectAttempts(secretWord.word().length(), difficulty.level());
    }
}

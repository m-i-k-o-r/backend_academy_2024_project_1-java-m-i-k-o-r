package backend.academy.game;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Dictionary;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.Word;
import backend.academy.ui.Console;

public class HangmanGame implements Game {
    private final Console console = new Console();

    @Override
    public void play(Dictionary dictionary) {
        console.println("--===| Настройка игры |===--");

        Settings settings = new Settings();
        Category category = settings.selectCategory(dictionary.categories());
        Difficulty difficulty = settings.selectDifficulty(category.difficulties());
        Word secretWord = settings.selectWord(difficulty.words());
        int maxAttempts = settings.selectAttempts(secretWord.word().length(), difficulty.level());

        console.println("--===| Игра |===--");
        Session session = new Session(secretWord, maxAttempts);

        while (!session.isGameOver()) {
            session.printState();
            session.printCommands();

            String input = console.getStringInput("Введите букву или команду > ");

            if (input.startsWith("-")) {
                session.handleCommand(input);
            } else {
                session.guessLetter(input.charAt(0));
            }

            if (session.isWordGuessed()) {
                break;
            }
            console.println();
        }

        session.end();
    }
}

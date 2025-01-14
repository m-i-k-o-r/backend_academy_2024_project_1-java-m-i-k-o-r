package backend.academy.game;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.Word;
import backend.academy.ui.Console;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Settings {
    private static final int RANDOM_SELECTION = 0;
    private final Random random = new SecureRandom();
    private final Console console;

    public Settings() {
        this.console = new Console();
    }

    public Settings(Console console) {
        this.console = console;
    }

    public Category selectCategory(List<Category> categories) {
        console.println("-== Выбор Категории ==-");

        console.println("0. пропустить (выбор случайной категории)");
        for (int i = 0; i < categories.size(); i++) {
            console.println((i + 1) + ". " + categories.get(i).name());
        }

        int selectedCategoryIndex = console.getValidInput(
            "Выберете категорию > ",
            categories.size()
        );

        Category category = (selectedCategoryIndex == RANDOM_SELECTION)
            ? categories.get(random.nextInt(categories.size()))
            : categories.get(selectedCategoryIndex - 1);

        console.println("Выбрана категория: " + category.name());
        console.println();

        return category;
    }

    public Difficulty selectDifficulty(List<Difficulty> difficulties) {
        console.println("-== Выбор Сложности ==-");

        console.println("0. пропустить (выбор случайной сложности)");
        for (int i = 0; i < difficulties.size(); i++) {
            console.println((i + 1) + ". " + difficulties.get(i).toString());
        }

        int selectedDifficultyIndex = console.getValidInput(
            "Выберете сложность > ",
            difficulties.size()
        );

        Difficulty difficulty = (selectedDifficultyIndex == RANDOM_SELECTION)
            ? difficulties.get(random.nextInt(difficulties.size()))
            : difficulties.get(selectedDifficultyIndex - 1);

        console.println("Выбрана сложность: " + difficulty.toString());
        console.println();

        return difficulty;
    }

    public Word selectWord(List<Word> words) {
        return words.get(random.nextInt(words.size()));
    }

    public int selectAttempts(int lengthWord, int level) {
        console.println("-== Выбор количества допустимых ошибок ==-");
        console.println("0. По формуле (кол-во букв + сложность)");
        console.println("1. Ввести вручную");

        int action = console.getValidInput("Выберете действие > ", 2);

        int attempts = (action == RANDOM_SELECTION)
            ? (lengthWord + level)
            : console.getValidInput("Введите кол-во допустимых ошибок > ", Integer.MAX_VALUE);

        console.println("Вам будет доступно " + attempts + " ошибок");
        console.println();

        return attempts;
    }
}

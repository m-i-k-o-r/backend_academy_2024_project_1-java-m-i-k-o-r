package backend.academy.game;

import backend.academy.dictionary.Word;
import backend.academy.ui.Console;

public class Session {
    private final static char UNKNOWN_CHAR = '\u0000';

    private final Word secretWord;
    private final char[] guessedWord;

    private int attempts;
    private final int maxAttempts;

    private boolean isUsedHint;

    private final Console console;

    public Session(Word secretWord, int maxAttempts, Console console) {
        this.secretWord = secretWord;
        this.guessedWord = new char[secretWord.word().length()];
        this.attempts = 0;
        this.maxAttempts = maxAttempts;
        isUsedHint = false;
        this.console = console;
    }

    public Session(Word secretWord, int maxAttempts) {
        this(secretWord, maxAttempts, new Console());
    }

    public boolean isGameOver() {
        return attempts >= maxAttempts || isWordGuessed();
    }

    public boolean isWordGuessed() {
        for (char c : guessedWord) {
            if (c == UNKNOWN_CHAR) {
                return false;
            }
        }
        return true;
    }

    public void guessLetter(char letter) {
        boolean isCorrect = false;
        String wordLowerCase = secretWord.word().toLowerCase();
        char letterLowerCase = Character.toLowerCase(letter);

        for (int i = 0; i < secretWord.word().length(); i++) {
            if (wordLowerCase.charAt(i) == letterLowerCase) {
                guessedWord[i] = letterLowerCase;
                isCorrect = true;
            }
        }

        if (!isCorrect) {
            attempts++;
        }
    }

    public void handleCommand(String command) {
        switch (command) {
            case "-hint":
                if (isUsedHint) {
                    console.println(" ! Подсказка уже активирована");
                } else {
                    isUsedHint = true;
                    console.println("Использована подсказка!");
                }
                break;
            case "-all":
                String fullWordGuess = console.getStringInput("Введите полное слово > ");
                if (secretWord.word().equals(fullWordGuess)) {
                    for (int i = 0; i < secretWord.word().length(); i++) {
                        guessedWord[i] = secretWord.word().charAt(i);
                    }
                } else {
                    attempts = maxAttempts;
                }
                break;
            default:
                console.println(" ! Некорректный ввод команды");
        }
    }

    public void printState() {
        console.printHealth(maxAttempts - attempts);
        console.printGallows(attempts * 1.0 / maxAttempts);
        console.printHint(isUsedHint, secretWord.hint());
        console.printSecretWord(guessedWord);
    }

    public void printCommands() {
        console.println("Доступные команды:");
        if (!isUsedHint) {
            console.println("  -hint для получения подсказки");
        }
        console.println("  -all для введения полного слова (всё или ничего)");
    }

    public void sumUp() {
        console.println("--===| Конец |===--");
        console.printSecretWord(secretWord.word().toCharArray());
        if (isWordGuessed()) {
            console.printWin();
        } else {
            console.printLose();
        }
    }
}

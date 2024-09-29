package backend.academy;

import backend.academy.dictionary.Dictionary;
import backend.academy.game.Game;
import backend.academy.game.HangmanGame;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary("dictionary.json");

        Game game = new HangmanGame();
        game.play(dictionary);
    }
}

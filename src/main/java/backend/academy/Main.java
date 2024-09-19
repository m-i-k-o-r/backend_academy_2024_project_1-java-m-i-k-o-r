package backend.academy;

import backend.academy.dictionary.Dictionary;
import backend.academy.game.Game;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.init("dictionary.json");

        Game game = new Game();
        game.startGame(dictionary);
    }
}

package backend.academy;

import backend.academy.dictionary.Dictionary;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.init("dictionary.json");
    }
}

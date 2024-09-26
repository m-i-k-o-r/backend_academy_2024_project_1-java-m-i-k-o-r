package backend.academy.dictionary;

import java.util.List;

public record Difficulty(
    int level,
    List<Word> words
) {
    @Override
    public String toString() {
        return level + " уровень";
    }
}

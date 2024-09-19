package backend.academy.dictionary;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Difficulty {
    private int level;
    private List<Word> words;

    @Override
    public String toString() {
        return level + " уровень";
    }
}
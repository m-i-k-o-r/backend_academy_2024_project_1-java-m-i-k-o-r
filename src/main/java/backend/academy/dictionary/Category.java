package backend.academy.dictionary;

import java.util.List;

public record Category(
    String name,
    List<Difficulty> difficulties
) {

}

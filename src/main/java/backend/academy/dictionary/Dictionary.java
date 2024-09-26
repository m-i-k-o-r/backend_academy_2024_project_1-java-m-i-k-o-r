package backend.academy.dictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dictionary {
    private List<Category> categories;

    public void init(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader in = new BufferedReader(new FileReader(
            Resources.getResource(fileName).getFile(),
            StandardCharsets.UTF_8)
        )) {
            this.categories = mapper.readValue(in, Dictionary.class).categories();
        } catch (IOException e) {
            throw new IllegalArgumentException("File not may parsing: ", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Category category : this.categories()) {
            sb.append("Категория: " + category.name() + "\n");
            for (Difficulty difficulty : category.difficulties()) {
                sb.append("  └ Сложность: " + difficulty.level() + "\n");
                for (Word word : difficulty.words()) {
                    sb.append("    └ " + word.word() + " - " + word.hint() + "\n");
                }
            }
        }
        return sb.toString();
    }
}

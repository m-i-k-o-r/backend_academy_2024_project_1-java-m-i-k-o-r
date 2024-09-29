package backend.academy.dictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public record Dictionary(
    List<Category> categories
) {
    public Dictionary(String fileName) {
        this(initFromFile(fileName));
    }

    private static List<Category> initFromFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader in = new BufferedReader(new FileReader(
            Resources.getResource(fileName).getFile(),
            StandardCharsets.UTF_8)
        )) {
            return mapper.readValue(in, Dictionary.class).categories();
        } catch (IOException e) {
            throw new IllegalArgumentException("File not may parsing: ", e);
        }
    }
}

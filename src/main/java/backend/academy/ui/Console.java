package backend.academy.ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@SuppressWarnings("regexpsinglelinejava")
public class Console {
    private final Scanner scanner;

    public Console() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getValidInput(String prompt, int maxValue) {
        while (true) {
            print(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();

                if (input >= 0 && input <= maxValue) {
                    return input;
                } else {
                    println(" ! Некорректный ввод: число не в диапазоне от 0 до " + maxValue);
                }
            } else {
                println(" ! Некорректный ввод: ожидается число");
            }
            scanner.nextLine();
        }
    }

    public void println() {
        System.out.println();
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }
}

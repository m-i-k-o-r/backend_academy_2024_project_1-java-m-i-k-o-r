package backend.academy.ui;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Console {
    private static final char UNKNOWN_CHAR = '\u0000';

    private final Scanner scanner;
    private final PrintWriter writer;

    private final GallowsStages[] stages;

    public Console() {
        this(
            new Scanner(System.in, StandardCharsets.UTF_8),
            new OutputStreamWriter(System.out, StandardCharsets.UTF_8)
        );
    }

    public Console(Scanner scanner, OutputStreamWriter writer) {
        this.scanner = scanner;
        this.stages = GallowsStages.values();
        this.writer = new PrintWriter(writer, true);
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

    public String getStringInput(String prompt) {
        print(prompt);
        return scanner.next();
    }

    public void println() {
        writer.println();
    }

    public void println(String message) {
        writer.println(message);
    }

    public void print(String message) {
        writer.print(message);
        writer.flush();
    }

    public void printHealth(int healthNum) {
        println("Осталось жизней: " + "❤ ".repeat(Math.max(0, healthNum)));
    }

    public void printGallows(double percent) {
        double percentValid = percent;
        if (percent < 0) {
            percentValid = 0;
        } else if (percent > 1) {
            percentValid = 1;
        }
        int stage = (int) (percentValid * (stages.length - 1));
        println(stages[stage].stage());
    }

    public void printSecretWord(char[] word) {
        StringBuilder output = new StringBuilder();
        output.append("Загаданное слово: ");
        for (char c : word) {
            if (c == UNKNOWN_CHAR) {
                output.append('_');
            } else {
                output.append(c);
            }
            output.append(' ');
        }
        println(output.toString());
    }

    public void printHint(boolean isUsedHint, String hint) {
        if (isUsedHint) {
            println("Подсказка: " + hint);
        }
    }

    public void printWin() {
        println("""

            ██╗░░░██╗░░░░░██╗░░░░░░░██╗██╗███╗░░██╗
            ██║░░░██║░░░░░██║░░██╗░░██║██║████╗░██║
            ██║░░░██║░░░░░╚██╗████╗██╔╝██║██╔██╗██║
            ██║░░░██║░░░░░░████╔═████║░██║██║╚████║
            ╚██████╔╝░░░░░░╚██╔╝░╚██╔╝░██║██║░╚███║
            ░╚═════╝░░░░░░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝

            ---====| Поздравляем с победой |====---
            """);
    }

    public void printLose() {
        println(stages[stages.length - 1].stage());
    }

    public void close() {
        scanner.close();
        writer.close();
    }
}

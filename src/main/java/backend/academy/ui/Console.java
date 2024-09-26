package backend.academy.ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@SuppressWarnings("regexpsinglelinejava")
public class Console {
    private final static char UNKNOWN_CHAR = '\u0000';

    private final Scanner scanner;
    private final GallowsStages[] stages;

    public Console() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        this.stages = GallowsStages.values();
    }

    public Console(Scanner scanner) {
        this.scanner = scanner;
        this.stages = GallowsStages.values();
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
        System.out.println();
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
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
}

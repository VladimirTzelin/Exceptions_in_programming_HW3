package View;

import java.util.Scanner;

public class ConsoleView implements View {

    
    // Проблема с распознаванием кириллицы
    private Scanner in = new Scanner(System.in, "cp866");

    @Override
    public void printOutput(String message) {
        System.out.printf("%s", message);
    }

    @Override
    public String getInput(String message) {
        System.out.printf("%s", message);
        return in.nextLine();
    }

}
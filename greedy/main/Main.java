
package main;

import ui.AlgorithmGUI;
import ui.ConsoleUI;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Select interface:");
        System.out.println("1. Console");
        System.out.println("2. GUI");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            ConsoleUI consoleUI = new ConsoleUI();
            consoleUI.start();
        } else if (choice == 2) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    AlgorithmGUI gui = new AlgorithmGUI();
                    gui.setVisible(true);
                }
            });
        }
    }
}

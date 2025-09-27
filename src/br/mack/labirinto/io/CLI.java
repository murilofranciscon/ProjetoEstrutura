package br.mack.labirinto.io;

import java.util.Scanner;

public class CLI {
    private Scanner scanner = new Scanner(System.in);

    public String nextCommand() {
        System.out.print("Digite comando (W/A/S/D): ");
        return scanner.nextLine();
    }
}

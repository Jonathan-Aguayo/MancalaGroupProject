package model;

import java.util.Scanner;

public class ModelTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MancalaModel mancala = new MancalaModel();
        mancala.setStoneAmount(3);
        mancala.newGame();
        do {
            System.out.println(mancala);
            System.out.println("Turn: " + mancala.getTurnCount() + " - Redo: " + mancala.getRedoCount());
            System.out.print("P" + (mancala.isP1turn() ? "1" : "2") + " picks: ");
            String input = scanner.nextLine();
            if (input.compareToIgnoreCase("r") == 0) {
                mancala.redo();
            } else if (input.compareToIgnoreCase("e") == 0) {
                mancala.endTurn();
            } else {
                mancala.toMove(Integer.parseInt(input));
            }
            System.out.println();
        } while (!mancala.hasEmptyPits());
        scanner.close();

        System.out.println(mancala.getResult());
    }
}
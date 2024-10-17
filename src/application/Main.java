package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ChessMatch match = new ChessMatch();
        Scanner sc = new Scanner(System.in);

        while (true){
            UI.printBoard(match.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(sc);
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(sc);

            match.performChessMove(source,target);

        }
    }
}

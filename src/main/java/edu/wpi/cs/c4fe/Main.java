package edu.wpi.cs.c4fe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int BOARD_WIDTH = 7;
    public static final int BOARD_HEIGHT = 6;
    public static final int CONNECT_LENGTH = 4;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of arguments.");
        }

        String inputFile = args[0];
        String outputFile = args[1];

        List<GameState> states = Main.read(inputFile);
        for (GameState e : states) {
            System.out.println(e.toString() + "\n");
        }
    }

    protected static List<GameState> read(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String input;
            bufferedReader.readLine();

            List<GameState> states = new ArrayList<GameState>();

            while ((input = bufferedReader.readLine()) != null) {
                String[] positions = input.split(",");
                BoardCell[][] gameBoard = new BoardCell[BOARD_WIDTH][BOARD_HEIGHT]; // x firs increase, y decrease

                try {
                    int index = 0;
                    for (int i = 0; i < BOARD_WIDTH; i++) {
                        for (int j = BOARD_HEIGHT - 1; j >= 0; j--) {
                            gameBoard[i][j] = BoardCell.values()[Integer.parseInt(positions[index])];
                            index++;
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    break;
                }

                GameState state = new GameState(CONNECT_LENGTH, gameBoard, new boolean[]{false, false});
                states.add(state);
            }

            return states;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package edu.wpi.cs.c4fe;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the main class for running the connect 4 feature evaluator.
 *
 * @author Aditya Nivarthi
 */
public class Main {

    public static final int BOARD_WIDTH = 7;
    public static final int BOARD_HEIGHT = 6;
    public static final int CONNECT_LENGTH = 4;

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of arguments.");
        }

        String inputFile = args[0];
        String outputFile = args[1];

        List<GameState> states = read(inputFile);

        BoardFeature[] featureTesters = new BoardFeature[] {
                new BottomLeftFeature(),
                new CenterMajorityFeature(),
                new LineBoardFeatureCounter()
        };
        Map<String, Number[]> features = new HashMap<>();
        for (int i = 0; i < states.size(); i++) {
            int row = i;
            for (BoardFeature tester : featureTesters) {
                Map<String, Number> stateFeatures = tester.apply(states.get(row));
                stateFeatures.forEach((name, value) -> {
                    features.computeIfAbsent(name, s -> new Number[states.size()])[row] = value;
                });
            }
        }
        System.out.println(features);

        write(outputFile, states.size(), features);
    }

    /**
     * Returns the list of {@link GameState}s from the specified input file.
     *
     * @param filename The name of the input file to read.
     * @return a List&lt;GameState&gt;
     */
    protected static List<GameState> read(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String input;

            // Chew header line in file
            bufferedReader.readLine();

            List<GameState> states = new ArrayList<>();

            // Each line of input is a new GameState
            while ((input = bufferedReader.readLine()) != null) {
                String[] positions = input.split(",");
                BoardCell[][] gameBoard = new BoardCell[BOARD_WIDTH][BOARD_HEIGHT]; // x firs increase, y decrease

                // Set the board values from the positions in the input
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

                // Create a new GameState from the board
                GameState state = new GameState(CONNECT_LENGTH, gameBoard, new boolean[] { false, false });

                states.add(state);
            }

            return states;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected static void write(String filename, int entries, Map<String, Number[]> features) {
        try {
            PrintWriter w = new PrintWriter(new FileOutputStream(filename));
            w.println(features.keySet().stream().sorted().collect(Collectors.joining(",")));
            for (int i = 0; i < entries; i++) {
                int row = i;
                w.println(features.entrySet()
                        .stream()
                        .sorted(Comparator.comparing(Map.Entry::getKey))
                        .map(Map.Entry::getValue)
                        .map(values -> values[row])
                        .map(v -> v == null ? "0" : v.toString())
                        .collect(Collectors.joining(",")));
            }
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

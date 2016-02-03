package edu.wpi.cs.c4fe;

/**
 * This enumerated type represents the states that can be in any position of the Connect-N board.
 *
 * @author Aditya Nivarthi
 */
public enum BoardCell {
    NONE("."),
    MIN("-"),
    MAX("+");

    private String symbol;

    /**
     * Creates a {@link BoardCell} instance with the specified symbol.
     *
     * @param symbol The symbol to represent the enumerated type in string form.
     */
    BoardCell(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol for this enumerated type.
     *
     * @return a String
     */
    public String getSymbol() {
        return symbol;
    }
}

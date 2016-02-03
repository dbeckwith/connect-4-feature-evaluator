package edu.wpi.cs.c4fe;

/**
 * This enumerated type represents the players that can be on the game board at any time in the game.
 *
 * @author Aditya Nivarthi
 */
public enum Player {
    MIN, MAX;

    /**
     * Returns this Player in the form of a {@link BoardCell}.
     *
     * @return a {@link BoardCell}
     */
    public BoardCell getAsBoardCell() {
        return BoardCell.valueOf(this.name());
    }

    /**
     * Returns the opponent of this player.
     *
     * @return a {@link Player}
     */
    public Player getOpponent() {
        return (this == Player.MAX) ? Player.MIN : Player.MAX;
    }
}

package edu.wpi.cs.c4fe;

/**
 * This class represents a board position within a {@link BoardFeature}.
 *
 * @author Daniel Beckwith
 */
public class BoardPos {

    private final int x;
    private final int y;
    private final BoardCell cell;

    /**
     * Creates a BoardPos instance with the specified position and {@link BoardCell}.
     *
     * @param x    The horizontal location on the board.
     * @param y    The vertical location on the board.
     * @param cell The cell contents on the board.
     */
    public BoardPos(int x, int y, BoardCell cell) {
        this.x = x;
        this.y = y;
        this.cell = cell;
    }

    /**
     * Returns the horizontal coordiante of the position.
     *
     * @return an integer
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the vertical coordiante of the position.
     *
     * @return an integer
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the cell contents of the position.
     *
     * @return a {@link BoardCell}
     */
    public BoardCell getCell() {
        return cell;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "BoardPos{" +
                "x=" + x +
                ", y=" + y +
                ", cell=" + cell +
                '}';
    }

    /**
     * Returns whether this object is "equal" to the specified object.
     *
     * @param o The object to compare to this object.
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardPos)) return false;

        BoardPos boardPos = (BoardPos) o;

        if (x != boardPos.x) return false;
        if (y != boardPos.y) return false;
        return cell == boardPos.cell;

    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return an integer
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (cell != null ? cell.hashCode() : 0);
        return result;
    }
}

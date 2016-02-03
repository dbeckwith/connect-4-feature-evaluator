package edu.wpi.cs.c4fe;


import java.util.Arrays;

/**
 * This class represents the board feature that can be analyzed for the heuristic function.
 *
 * @author Daniel Beckwith
 */
public class LineBoardFeature {

    public static final int[] VALID_DIRS = {1, -1, 1, 0, 1, 1, 0, 1};

    private final BoardPos[] positions;
    private final int length;
    private final int dx;
    private final int dy;

    /**
     * Creates a BoardFeature instance with the specified positions.
     *
     * @param positions The list of positions for this board feature.
     */
    public LineBoardFeature(BoardPos... positions) {
        this.positions = positions;

        int length = 0;
        for (BoardPos position : positions) {
            if (position.getCell() != BoardCell.NONE) length++;
        }
        this.length = length;

        int dx = 0, dy = 0;
        if (positions.length > 1) {
            dx = positions[1].getX() - positions[0].getX();
            dy = positions[1].getY() - positions[0].getY();

            if (dx != 0) dx /= dx < 0 ? -dx : dx;
            if (dy != 0) dy /= dy < 0 ? -dy : dy;

            if (dx < 0 || (dx == 0 && dy < 0)) {
                dx = -dx;
                dy = -dy;
            }
        }

        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the list of board positions for this feature.
     *
     * @return an array of {@link BoardPos}
     */
    public BoardPos[] getPositions() {
        return positions;
    }

    /**
     * Returns the horizontal change in the board positions across the entire board feature.
     *
     * @return an integer
     */
    public int getDx() {
        return dx;
    }

    /**
     * Returns the vertical change in the board positions across the entire board feature.
     *
     * @return an integer
     */
    public int getDy() {
        return dy;
    }

    /**
     * Returns the number of pieces involved in this board feature.
     *
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns a string representation of the board feature.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "BoardFeature{" +
                "positions=" + Arrays.toString(positions) +
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
        if (!(o instanceof LineBoardFeature)) return false;

        LineBoardFeature that = (LineBoardFeature) o;

        if (length != that.length) return false;
        if (dx != that.dx) return false;
        if (dy != that.dy) return false;
        return Arrays.deepEquals(positions, that.positions);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return an integer
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(positions);
        result = 31 * result + length;
        result = 31 * result + dx;
        result = 31 * result + dy;
        return result;
    }
}

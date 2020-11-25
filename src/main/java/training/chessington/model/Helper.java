package training.chessington.model;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;
import training.chessington.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static List<Move> addOrthogonalMoves(Coordinates from, Board board, Piece piece){
        List<Move> moves = new ArrayList<>();

        Coordinates to;
        int distance;
        Coordinates min;

        for (int i = 0; i < 8; i++){

            distance = i - from.getRow();
            to = from.plus(distance,0);
            if (distance != 0){
                min = (from.getRow() < to.getRow()) ? from : to;
                if (board.checkEmptyBetween(min, Math.abs(distance), false)
                        && board.checkFriendlyFire(to, piece.getColour()))
                    moves.add(new Move(from, to));
            }

            distance = i - from.getCol();
            to = from.plus(0, distance);
            if (distance != 0){
                min = (from.getCol() < to.getCol()) ? from : to;
                if (board.checkEmptyBetween(min, Math.abs(distance), true)
                        && board.checkFriendlyFire(to, piece.getColour()))
                    moves.add(new Move(from, to));
            }
        }

        return moves;
    }

    public static List<Move> addDiagonalMoves(Coordinates from, Board board, Piece piece){
        List<Move> moves = new ArrayList<>();

        Coordinates to;
        int distance;
        Coordinates min;

        for (int i = 0; i < 8; i++) {

            distance = i - from.getRow();
            if (distance != 0) {
                to = from.plus(distance, distance);
                min = (from.getRow() < to.getRow()) ? from : to;
                if (to.withinBounds() && board.checkEmptyBetweenDiagonal(min, Math.abs(distance), true)
                        && board.checkFriendlyFire(to, piece.getColour()))
                    moves.add(new Move(from, to));

                to = from.plus(distance, -distance);
                min = (from.getRow() < to.getRow()) ? from : to;
                if (to.withinBounds() && board.checkEmptyBetweenDiagonal(min, Math.abs(distance), false)
                        && board.checkFriendlyFire(to, piece.getColour()))
                    moves.add(new Move(from, to));
            }
        }

        return moves;
    }
}

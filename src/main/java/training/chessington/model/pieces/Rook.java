package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        Coordinates to;
        int distance;
        Coordinates min;

        for (int i = 0; i < 8; i++){

            distance = i - from.getRow();
            to = from.plus(distance,0);
            if (distance != 0){
                min = (from.getRow() < to.getRow()) ? from : to;
                if (board.checkEmptyBetween(min, Math.abs(distance), false))
                    legalMoves.add(new Move(from, to));
            }

            distance = i - from.getCol();
            to = from.plus(0, distance);
            if (distance != 0){
                min = (from.getCol() < to.getCol()) ? from : to;
                if (board.checkEmptyBetween(min, Math.abs(distance), true))
                    legalMoves.add(new Move(from, to));
            }
        }

        return legalMoves;
    }
}

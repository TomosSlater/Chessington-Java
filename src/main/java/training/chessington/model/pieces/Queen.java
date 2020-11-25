package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> legalMoves = Helper.addDiagonalMoves(from, board, this);
        for(Move move: Helper.addOrthogonalMoves(from, board, this)) legalMoves.add(move);

        return legalMoves;
    }
}

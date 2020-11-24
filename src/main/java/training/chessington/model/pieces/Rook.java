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

        for (int i = 0; i < 8; i++){
            if (i != from.getRow()){
                to = from.plus(i - from.getRow(),0);
                legalMoves.add(new Move(from, to));
            }

            if (i != from.getCol()){
                to = from.plus(0,i - from.getCol());
                legalMoves.add(new Move(from, to));
            }
        }

        return legalMoves;
    }
}

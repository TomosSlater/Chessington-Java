package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        ArrayList<Move> legalMoves = new ArrayList<>();
        if(this.getColour() == PlayerColour.WHITE){
            legalMoves.add(new Move(from, new Coordinates(from.getRow() - 1, from.getCol())));
        } else{
            legalMoves.add(new Move(from, new Coordinates(from.getRow() + 1, from.getCol())));
        }
        return legalMoves;
    }
}

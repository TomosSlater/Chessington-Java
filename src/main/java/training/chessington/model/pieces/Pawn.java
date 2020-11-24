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
        Coordinates to;
        if(this.getColour() == PlayerColour.WHITE){
            if (from.getRow() != 0){
                to = from.plus(-1, 0);
                if (board.checkEmpty(to)) legalMoves.add(new Move(from, to));

                to = from.plus(-2, 0);
                if (isAtStartingPosition(from) && board.checkEmpty(to) && board.checkEmpty(from.plus(-1, 0)))
                    legalMoves.add(new Move(from, to));

                to = from.plus(-1, -1);
                if (from.getCol() != 0 && !board.checkEmpty(to) && board.checkColour(to) == PlayerColour.BLACK)
                    legalMoves.add(new Move(from, to));

                to = from.plus(-1, 1);
                if (from.getCol() != 7 && !board.checkEmpty(to) && board.checkColour(to) == PlayerColour.BLACK)
                    legalMoves.add(new Move(from, to));
            }
        } else{
            if (from.getRow() != 7){
                to = from.plus(1, 0);
                if (board.checkEmpty(to)) legalMoves.add(new Move(from, to));

                to = from.plus(2, 0);
                if (isAtStartingPosition(from) && board.checkEmpty(to) && board.checkEmpty(from.plus(1, 0)))
                    legalMoves.add(new Move(from, to));

                to = from.plus(1, -1);
                if (from.getCol() != 0 && !board.checkEmpty(to) && board.checkColour(to) == PlayerColour.WHITE)
                    legalMoves.add(new Move(from, to));

                to = from.plus(1, 1);
                if (from.getCol() != 7 && !board.checkEmpty(to) && board.checkColour(to) == PlayerColour.WHITE)
                    legalMoves.add(new Move(from, to));
            }
        }
        return legalMoves;
    }

    private boolean isAtStartingPosition(Coordinates from){
        if (this.getColour() == PlayerColour.WHITE){
            return (from.getRow() == 6) ? true : false;
        } else {
            return (from.getRow() == 1) ? true : false;
        }
    }
}

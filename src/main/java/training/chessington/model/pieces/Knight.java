package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> legalMoves = new ArrayList<>();
        Coordinates to;
        Coordinates[] diagonals = new Coordinates[]{
          new Coordinates(1, 1),
          new Coordinates(1, -1),
          new Coordinates(-1, 1),
          new Coordinates(-1, -1)
        };

        for(Coordinates diagonal: diagonals){
            to = from.plus(diagonal.getRow() * 2, diagonal.getCol());
            if (to.withinBounds() && board.checkFriendlyFire(to, this.colour))
                legalMoves.add(new Move(from, to));

            to = from.plus(diagonal.getRow(), diagonal.getCol() * 2);
            if (to.withinBounds() && board.checkFriendlyFire(to, this.colour))
                legalMoves.add(new Move(from, to));
        }

        return legalMoves;
    }
}

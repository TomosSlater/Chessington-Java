package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {

    @Test
    public void kingCanMoveOneSpaceInAnyDirection() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
    }

    @Test
    public void kingCantMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7, 7);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, 1)));
    }

    @Test
    public void kingCanTakeEnemyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);

        Piece king2 = new King(PlayerColour.BLACK);
        Coordinates coords2 = new Coordinates(3, 4);
        board.placePiece(coords2, king2);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);
        List<Move> moves2 = king2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
        assertThat(moves2).contains(new Move(coords2, coords2.plus(-1, 0)));
    }

    @Test
    public void kingCantTakeFriendlyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);

        Piece king2 = new King(PlayerColour.WHITE);
        Coordinates coords2 = new Coordinates(4, 5);
        board.placePiece(coords2, king2);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);
        List<Move> moves2 = king2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, 1)));
        assertThat(moves2).doesNotContain(new Move(coords2, coords2.plus(0, -1)));
    }
}

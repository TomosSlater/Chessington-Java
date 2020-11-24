package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {

    @Test
    public void rookCanMoveUpwards() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7, 0);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-3, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-4, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-5, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-6, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-7, 0)));
    }

    @Test
    public void rookCanMoveDownwards() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(3, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(4, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(5, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(6, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(7, 0)));
    }

    @Test
    public void rookCanMoveRight() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 3)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 4)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 5)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 6)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 7)));
    }

    @Test
    public void rookCanMoveLeft() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7, 7);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -3)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -4)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -5)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -6)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -7)));
    }

    @Test
    public void rookCantMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7, 7);
        board.placePiece(coords, rook);

        Piece rook2 = new Rook(PlayerColour.WHITE);
        Coordinates coords2 = new Coordinates(0, 0);
        board.placePiece(coords2, rook2);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);
        List<Move> moves2 = rook2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, 1)));
        assertThat(moves2).doesNotContain(new Move(coords, coords.plus(0, -1)));
        assertThat(moves2).doesNotContain(new Move(coords, coords.plus(-1, 0)));
    }

    @Test
    public void rookCantMoveThroughPieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, rook);

        Piece rook2 = new Rook(PlayerColour.WHITE);
        Coordinates coords2 = new Coordinates(4, 5);
        board.placePiece(coords2, rook2);

        Piece rook3 = new Rook(PlayerColour.WHITE);
        Coordinates coords3 = new Coordinates(4, 3);
        board.placePiece(coords3, rook3);

        Piece rook4 = new Rook(PlayerColour.WHITE);
        Coordinates coords4 = new Coordinates(5, 4);
        board.placePiece(coords4, rook4);

        Piece rook5 = new Rook(PlayerColour.WHITE);
        Coordinates coords5 = new Coordinates(3, 4);
        board.placePiece(coords5, rook5);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2, 0)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, 2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2, 0)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -2)));

    }
}

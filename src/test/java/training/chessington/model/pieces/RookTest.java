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
    public void RookCanMoveUpwards() {
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
    public void RookCanMoveDownwards() {
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
    public void RookCanMoveRight() {
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
    public void RookCanMoveLeft() {
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
}

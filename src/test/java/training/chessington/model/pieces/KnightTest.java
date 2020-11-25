package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KnightTest {

    @Test
    public void knightCanMoveInLShape() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
    }

    @Test
    public void knightCantMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);


        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, 2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, -2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2, -1)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(1, -2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2, -1)));

    }

    @Test
    public void knightCanTakeEnemyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        Piece knight2 = new Knight(PlayerColour.BLACK);
        Coordinates coords2 = new Coordinates(6, 5);
        board.placePiece(coords2, knight2);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);
        List<Move> moves2 = knight2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
        assertThat(moves2).contains(new Move(coords2, coords2.plus(-2, -1)));
    }

    @Test
    public void kingCantTakeFriendlyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        Piece knight2 = new Knight(PlayerColour.WHITE);
        Coordinates coords2 = new Coordinates(6, 5);
        board.placePiece(coords2, knight2);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);
        List<Move> moves2 = knight2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2, 1)));
        assertThat(moves2).doesNotContain(new Move(coords2, coords2.plus(-2, -1)));
    }
}

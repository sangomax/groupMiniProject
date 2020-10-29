package chess.model;

public class Position {

    private Piece piece;

    public Position(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        if(getPiece() == null) {
            return "•\t";
        } else {
            return piece.toString();
        }

    }
}

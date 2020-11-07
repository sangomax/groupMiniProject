package chess.model;

public class Position {

    private Piece piece;

    private String code;

    public Position(Piece piece, String code) {
        this.piece = piece;
        this.code = code;
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

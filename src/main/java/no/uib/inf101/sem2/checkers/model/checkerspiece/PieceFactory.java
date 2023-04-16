package no.uib.inf101.sem2.checkers.model.checkerspiece;

public class PieceFactory implements IPieceFactory{

    @Override
    public AbstractPiece getNext(char pieceType, char team) {
        if(pieceType == 'P'){
            return new Pawn(team);
        }
        if(pieceType == 'K'){
            return new King(team);
        }
        if(pieceType == '-'){
            return new EmptyPiece(team);
        }
        throw new IllegalArgumentException("Piece does not exist");
    }
    
}

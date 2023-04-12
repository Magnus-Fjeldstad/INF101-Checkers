package no.uib.inf101.sem2.model.checkerspiece;

public interface IPieceFactory {

    /**
     * 
     * @param pieceType Char som representerer prikketype
     * @param team true om hvit lag false om svart
     * @return En brikke
     */
    public AbstractPiece getNext(char pieceType, char team);
}

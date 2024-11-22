package bank.exceptions;

/**
 * Exception, falls ein Attribut für die Transaktion fehlerhaft gesetzt wurde.
 */
public class TransactionAttributeException extends Exception{
    public TransactionAttributeException(String meldung) {
        super(meldung);
    }
}

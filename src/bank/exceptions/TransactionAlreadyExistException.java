package bank.exceptions;

/**
 * Exception, falls diese Transaktion bereits existiert.
 */
public class TransactionAlreadyExistException extends Exception {
    public TransactionAlreadyExistException(String meldung) {
        super(meldung);
    }
}
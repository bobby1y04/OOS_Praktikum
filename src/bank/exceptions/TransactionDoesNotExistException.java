package bank.exceptions;

public class TransactionDoesNotExistException extends Exception {
    public TransactionDoesNotExistException(String meldung) {
        super(meldung);
    }
}

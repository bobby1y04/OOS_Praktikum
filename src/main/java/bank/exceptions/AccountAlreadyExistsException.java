package bank.exceptions;

/**
 * Exception, falls das Konto bereits existiert.
 */
public class AccountAlreadyExistsException extends Exception {
public AccountAlreadyExistsException(String meldung) {
    super(meldung);
    }
}

package bank.exceptions;

/**
 * Exception, falls Konto noch nicht existiert.
 */
public class AccountDoesNotExistException extends Exception{
    public AccountDoesNotExistException(String meldung){
        super(meldung);
    }
}
package bank; // bank.CalculateBill.java


/**
 * The interface CalculateBill contains a method calculate which computes the
 * correct amount of money for a transaction depending on whether it's a
 * Payment or a Transfer.
 * @author Bobby Ly
 */
public interface CalculateBill {

    /**
     * Calculates the total amount of money for the transaction.
     * @return The calculated amount, which can depend on the
     *         specific implementation in either a Payment or
     *         a Transfer.
     */
    public abstract double calculate();
}

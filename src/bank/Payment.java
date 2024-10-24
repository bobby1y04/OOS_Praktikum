package bank;// bank.Payment.java

/**
 * The Payment class represents a money-payment with incoming and outgoing interest.
 * It extends the Transaction class and adds incoming and outgoing interest for deposits and withdrawals.
 * @author Bobby Ly
 */
public class Payment extends Transaction {


    /**
     * The incoming interest that has to be added to the amount of
     * money for the transaction when depositing money.
     */
    private double incomingInterest;
    /**
     * The outgoing interest that has to be added to the amount of
     * money for the transaction when withdrawing money.
     */
    private double outgoingInterest;


    /**
     * Constructor for the Payment class with specific parameters.
     * @param date The date of the payment.
     * @param amount The amount of money that was paid.
     * @param description The description of the payment.
     * @param incomingInterest The incoming interest for the payment.
     * @param outgoingInterest The outgoing interest for the payment.
     */
    public Payment(String date, double amount, String description, double incomingInterest, double outgoingInterest) {
        super(date, amount, description);
        this.setIncomingInterest(incomingInterest); // Initialisierung + Überprüfung des Wertes
        this.setOutgoingInterest(outgoingInterest); // Initialisierung + Überprüfung des Wertes
    }

    /**
     * Copy constructor for creating a copy of another Payment object.
     * @param other The other Payment object to be copied.
     */
    public Payment(Payment other) {
        super(other);
        this.setIncomingInterest(other.getIncomingInterest());
        this.setOutgoingInterest(other.getOutgoingInterest());
    }


    /**
     * Gets the incoming interest.
     * @return The incoming interest.
     */
    public double getIncomingInterest() {
        return this.incomingInterest;
    }

    /**
     * Sets the incoming interest.
     * @param val The new incoming interest (0-1)
     */
    public void setIncomingInterest(double val) {
        if (val >= 0 && val <= 1) this.incomingInterest = val;
        else System.out.println("Error: Value for incoming interest must be between 0 and 1.");
    }

    /**
     * Gets the outgoing interest.
     * @return The new outgoing interest.
     */
    public double getOutgoingInterest() {
        return this.outgoingInterest;
    }

    /**
     * Sets the outgoing interest.
     * @param val The new outgoing interest (0-1)
     */
    public void setOutgoingInterest(double val) {
        if (val >= 0 && val <= 1) this.outgoingInterest = val;
        else System.out.println("Error: Value for outgoing interest must be between 0 and 1.");
    }

    /**
     * Overrides the printObject() methods to print all attributes for
     * a payment to the console.
     */
    @Override
    public String toString() {
        return (super.toString()
                + "Incoming interest: " + this.getIncomingInterest() + "\n"
                + "Outgoing interest: " + this.getOutgoingInterest() + "\n");
    }

    /**
     * Computes the correct amount of money for this payment.
     *
     *
     * @return The new computed amount of money for the payment.
     *         If the original amount of money was positive (deposit), the incoming interest will be subtracted.
     *         If the original amount of money was negative (withdrawal), the outgoing interest will be added.
     */
    @Override
    public double calculate() {
        return (super.getAmount() >= 0 ? (super.getAmount()-(super.getAmount()*this.getIncomingInterest())) : (super.getAmount()+(super.getAmount()*this.getOutgoingInterest())));
    }

    /**
     * Gets the amount of money for this Payment.
     *
     * @return The amount of money for this Payment.
     */
    @Override
    public double getAmount() {
        return this.calculate();
    }


    /**
     * Checks whether the object in the parameter list is equal to this Payment object.
     * This method compares whether the given object is also an instance of the
     * Payment class and whether the values of amount, date, description, incomingInterest,
     * and outgoingInterest match this Payment object.
     *
     * @param obj The object to be compared with this Payment object.
     * @return true if the given object is equal to this Payment object,
     *         false if it is null, represents a different class, or
     *         if the relevant field (amount, date, description, incomingInterestk, outgoingInterest)
     *         do not match.
     */
    @Override
    public boolean equals(Object obj) {
        return (
                super.equals(obj)
                        && this.getIncomingInterest() == ((Payment)obj).getIncomingInterest()
                        && this.getOutgoingInterest() == ((Payment)obj).getOutgoingInterest()
        );
    }


}

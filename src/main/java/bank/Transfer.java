package bank;// bank.Transfer.java

import bank.exceptions.TransactionAttributeException;

/**
 * The Transfer class represents a money transfer between two people.
 * It extends the Transaction class and adds a sender and a recipient.
 *
 * @author Bobby Ly
 */
public class Transfer extends Transaction {

    /**
     * The person who sends the money.
     */
    private String sender;
    /**
     * The person who receives the money.
     */
    private String recipient;


    /**
     * Konstruktor, der ein neues Objekt mit den Attributen date, amound und description erzeugt
     * @param date Zeitpunkt einer Überweisung
     * @param amount Geldmenge einer Überweisung
     * @param description Beschreibung der Überweisung
     * @throws TransactionAttributeException
     */
    public Transfer(String date, double amount, String description) throws TransactionAttributeException {
        super(date, amount, description);
    }

    /**
     * Constructor with specific parameters
     *
     * @param date        The date of the transfer.
     * @param amount      The amount of money for the transfer (must be positive)
     * @param description The description of the transfer.
     * @param sender      The person who sends the money.
     * @param recipient   The person who receives the money.
     */
    public Transfer(String date, double amount, String description, String sender, String recipient) throws TransactionAttributeException {
        super(date, amount, description);
        this.setSender(sender);
        this.setRecipient(recipient);
    }

    /**
     * Copy constructor for creating a copy of another Transfer object.
     *
     * @param other The other Transfer object to be copied.
     */
    public Transfer(Transfer other) throws TransactionAttributeException {
        super(other);
        this.setSender(other.getSender());
        this.setRecipient(other.getRecipient());
    }


    /**
     * Gets the sender of the transfer.
     *
     * @return The sender of the Transfer.
     */
    public String getSender() {
        return this.sender;
    }

    /**
     * Sets the sender of the transfer.
     *
     * @param val The new sender of the transfer.
     */
    public void setSender(String val) {
        this.sender = val;
    }

    /**
     * Gets the recipient of the transfer.
     *
     * @return The recipient of the transfer.
     */
    public String getRecipient() {
        return this.recipient;
    }

    /**
     * Sets the recipient of the transfer.
     *
     * @param val The new recipient of the transfer.
     */
    public void setRecipient(String val) {
        this.recipient = val;
    }

    /**
     * Overrides the setAmount method to ensure the amount is positive.
     *
     * @param val The new amount of money for the transfer (must be positive)
     */
    @Override
    public void setAmount(double val) throws TransactionAttributeException {
        if (val < 0) throw new TransactionAttributeException("Der zu sendene Betrag muss positiv sein!");
        else super.setAmount(val);
    }


    /**
     * Overrides the printObject() method to print all attributes for
     * a transfer to the console.
     */
    @Override
    public String toString() {
        return (super.toString()
                + "Sender: " + this.getSender() + "\n"
                + "Recipient: " + this.getRecipient() + "\n");
    }


    /**
     * Checks whether the object in the parameter list is equal to this Transfer object.
     * This method compares whether the given object is also an instance of the
     * Payment class and whether the values of amount, date, description, sender,
     * and recipient match this Payment object.
     *
     * @param obj The object to be compared with this Transfer object.
     * @return true if the given object is equal to this Transfer object,
     * false if it is null, represents a different class, or
     * if the relevant field (amount, date, description, sender, recipient)
     * do not match.
     */
    @Override
    public boolean equals(Object obj) {
        return (
                super.equals(obj)
                        && this.getSender().equals(((Transfer) obj).getSender())
                        && this.getRecipient().equals(((Transfer) obj).getRecipient())
        );
    }

    /**
     * Computes the amount of money for the transaction.
     * @return The computed amount of money for the transaction.
     */
    @Override
    public double calculate() {
        return this.getAmount();
    }


}



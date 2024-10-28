package bank;// bank.Transaction.java

/**
 * The Transaction class represents a money transaction with
 * a date, a certain amount of money and a description.
 *
 * @author Bobby Ly
 */
abstract class Transaction implements CalculateBill {

    private String date;    //  date of transaction in the form: DD.MM.YYYY
    private double amount;   //  amount of the transaction (when transferring money, it's always >= 0)
    private String description; // Description of the transaction

    /**
     * Constructor for creating a transaction with specific parameters.
     *
     * @param date        The date of the transaction.
     * @param amount      The amount of money for the transaction.
     * @param description The description of the transaction.
     */
    public Transaction(String date, double amount, String description) {
        this.setDate(date);
        this.setAmount(amount);
        this.setDescription(description);
    }

    /**
     * Copy constructor for creating a copy of another Transaction object.
     *
     * @param other The other Transaction object to be copied.
     */
    public Transaction(Transaction other) {
        this.setDate(other.getDate());
        this.setAmount(other.getAmount());
        this.setDescription(other.getDescription());
    }

    /**
     * Gets the date of the transaction.
     *
     * @return The date of the transaction.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param val The new date of the transaction.
     */
    public void setDate(String val) {
        date = val;
    }

    /**
     * Gets the amount of money of the transaction.
     *
     * @return The amount of money of the transaction.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount of money of the transaction.
     *
     * @param val The new amount of money of the transaction.
     */
    public void setAmount(double val) {
        amount = val;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return The description of the transaction.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param val The description of the transaction.
     */
    public void setDescription(String val) {
        description = val;
    }

    /**
     * Prints the attributes of the Transaction object to the console.
     *
     * @return string that contains all attributes of the Transaction object
     */
    public String toString() {
        return (
                "Date: " + this.getDate() + "\n"
                        + "Amount: " + this.calculate() + "\n"
                        + "Description: " + this.getDescription() + "\n"
        );
    }


    /**
     * Checks whether the object in the parameter list is equal to this Transaction object.
     * This method compares whether the given object is also an instance of the
     * Transaction class and whether the values of amount, date and description
     * match this Payment object.
     *
     * @param obj The object to be compared with this Transaction object.
     * @return true if the given object is equal to this Transaction object,
     * false if it is null, represents a different class, or
     * if the relevant field (amount, date, description)
     * do not match.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Identität überprüfen
        }

        if (obj == null || getClass() != obj.getClass()) { // Prüft, ob das Objekt null ist oder auch eine Instanz von der Klasse Transaction ist
            return false; // Typ und Null überprüfen
        }


        Transaction other = (Transaction) obj;
        return this.getAmount() == other.getAmount()
                && this.getDate().equals(other.getDate())
                && this.getDescription().equals(other.getDescription());
    }
}




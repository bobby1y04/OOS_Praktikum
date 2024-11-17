package bank;

import bank.exceptions.*;

import java.util.*;

/**
 * Die Klasse PrivateBank repräsentiert eine private Bank, wo man Konten anlegen kann.
 */
public class PrivateBank implements Bank{

    protected String name;    // Dieses Attribut soll den Namen der privaten Bank repräsentieren.

    /*
     * Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in
     * Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen. Dieses Attribut soll identisch zu dem
     * gleichnamigen Attribut der Klasse Payment sein.
     */
    protected double incomingInterest;

    /*
     * Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in
     * Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen. Dieses Attribut soll identisch zu
     * dem gleichnamigen Attribut der Klasse Payment sein.
     */
    protected double outgoingInterest;

    /*
     * Dieses Attribut soll Konten mit
     * Transaktionen verknüpfen, sodass jedem gespeicherten Konto 0 bis n Transaktionen zugeordnet
     * werden können. Der Schlüssel steht für den Namen des Kontos.
     */
    protected Map<String, List<Transaction>> accountsToTransactions = new HashMap<>(); // Hashmap implementiert das Interface Map

    // Getter und Setter
    /**
     * Gibt den Namen der privaten Bank zurück
     * @return Name der privaten Bank
     */
    public String getName() { return this.name;}

    /**
     * Setzt den Namen der privaten Bank
     * @param val Der neue Name der privaten Bank
     */
    public void setName(String val) { this.name = val;}

    /**
     * Gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1), die bei einer Einzahlung anfallen, zurück.
     * @return Zinsen, die bei einer Einzahlung anfallen.
     */
    public double getIncomingInterest() { return this.incomingInterest;}

    /**
     * Setzt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1), die bei einer Einzahlung anfallen.
     * @param val Zinsen, die bei einer Einzahlung anfallen.
     */
    public void setIncomingInterest(double val) {
        if (val >= 0 && val <= 1) {
            this.incomingInterest = val;
        }
    }

    /**
     * Gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1), die bei einer Auszahlung anfallen, zurück.
     * @return Zinsen, die bei einer Auszahlung anfallen.
     */
    public double getOutgoingInterest() { return this.outgoingInterest;}

    /**
     * Setzt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1), die bei einer Auszahlung anfallen.
     * @param val Zinsen, die bei einer Auszahlung anfallen.
     */
    public void setOutgoingInterest(double val) {
        if (val >= 0 && val <= 1) {
            this.outgoingInterest = val;
        }
    }


    // Konstruktor

    /**
     * Konstruktor mit den Parametern name, incomingInterest und outgoingInterest
     * @param name Name der privaten Bank
     * @param incomingInterest Zinsen, die bei einer Einzahlung anfallen
     * @param outgoingInterest Zinsen, die bei einer Auszahlung anfallen
     */
    public PrivateBank(String name, double incomingInterest, double outgoingInterest) {
        this.setName(name);
        this.setIncomingInterest(incomingInterest);
        this.setOutgoingInterest(outgoingInterest);
    }

    /**
     * Copy-Konstruktor für die Klasse PrivateBank
     * @param other Objekt vom Typ PrivateBank, wovon kopiert werden soll
     */
    public PrivateBank(PrivateBank other) {
        this.setName(other.getName());
        this.setIncomingInterest(other.getIncomingInterest());
        this.setOutgoingInterest(other.getOutgoingInterest());
    }

    /**
     * Überschriebene toString()-Methode
     * @return String-Darstellung des PrivateBanks Objektes
     */
    @Override
    public String toString() {
        return "\nName: " + getName() +
                "\nIncoming Interest: " + getIncomingInterest() +
                "\nOutgoing Interest: " + getOutgoingInterest() +
                "\nTransaktionsliste: " + accountsToTransactions + "\n";
    }

    /**
     * Überschriebene equals()-Methode
     * @param obj Zu vergleichendes Objekt
     * @return True, falls gleichwertig und False, falls nicht verschieden
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrivateBank)) {
            return false;
        }
        PrivateBank other = (PrivateBank) obj;
        return (this.getName().equals(other.getName()) &&
                this.getIncomingInterest() == other.getIncomingInterest() &&
                this.getOutgoingInterest() == other.getOutgoingInterest() &&
                this.accountsToTransactions.equals(other.accountsToTransactions));
    }


    // Methoden-Implementierungen des Interfaces Bank

    /**
     * Adds an account to the bank.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    @Override
    public void createAccount(String account) throws AccountAlreadyExistsException {
        if (accountsToTransactions.containsKey(account)) {
            // falls das Konto bereits existiert, soll eine Exception geworfen werden
            throw new AccountAlreadyExistsException("Das Konto " + account + " existiert bereits!");
        } else {
            // ansonsten wird das Konto mit einer leeren Transaktionsliste erstellt
            List<Transaction> leereListe = new ArrayList<>();
            accountsToTransactions.put(account, leereListe);
        }
    }


    /**
     * Adds an account (with specified transactions) to the bank.
     * Important: duplicate transactions must not be added to the account!
     *
     * @param account      the account to be added
     * @param transactions a list of already existing transactions which should be added to the newly created account
     * @throws AccountAlreadyExistsException    if the account already exists
     * @throws TransactionAlreadyExistException if the transaction already exists
     * @throws TransactionAttributeException    if the validation check for certain attributes fail
     */
    @Override
    public void createAccount(String account, List<Transaction> transactions)
            throws AccountAlreadyExistsException, TransactionAlreadyExistException, TransactionAttributeException, AccountDoesNotExistException {
        createAccount(account); // Erstmal Konto mit leerer Transaktionsliste anlegen
        for (Transaction t : transactions) {
            this.addTransaction(account, t);
        }

    }

    /**
     * Adds a transaction to an already existing account.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which should be added to the specified account
     * @throws TransactionAlreadyExistException if the transaction already exists
     * @throws AccountDoesNotExistException     if the specified account does not exist
     * @throws TransactionAttributeException    if the validation check for certain attributes fail
     */
    @Override
    public void addTransaction(String account, Transaction transaction)
            throws TransactionAlreadyExistException, AccountDoesNotExistException, TransactionAttributeException {
    if (!accountsToTransactions.containsKey(account)) {
        // falls das Konto nicht existiert, soll eine Exception geworfen werden
        throw new AccountDoesNotExistException("Das Konto " + account + " existiert nicht!");
    }
    if (containsTransaction(account, transaction)) {
        // falls die Transaktion bereits existiert, soll eine Exception geworfen werden
        throw new TransactionAlreadyExistException("Die Transaktion " + transaction +  " existiert bereits!");
    }
    if (transaction instanceof Payment) {
        Payment payment = (Payment) transaction;
        payment.setIncomingInterest(this.getIncomingInterest());
        payment.setOutgoingInterest(this.getOutgoingInterest());
    }

    accountsToTransactions.get(account).add(transaction);
    }


    /**
     * Removes a transaction from an account. If the transaction does not exist, an exception is
     * thrown.
     *
     * @param account     the account from which the transaction is removed
     * @param transaction the transaction which is removed from the specified account
     * @throws AccountDoesNotExistException     if the specified account does not exist
     * @throws TransactionDoesNotExistException if the transaction cannot be found
     */
    @Override
    public void removeTransaction(String account, Transaction transaction)
            throws AccountDoesNotExistException, TransactionDoesNotExistException {
    if (!accountsToTransactions.containsKey(account)) {
        throw new AccountDoesNotExistException("Das Konto " + account + " existiert nicht!");
    } else if (!containsTransaction(account, transaction)) {
        throw new TransactionDoesNotExistException("Die Transaktion " + transaction + " existiert nicht!");
    }

    // falls keine Exception aufgetreten ist, wird die angegebene Transaktion gelöscht
    accountsToTransactions.get(account).remove(transaction);
    }

    /**
     * Checks whether the specified transaction for a given account exists.
     *
     * @param account     the account from which the transaction is checked
     * @param transaction the transaction to search/look for
     */
    @Override
    public boolean containsTransaction(String account, Transaction transaction) {
    // erstmal prüfen, ob das Konto existiert
        if (!accountsToTransactions.containsKey(account)) {
            return false;
        }
    return accountsToTransactions.get(account).contains(transaction);
    }

    /**
     * Calculates and returns the current account balance.
     *
     * @param account the selected account
     * @return the current account balance
     */
    @Override
    public double getAccountBalance(String account) {
    double balance = 0.0;
    if (accountsToTransactions.containsKey(account)) {
        List<Transaction> liste = accountsToTransactions.get(account);
        for (Transaction transaction : liste) {
            // alle Transaktionen summieren
            balance += transaction.calculate();
        }
    }
    return balance;
    }

    /**
     * Returns a list of transactions for an account.
     *
     * @param account the selected account
     * @return the list of all transactions for the specified account
     */
    @Override
    public List<Transaction> getTransactions(String account) {
        if (accountsToTransactions.containsKey(account)) {
            return accountsToTransactions.get(account);
        }
        return null;
    }

    /**
     * Returns a sorted list (-> calculated amounts) of transactions for a specific account. Sorts the list either in ascending or descending order
     * (or empty).
     *
     * @param account the selected account
     * @param asc     selects if the transaction list is sorted in ascending or descending order
     * @return the sorted list of all transactions for the specified account
     */
    @Override
    public List<Transaction> getTransactionsSorted(String account, boolean asc) {
    // falls das Konto nicht existiert, soll null zurückgegeben werden
        if (!accountsToTransactions.containsKey(account)) {
            return null;
        }

        List<Transaction> transaktionsListe = new ArrayList<>(accountsToTransactions.get(account));
        if (asc) {
            // Comparator vergleicht Objekte über ihren double-Wert, nachdem diese
            // per Methodenreferenz über Transaction::calculate berechnet wurden.
            // comparingDouble erwartet als Argument eine Funktion, die einen double-Wert liefer
            transaktionsListe.sort(Comparator.comparingDouble(Transaction::calculate)); // Transaction::calculate verweist auf die calculate-Methode der Klasse Transaction
        }
        if (!asc) {
            transaktionsListe.sort(Comparator.comparingDouble(Transaction::calculate).reversed());
        }
        return transaktionsListe;


    }

    /**
     * Returns a list of either positive or negative transactions (-> calculated amounts).
     *
     * @param account  the selected account
     * @param positive selects if positive or negative transactions are listed
     * @return the list of all transactions by type
     */
    @Override
    public List<Transaction> getTransactionsByType(String account, boolean positive) {
    // erstmal prüfen, ob das Konto überhaupt existiert
        if (!accountsToTransactions.containsKey(account)) {
            return null;
        }

        List<Transaction> res = new ArrayList<>(); // resultierende Liste
        if (positive) {
            // nur positive Transaktionen in die Liste mit hinzunehmen
            for (Transaction t : accountsToTransactions.get(account)) {
                if (t.calculate() >= 0) {
                    res.add(t);
                }
            }
        } else {
            // nur negative Transaktionen in die Liste mit hinzunehmen
            for (Transaction t : accountsToTransactions.get(account)) {
                if (t.calculate() < 0) {
                    res.add(t);
                }
            }
        }
        return res;
    }

}

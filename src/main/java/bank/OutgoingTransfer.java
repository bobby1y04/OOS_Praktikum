package bank;

import bank.exceptions.TransactionAttributeException;

/**
 * Unterklasse der Klasse Transfer für ausgehende Überweisungen
 */
public class OutgoingTransfer extends Transfer{

    /**
     *  Konstruktor der Klasse OutgoingTransfer, der die Attribute
     *  date, amount und description setzt.
     * @param date Zeitpunkt der ausgehenden Überweisung
     * @param amount Geldmenge der ausgehenden Überweisung
     * @param description Beschreibung der ausgehenden Überweisung
     * @throws TransactionAttributeException Exception, falls ein Attribut fehlerhaft gesetzt wurde
     */
    public OutgoingTransfer(String date, double amount, String description) throws TransactionAttributeException {
        super(date, amount, description);
    }

    /**
     * Konstruktor der Klasse OutgoingTransfer, der die Attribute
     * date, amount, description, sender und recipient setzt.
     * @param date Zeitpunkt der ausgehenden Überweisung
     * @param amount Geldmenge der ausgehenden Überweisung
     * @param description Beschreibung der ausgehenden Überweisung
     * @param sender Sender der ausgehenden Überweisung
     * @param recipient Empfänger der ausgehenden Überweisung
     * @throws TransactionAttributeException Exception, falls ein Attribut fehlerhaft gesetzt wurde
     */
    public OutgoingTransfer(String date, double amount, String description, String sender, String recipient) throws TransactionAttributeException {
        super(date, amount, description, sender, recipient);
    }

    /**
     * Copy-Konstruktor der Klasse OutgoingTransfer
     * @param other Das zu kopierende Objekt der Klasse OutgoingTransfer
     * @throws TransactionAttributeException Exception, falls ein Attribut fehlerhaft gesetzt wurde
     */
    public OutgoingTransfer(Transfer other) throws TransactionAttributeException {
        super(other);
    }

    /**
     * Überschriebene calculate()-Methode aus der Oberklasse Transfer.
     * Im Falle von ausgehenden Überweisungen wird die Geldmenge mit
     * einem negativen Vorzeichen versehen.
     * @return Negativer Geldbetrag
     */
    @Override
    public double calculate() {
        return (super.calculate()*(-1));
    }
}

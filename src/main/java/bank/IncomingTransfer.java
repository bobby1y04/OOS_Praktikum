package bank;

import bank.exceptions.TransactionAttributeException;

/**
 * Unterklasse der Klasse Transfer für eingehende Überweisungen
 */
public class IncomingTransfer extends Transfer {

    /**
     * Konstruktor, der ein Objekt der Klasse IncomingTransfer erstellt und
     * die Attribute date, amount und description setzt.
     *
     * @param date        Zeitpunkt der eingehenden Überweisung
     * @param amount      Geldmenge der eingehenden Überweisung
     * @param description Beschreibung der eingehenden Überweisung
     * @throws TransactionAttributeException Exception, falls Attribut fehlerhaft gesetzt wurde
     */
    public IncomingTransfer(String date, double amount, String description) throws TransactionAttributeException {
        super(date, amount, description);
    }

    /**
     * Konstruktor, der ein Objekt der Klasse IncomingTransfer erstellt und
     * die Attribute date, amount, description, sender und recipient setzt.
     *
     * @param date        Zeitpunkt der eingehenden Überweisung
     * @param amount      Geldmenge der eingehenden Überweisung
     * @param description Beschreibung der eingehenden Überweisung
     * @param sender      Sender der eingehenden Überweisung
     * @param recipient   Empfänger der eingehenden Überweisung
     * @throws TransactionAttributeException Exception, falls Attribut fehlerhaft gesetzt wurde
     */
    public IncomingTransfer(String date, double amount, String description, String sender, String recipient) throws TransactionAttributeException {
        super(date, amount, description, sender, recipient);
    }

    /**
     * Copy-Konstruktor der Klasse IncomingTransfer
     * @param other Das zu kopierende Objekt der Klasse IncomingTransfer
     * @throws TransactionAttributeException Exception, falls ein Attribut fehlerhaft gesetzt wurde
     */
    public IncomingTransfer(Transfer other) throws TransactionAttributeException {
        super(other);
    }

    // Bei eingehenden Überweisungen ist es nicht notwendig, die calculate()-Methode
    // der Oberklasse zu überschreiben, da eine eingehende Überweisung auf das
    // eigene Konto immer positiv ist.
}

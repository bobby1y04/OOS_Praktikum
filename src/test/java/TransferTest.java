import bank.OutgoingTransfer;
import bank.IncomingTransfer;
import bank.exceptions.TransactionAttributeException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für die Klassen Transfer, OutgoingTransfer und IncomingTransfer.
 * Diese Klasse enthält Unit-Tests für:
 * - Konstruktor und Copy-Konstruktor
 * - Die calculate()-Methode (getrennt für Incoming- und OutgoingTransfer)
 * - Die equals()-Methode
 * - Die toString()-Methode
 */
public class TransferTest {

    OutgoingTransfer outTransfer; // Testobjekt für OutgoingTransfer
    IncomingTransfer inTransfer; // Testobjekt für IncomingTransfer

    /**
     * Initialisiert die Testobjekte vor jedem Test.
     * Stellt sicher, dass die Testdaten konsistent und bereit sind.
     *
     * @throws TransactionAttributeException falls ein Attribut der Transfers ungültig ist.
     */
    @BeforeEach
    public void init() throws TransactionAttributeException {
        outTransfer = new OutgoingTransfer("02.01.2025", 1000, "Happy Birthday", "Bob", "Alice");
        inTransfer = new IncomingTransfer("23.03.2025", 500, "Alles Gute zum Geburtstag!", "Alice", "Bob");
    }

    /**
     * Testet den Konstruktor der Klassen OutgoingTransfer und IncomingTransfer.
     * Überprüft, ob die Attribute der erstellten Objekte korrekt gesetzt wurden.
     */
    @Test
    public void testConstructor() {
        // Überprüfung der Attribute von outTransfer
        assertEquals(outTransfer.getDate(), "02.01.2025");
        assertEquals(outTransfer.getAmount(), 1000);
        assertEquals(outTransfer.getDescription(), "Happy Birthday");
        assertEquals(outTransfer.getSender(), "Bob");
        assertEquals(outTransfer.getRecipient(), "Alice");

        // Überprüfung der Attribute von inTransfer
        assertEquals(inTransfer.getDate(), "23.03.2025");
        assertEquals(inTransfer.getAmount(), 500);
        assertEquals(inTransfer.getDescription(), "Alles Gute zum Geburtstag!");
        assertEquals(inTransfer.getSender(), "Alice");
        assertEquals(inTransfer.getRecipient(), "Bob");
    }

    /**
     * Testet den Copy-Konstruktor der Klassen OutgoingTransfer und IncomingTransfer.
     * Überprüft, ob die Kopien der Transfer-Objekte identisch mit den Originalen sind.
     *
     * @throws TransactionAttributeException falls ein Attribut ungültig ist.
     */
    @Test
    public void testCopyConstructor() throws TransactionAttributeException {
        // Erstellen von Kopien der Testobjekte
        OutgoingTransfer outTransfer_copy = new OutgoingTransfer(outTransfer);
        IncomingTransfer inTransfer_copy = new IncomingTransfer(inTransfer);

        // Überprüfung, ob die Kopien den Originalen entsprechen
        assertEquals(outTransfer_copy, outTransfer);
        assertEquals(inTransfer_copy, inTransfer);
    }

    /**
     * Testet die calculate()-Methode für OutgoingTransfer und IncomingTransfer.
     * Überprüft, ob die berechneten Beträge korrekt sind.
     */
    @Test
    public void testCalculate() {
        // OutgoingTransfer: Betrag wird negativ dargestellt
        assertEquals(outTransfer.calculate(), -1000);

        // IncomingTransfer: Betrag bleibt positiv
        assertEquals(inTransfer.calculate(), 500);
    }

    /**
     * Testet die equals()-Methode der Klasse OutgoingTransfer.
     * Überprüft, ob zwei OutgoingTransfer-Objekte mit identischen Attributen als gleich erkannt werden.
     *
     * @throws TransactionAttributeException falls ein Attribut ungültig ist.
     */
    @Test
    public void testEquals() throws TransactionAttributeException {
        // Erstellen eines weiteren OutgoingTransfer-Objekts mit denselben Attributen wie outTransfer
        OutgoingTransfer other = new OutgoingTransfer("02.01.2025", 1000, "Happy Birthday", "Bob", "Alice");
        // assertEquals(outTransfer.equals(other), true);
        // Überprüfung, ob die beiden Objekte als gleich erkannt werden
        assertTrue(outTransfer.equals(other));
    }

    /**
     * Testet die toString()-Methode der Klasse OutgoingTransfer.
     * Überprüft, ob die String-Repräsentation des Objekts korrekt ist.
     */
    @Test
    public void toStringTest() {
        assertEquals(outTransfer.toString(),
                "Date: 02.01.2025\nAmount: -1000.0\nDescription: Happy Birthday\nSender: Bob\nRecipient: Alice\n");
    }
}
import bank.Payment;
import bank.exceptions.TransactionAttributeException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für die Klasse Payment.
 * Diese Klasse enthält Unit-Tests für:
 * - Konstruktor und Copy-Konstruktor
 * - Die calculate()-Methode
 * - Die equals()-Methode
 * - Die toString()-Methode
 * - Ungültige Eingabewerte für Zinsattribute (incomingInterest und outgoingInterest)
 */
public class PaymentTest {

    Payment payment1; // Testobjekt 1
    Payment payment2; // Testobjekt 2
    Payment payment3; // Testobjekt 3

    /**
     * Initialisiert die Testobjekte vor jedem Test.
     * Stellt sicher, dass die Testdaten konsistent und bereit sind.
     *
     * @throws TransactionAttributeException falls ein Attribut der Payment-Objekte ungültig ist.
     */
    @BeforeEach
    public void init() throws TransactionAttributeException {
        payment1 = new Payment("23.11.2024", -500, "Bafög: Nachzahlung", 0.05, 0.02);
        payment2 = new Payment("24.11.2024", 20, "Nachhilfegeld", 0.05, 0.02);
        payment3 = new Payment("25.11.2024", 1000, "Prämie", 0.05, 0.02);
    }

    /**
     * Testet den Konstruktor der Klasse Payment.
     * Überprüft, ob die Attribute der erstellten Objekte korrekt gesetzt wurden.
     */
    @Test
    public void testConstructor() {
        // Überprüfung der Attribute von payment1
        assertEquals(payment1.getDate(), "23.11.2024");
        assertEquals(payment1.getAmount(), -500);
        assertEquals(payment1.getDescription(), "Bafög: Nachzahlung");
        assertEquals(payment1.getIncomingInterest(), 0.05);
        assertEquals(payment1.getOutgoingInterest(), 0.02);

        // Überprüfung der Attribute von payment2
        assertEquals(payment2.getDate(), "24.11.2024");
        assertEquals(payment2.getAmount(), 20);
        assertEquals(payment2.getDescription(), "Nachhilfegeld");
        assertEquals(payment2.getIncomingInterest(), 0.05);
        assertEquals(payment2.getOutgoingInterest(), 0.02);

        // Überprüfung der Attribute von payment3
        assertEquals(payment3.getDate(), "25.11.2024");
        assertEquals(payment3.getAmount(), 1000);
        assertEquals(payment3.getDescription(), "Prämie");
        assertEquals(payment3.getIncomingInterest(), 0.05);
        assertEquals(payment3.getOutgoingInterest(), 0.02);
    }

    /**
     * Testet den Copy-Konstruktor der Klasse Payment.
     * Überprüft, ob die Kopie eines Payment-Objekts identisch mit dem Original ist.
     *
     * @throws TransactionAttributeException falls ein Attribut ungültig ist.
     */
    @Test
    public void testCopyConstructor() throws TransactionAttributeException {
        Payment paymentCopy = new Payment(payment1); // Kopie von payment1
        assertEquals(payment1, paymentCopy); // Vergleicht das Original mit der Kopie
    }

    /**
     * Testet die calculate()-Methode der Klasse Payment.
     * Überprüft, ob der berechnete Betrag korrekt ist.
     */
    @Test
    public void testCalculate() {
        // Überprüfung der Berechnung von payment1 (negative Amount mit outgoingInterest)
        assertEquals(payment1.calculate(), (-500 + (-500 * 0.02)));

        // Überprüfung der Berechnung von payment2 (positive Amount mit incomingInterest)
        assertEquals(payment2.calculate(), (20 - (20 * 0.05)));
    }

    /**
     * Testet die equals()-Methode der Klasse Payment.
     * Überprüft, ob zwei Payment-Objekte mit identischen Attributen als gleich erkannt werden.
     *
     * @throws TransactionAttributeException falls ein Attribut ungültig ist.
     */
    @Test
    public void testEquals() throws TransactionAttributeException {
        Payment other = new Payment("23.11.2024", -500, "Bafög: Nachzahlung", 0.05, 0.02);
        // assertEquals(payment1.equals(other), true);
        assertTrue(payment1.equals(other));
    }

    /**
     * Testet die toString()-Methode der Klasse Payment.
     * Überprüft, ob die String-Repräsentation eines Payment-Objekts korrekt ist.
     */
    @Test
    public void testToString() {
        assertEquals(payment1.toString(),
                "Date: 23.11.2024\nAmount: -510.0\nDescription: Bafög: Nachzahlung\nIncoming interest: 0.05\nOutgoing interest: 0.02\n");
    }

    /**
     * Testet, ob der Konstruktor der Klasse Payment bei ungültigen Zinswerten
     * (incomingInterest und outgoingInterest) eine TransactionAttributeException auslöst.
     * Ungültige Werte sind z. B. Werte kleiner als 0 oder größer als 1.
     *
     * @param invalidInterest Ein ungültiger Zinswert, der als Parameter für den Konstruktor verwendet wird.
     *                        Werte werden durch @ValueSource bereitgestellt.
     */
    @ParameterizedTest
    @ValueSource(doubles = {-3.0, -2.0, -0.1, 1.1, 2.0, 3.0})
    public void testInvalidInterests(double invalidInterest) {
        // assertThrows überprüft, ob eine spezifische Exception während der Ausführung des übergebenen Codes geworfen wird.
        assertThrows(TransactionAttributeException.class, () -> {
            // Lambda-Expression: Definiert den Codeblock, der getestet wird.
            // In diesem Codeblock wird ein Payment-Objekt mit ungültigen Zinswerten erstellt.

            Payment invalidPayment = new Payment("26.11.2024", 41836, "Test", invalidInterest, invalidInterest);
            // Versucht, ein Payment-Objekt zu erstellen. Falls die Zinswerte ungültig sind (z. B. < 0 oder > 1),
            // sollte der Konstruktor eine TransactionAttributeException werfen.

            assertNotEquals(invalidPayment.getIncomingInterest(), invalidInterest);
            // Falls keine Exception geworfen wurde, überprüft dieser Assertion-Test,
            // dass der Wert für incomingInterest nicht dem ungültigen Eingabewert entspricht.

            assertNotEquals(invalidPayment.getOutgoingInterest(), invalidInterest);
            // Ebenso wird geprüft, dass outgoingInterest nicht dem ungültigen Eingabewert entspricht.
            // Diese Zeilen werden jedoch nur erreicht, wenn keine Exception geworfen wird.
        });
        // Der Test ist bestanden, wenn die TransactionAttributeException geworfen wird.
    }
}
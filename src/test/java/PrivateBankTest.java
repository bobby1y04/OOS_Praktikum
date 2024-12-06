import bank.*;
import bank.exceptions.*;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für die Klasse PrivateBank.
 * Tests für Konstruktor, alle implementierten Methoden des Interfaces Bank, equals und toString.
 */
public class PrivateBankTest {

    PrivateBank privateBank1; // Test-Objekt für eine Bank
    PrivateBank privateBank2; // Zweites Test-Objekt für Vergleiche
    PrivateBank privateBank3; // Drittes Test-Objekt für Vergleiche
    List<Transaction> list = new ArrayList<>(); // Liste von Transaktionen für Tests

    /**
     * Initialisierungsmethode, die vor jedem Test ausgeführt wird.
     * Sie löscht bestehende JSON-Dateien, um Tests unabhängig voneinander zu machen,
     * und initialisiert eine Test-Bank mit Standardwerten.
     */
    @BeforeEach
    public void init() throws TransactionAttributeException, IOException {
        File directory = new File("/Users/bobby/02-FH/WiSe24_25/OOS/03-Praktikum/OOS_Praktikum/src/main/java/bank/Konten/");

        // Löscht alle Dateien im Verzeichnis
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && !file.delete()) {
                        throw new IOException("Datei konnte nicht gelöscht werden: " + file.getAbsolutePath());
                    }
                }
            }
        }

        // Initialisiere die Test-Bank
        privateBank1 = new PrivateBank("Fleeca", 0.05, 0.02, "/Users/bobby/02-FH/WiSe24_25/OOS/03-Praktikum/OOS_Praktikum/src/main/java/bank/Konten/");

        // Initialisiere Test-Transaktionen
        IncomingTransfer inTransfer = new IncomingTransfer("23.03.2025", 500, "Alles Gute zum Geburtstag!", "Alice", "Bob");
        OutgoingTransfer outTransfer = new OutgoingTransfer("02.01.2025", 1000, "Happy Birthday", "Bob", "Alice");
        IncomingTransfer inTransfer2 = new IncomingTransfer("10.10.2024", 10, "AOK Prämie", "AOK", "Bob");
        OutgoingTransfer outTransfer2 = new OutgoingTransfer("11.06.2025", 324, "Semesterbeitrag", "Bob", "Fachhochschule Aachen");
        Payment payment1 = new Payment("11.06.2025", 350, "Erstes Gehalt", 0.05, 0.02);
        Payment payment2 = new Payment("11.06.2025", 500, "Zweites Gehalt", 0.05, 0.02);
        list.add(inTransfer);
        list.add(outTransfer);
        list.add(inTransfer2);
        list.add(outTransfer2);
        list.add(payment1);
        list.add(payment2);
    }

    /**
     * Test für den Konstruktor der PrivateBank-Klasse.
     * Überprüft, ob die Attribute korrekt initialisiert wurden.
     */
    @Test
    public void testConstructor() {
        assertEquals(privateBank1.getName(), "Fleeca");
        assertEquals(privateBank1.getIncomingInterest(), 0.05);
        assertEquals(privateBank1.getOutgoingInterest(), 0.02);
    }

    /**
     * Test für den Copy-Konstruktor der PrivateBank-Klasse.
     * Überprüft, ob das kopierte Objekt den gleichen Zustand wie das Original hat.
     */
    @Test
    public void tesCopyConstructor() throws TransactionAttributeException {
        assertDoesNotThrow(() -> {
            privateBank2 = new PrivateBank(privateBank1);
        });
        assertEquals(privateBank1, privateBank2);
    }

    /**
     * Test für die equals()-Methode.
     * Überprüft verschiedene Szenarien, ob zwei PrivateBank-Objekte als gleich angesehen werden.
     */
    @Test
    public void testEquals() throws TransactionAttributeException {
        assertDoesNotThrow(() -> {
            privateBank2 = new PrivateBank("Sparkasse", 0.9, 0.4, "/Users/bobby/02-FH/WiSe24_25/OOS/03-Praktikum/OOS_Praktikum/src/main/java/bank/Konten/");
            privateBank3 = new PrivateBank(privateBank1);
        });

        assertEquals(privateBank1, privateBank3);
        assertNotEquals(privateBank1, privateBank2);
        assertNotEquals(null, privateBank1);
        assertNotEquals(null, privateBank2);
    }

    /**
     * Test für die toString()-Methode.
     * Überprüft, ob die String-Repräsentation der Bank korrekt ist.
     */
    @Test
    public void testToString() {
        assertEquals(privateBank1.toString(), "\nName: Fleeca\nIncoming Interest: 0.05\nOutgoing Interest: 0.02\nTransaktionsliste: {}\n");
    }

    /**
     * Test für das Erstellen eines neuen Kontos.
     * Überprüft auch, ob eine Ausnahme geworfen wird, wenn ein Konto bereits existiert.
     */
    @Test
    public void testCreateAccount() {
        assertDoesNotThrow(() -> {
            privateBank1.createAccount("Bob");
        });

        assertThrows(AccountAlreadyExistsException.class, () -> {
            privateBank1.createAccount("Bob");
        });
    }

    /**
     * Test für das Erstellen eines Kontos mit einer Liste von Transaktionen.
     * Überprüft auch, ob Ausnahmen korrekt geworfen werden, z. B. bei ungültigen Transaktionen.
     */
    @Test
    public void testCreateAccountWithTransactionList() {
        assertDoesNotThrow(() -> {
            privateBank1.createAccount("Bob", list);
        });

        assertThrows(AccountAlreadyExistsException.class, () -> {
            privateBank1.createAccount("Bob", list);
        });

        assertThrows(TransactionAttributeException.class, () -> {
            OutgoingTransfer invalidTransfer = new OutgoingTransfer("13.09.2025", -19, "Negativer Betrag", "Kev", "Bob");
            list.add(invalidTransfer);
            privateBank1.createAccount("invalidAccount", list);
        });
    }

    /**
     * Test für das Hinzufügen von Transaktionen.
     * Überprüft auch, ob die entsprechenden Ausnahmen geworfen werden.
     */
    @Test
    public void testAddTransactions() throws TransactionAttributeException {
        IncomingTransfer testInTransfer = new IncomingTransfer("16.04.2025", 30, "Mathekurs", "Heinz", "Bob");
        OutgoingTransfer testOutTransfer = new OutgoingTransfer("16.04.2025", 50, "Sommercamp", "Bob", "Trainer");
        Payment testPayment = new Payment("16.04.2025", 500, "Lohn", 0.05, 0.02);
        assertDoesNotThrow(() -> {
            privateBank1.createAccount("Bob");
            privateBank1.addTransaction("Bob", testInTransfer);
            privateBank1.addTransaction("Bob", testOutTransfer);
            privateBank1.addTransaction("Bob", testPayment);
        });

        List<Transaction> transactions = privateBank1.getTransactions("Bob");
        assertEquals(3, transactions.size());
        assertTrue(transactions.contains(testInTransfer));
        assertTrue(transactions.contains(testOutTransfer));
        assertTrue(transactions.contains(testPayment));

        assertThrows(TransactionAlreadyExistException.class, () -> {
            privateBank1.addTransaction("Bob", testInTransfer);
        });
        assertThrows(AccountDoesNotExistException.class, () -> {
            privateBank1.addTransaction("Alice", testInTransfer);
        });
    }

    /**
     * Test für das Entfernen von Transaktionen.
     * Überprüft, ob Transaktionen korrekt entfernt werden, und ob Ausnahmen korrekt geworfen werden.
     */
    @Test
    public void testRemoveTransactions() throws TransactionAttributeException {
        IncomingTransfer testTransaction = new IncomingTransfer("16.04.2025", 30, "Mathekurs", "Heinz", "Bob");
        assertDoesNotThrow(() -> {
            privateBank1.createAccount("Bob");

            privateBank1.addTransaction("Bob", testTransaction);
            List<Transaction> transactions = privateBank1.getTransactions("Bob");
            assertEquals(1, transactions.size());
            assertTrue(transactions.contains(testTransaction));

            privateBank1.removeTransaction("Bob", testTransaction);
            transactions = privateBank1.getTransactions("Bob");
            assertEquals(0, transactions.size());
            assertFalse(transactions.contains(testTransaction));
        });

        assertThrows(TransactionDoesNotExistException.class, () -> {
            privateBank1.removeTransaction("Bob", testTransaction);
        });
    }

    /**
     * Test für die containsTransaction()-Methode.
     * Überprüft, ob Transaktionen korrekt erkannt werden.
     */
    @Test
    public void testContainsTransactions() throws TransactionAttributeException {
        IncomingTransfer testTransaction = new IncomingTransfer("16.04.2025", 30, "Mathekurs", "Heinz", "Bob");
        assertDoesNotThrow(() -> {
            privateBank1.createAccount("Bob");

            privateBank1.addTransaction("Bob", testTransaction);
            List<Transaction> transactions = privateBank1.getTransactions("Bob");
            assertEquals(1, transactions.size());
            assertTrue(transactions.contains(testTransaction));
        });
        assertTrue(privateBank1.containsTransaction("Bob", testTransaction));
        assertFalse(privateBank1.containsTransaction("Bob", new IncomingTransfer("01.01.2025", 10, "Invalid", "Alice", "Bob")));
    }

    /**
     * Test für das Abrufen des Kontostands eines Kontos.
     * Überprüft, ob der Kontostand korrekt berechnet wurde und dieser auch zurückgegeben wird.
     */
    @Test
    public void testGetAccountBalance() throws TransactionAlreadyExistException, AccountAlreadyExistsException, AccountDoesNotExistException, TransactionAttributeException {
        privateBank1.createAccount("Bob", list);
        assertEquals(privateBank1.getAccountBalance("Bob"), -6.5);
    }

    /**
     * Test für das Abrufen der Transaktionsliste eines Kontos.
     */
    @Test
    public void testGetTransactions() throws TransactionAlreadyExistException, AccountAlreadyExistsException, AccountDoesNotExistException, TransactionAttributeException {
        privateBank1.createAccount("Bob", list);
        assertEquals(privateBank1.getTransactions("Bob"), list);
    }

    /**
     * Test für das Sortieren von Transaktionen.
     */
    @Test
    public void testGetTransactionsSorted() throws TransactionAlreadyExistException, AccountAlreadyExistsException, AccountDoesNotExistException, TransactionAttributeException {
        privateBank1.createAccount("Bob", list);
        list.sort(Comparator.comparing(Transaction::calculate));
        assertEquals(privateBank1.getTransactionsSorted("Bob", true), list);
    }

    /**
     * Test für das Filtern von Transaktionen nach Typ (positiv/negativ).
     */
    @Test
    public void testGetTransactionsByType() throws TransactionAlreadyExistException, AccountAlreadyExistsException, AccountDoesNotExistException, TransactionAttributeException {
        privateBank1.createAccount("Bob", list);
        List<Transaction> testList = new ArrayList<>();
        for (Transaction t : list) {
            if (t.calculate() >= 0) {
                testList.add(t);
            }
        }
        assertEquals(privateBank1.getTransactionsByType("Bob", true), testList);
    }

    /**
     * Test für die Persistenz: Überprüft, ob die Bankdaten korrekt gespeichert und wieder eingelesen werden.
     * (Für das erzeugte Verzeichnis mit serialisierten JSON-Dokumenten)
     */
    @AfterEach
    public void serializeTest() {
        // Erstelle eine neue Instanz der PrivateBank, die die gespeicherten JSON-Dateien einliest
        PrivateBank testBank = new PrivateBank("Fleeca", 0.05, 0.02, "/Users/bobby/02-FH/WiSe24_25/OOS/03-Praktikum/OOS_Praktikum/src/main/java/bank/Konten/");

        // Vergleiche die ursprüngliche Bankinstanz mit der neu geladenen Instanz
        assertEquals(privateBank1, testBank, "Die gespeicherten Daten stimmen nicht mit den geladenen Daten überein.");

    }
}
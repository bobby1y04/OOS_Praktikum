// Main.java

// The Main Class tests the functionalities of the bank package.
// It imports the package bank.
import bank.*;  // Importiere das Paket in die Main-Klasse
import bank.exceptions.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        test_functionalities();


    }

    public static void test_functionalities() {
        try {

            System.out.println("\n\n+-+-+-+-+-+-+-+-+-+-+-+-+- ANLEGEN VON KONTEN +-+-+-+-+-+-+-+-+-+-+-+-+-\n");
            // Erzeugen eines PrivateBank- und PrivateBankAlt-Objektes
            PrivateBank privateBank = new PrivateBank("Bank1", 0.05, 0.02);
            PrivateBankAlt privateBankAlt = new PrivateBankAlt("Bank2", 0.05, 0.02);



            // Methoden-Test: createAccount() & addTransaction()
            privateBank.createAccount("Bobby Ly");
            privateBank.addTransaction("Bobby Ly", new Payment("23.03.2004", 300, "Geburtstag", 0.05, 0.02));
            privateBankAlt.createAccount("Son Goku");
            privateBankAlt.addTransaction("Son Goku", new Transfer("02.01.2024", 20, "Essen", "Bobby", "Son Goku"));
            privateBankAlt.addTransaction("Son Goku", new Transfer("03.01.2024", 2.80, "Mensa", "Son Goku", "Studierendenwerk Aachen"));

            List<Transaction> transaktionsListe1 = new ArrayList<>();
            transaktionsListe1.add(new Payment("01.01.2024", 1000,"Einzahlung", 0.05, 0.02));
            transaktionsListe1.add(new Transfer("02.01.2024", 5.99, "Monatliches Spotify Abo", "Bobby Ly", "Spotify GmbH"));
            privateBank.createAccount("Luffy", transaktionsListe1);

            List<Transaction> transaktionsListe2 = new ArrayList<>();
            transaktionsListe2.add(new Payment("05.05.2024", -100,"Auszahlung", 0.05, 0.02));
            transaktionsListe2.add(new Transfer("06.05.2024", 520, "Gehalt", "Klingler", "Vegeta"));
            privateBankAlt.createAccount("Vegeta", transaktionsListe2);



            // Methoden-Test: getAccountBalance()
            System.out.println("PrivateBank Bobby Ly (Konto1) Balance: " + privateBank.getAccountBalance("Bobby Ly"));
            System.out.println("PrivateBankAlt Son Goku (Konto1) Balance: " + privateBankAlt.getAccountBalance("Son Goku"));
            System.out.println("PrivateBank Luffy (Konto2) Balance: " + privateBank.getAccountBalance("Luffy"));
            System.out.println("PrivateBankAlt Vegeta (Konto2) Balance: " + privateBankAlt.getAccountBalance("Vegeta"));

            // Methoden-Test: toString()
            System.out.println(privateBank);
            System.out.println(privateBankAlt);


            System.out.println("\n\n+-+-+-+-+-+-+-+-+-+-+-+-+- Testen des Copy-Konstruktors +-+-+-+-+-+-+-+-+-+-+-+-+-\n");
            PrivateBank privateBank1_copy = privateBank;
            System.out.print("Test 1: ");
            System.out.println(privateBank1_copy.equals(privateBank));
            PrivateBank privateBank1_different = new PrivateBank("Sparkasse", 0.05, 0.02);
            System.out.print("Test 2: ");
            System.out.println(privateBank1_different.equals(privateBank));

            System.out.println("\n\n+-+-+-+-+-+-+-+-+-+-+-+-+- Test: Hinzufügen und Löschen einer Transaktion +-+-+-+-+-+-+-+-+-+-+-+-+-\n");
            privateBank.addTransaction("Bobby Ly", new Transfer("10.10.2023", 1000, "Bonus1", "Bonusprogramm", "Bobby Ly"));
            System.out.println("Transaktion hinzugefügt!");
            privateBank.addTransaction("Bobby Ly", new Transfer("11.10.2023", 500, "Bonusrückzahlung", "Bobby Ly", "Bonusprogramm"));
            System.out.println("Transaktion hinzugefügt!");
            privateBank.addTransaction("Bobby Ly", new Payment("11.10.2023", -700, "Miete", 0.05, 0.02));
            System.out.println("Transaktion hinzugefügt!");
            Payment lastTransaction = new Payment("10.10.2023", -200, "Rückzahlung" , 0.05, 0.02);
            privateBank.addTransaction("Bobby Ly", lastTransaction);
            System.out.println("Transaktion hinzugefügt!");
            System.out.println(privateBank.getTransactions("Bobby Ly"));
            privateBank.removeTransaction("Bobby Ly", lastTransaction);
            System.out.println("Transaktion gelöscht!");
            System.out.println(privateBank.getTransactions("Bobby Ly"));

            System.out.println("\n\n+-+-+-+-+-+-+-+-+-+-+-+-+- Testen der Sortiermethoden +-+-+-+-+-+-+-+-+-+-+-+-+-\n");
            System.out.println("Aufsteigend sortiert: \n");
            System.out.println(privateBank.getTransactionsSorted("Bobby Ly", true));
            System.out.println("Absteigend sortiert: \n");
            System.out.println(privateBank.getTransactionsSorted("Bobby Ly", false));
            System.out.println("Nur positive Transaktionen anzeigen: \n");
            System.out.println(privateBank.getTransactionsByType("Bobby Ly", true));
            System.out.println("Nur negative Transaktionen anzeigen: \n");
            System.out.println(privateBank.getTransactionsByType("Bobby Ly", false));

            System.out.println("\n\n+-+-+-+-+-+-+-+-+-+-+-+-+- Testen der Exceptions +-+-+-+-+-+-+-+-+-+-+-+-+-\n");
            try {
                System.out.println("AccountAlreadyExistsException: ");
                privateBank.createAccount("Bobby Ly");
            } catch (AccountAlreadyExistsException e) {
                // e.printStackTrace(System.err);
                System.out.println(e.getMessage());
            }

            System.out.println();

            try {
                System.out.println("AccountDoesNotExistException: ");
                privateBank.addTransaction("Dummy Dumm", lastTransaction);
            } catch (AccountDoesNotExistException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();

            try {
                System.out.println("TransactionAlreadyExistsException: ");
                privateBank.addTransaction("Bobby Ly", new Transfer("10.10.2023", 1000, "Bonus1", "Bonusprogramm", "Bobby Ly"));
            } catch (TransactionAlreadyExistException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();

            try {
                System.out.println("TransactionAttributeException: ");
                privateBank.addTransaction("Bobby Ly", new Transfer("10.10.2023", -1, "Test - negativer Amount", "Tester", "Bobby Ly"));
            } catch (TransactionAttributeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();

            try {
                System.out.println("TransactionDoesNotExistException: ");
                privateBank.removeTransaction("Bobby Ly", lastTransaction);
            } catch (TransactionDoesNotExistException e) {
                System.out.println(e.getMessage());
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



// Main.java

// The Main Class tests the functionalities of the bank package.
// It imports the package bank.
import bank.*;  // Importiere das Paket in die Main-Klasse

public class Main {
    public static void main(String[] args) {
      test_functionalities();
    }

    public static void test_functionalities() {
        Transfer t1 = new Transfer("23.03.2004", 500, "Alles Gute zum Geburtstag!", "Bobby", "Alice");
        Transfer t2 = t1;
        Transfer t3 = new Transfer("23.03.2004", 500, "Rückzahlung", "Alice", "Bobby");

        Payment p1 = new Payment("15.02.2019", -500, "Klassenfahrt", 0.05, 0.1);
        Payment p1_copy = p1;
        p1_copy.setAmount(1000);
        Payment p2 = new Payment("15.02.2019", -1000, "Klassenfahrt", 0.05, 0.1);


        System.out.println("\n\n\n+-------------------------------------------------------------------+");
        System.out.println("Testen der calculate()-Methode:");
        System.out.println("+-------------------------------------------------------------------+\n");
        System.out.println("Berechnung für t1: " + t1.calculate());
        System.out.println("\nBerechnung für p1: " + p1.calculate());
        System.out.println("\nBerechnung für p1_copy: " + p1_copy.calculate());
        System.out.println("\nBerechnung für p2: " + p2.calculate());

        System.out.println("\n\n\n+-------------------------------------------------------------------+");
        System.out.println("Testen der toString()-Methode:");
        System.out.println("+-------------------------------------------------------------------+\n");
        System.out.println("t1:\n" + t1.toString());
        System.out.println("t2:\n" + t2.toString());
        System.out.println("t3:\n" + t3.toString());
        System.out.println("p1:\n" + p1.toString());
        System.out.println("p2:\n" + p1.toString());
        System.out.println("p3:\n" + p1.toString());


        System.out.println("\n\n\n+-------------------------------------------------------------------+");
        System.out.println("Testen der equals()-Methode:");
        System.out.println("+-------------------------------------------------------------------+\n");
        System.out.println("t1.equals(t2): " + t1.equals(t2));
        System.out.println("t1.equals(t3): " + t1.equals(t3));
        System.out.println("p1.equals(p1_copy): " + p1.equals(p1_copy));
        System.out.println("p1.equals(p2): " + p1.equals(p2));
    }

}

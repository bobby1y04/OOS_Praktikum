package bank;

import bank.exceptions.TransactionAttributeException;
import com.google.gson.*;

import java.lang.reflect.Type;

public class Serializer implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {

    /**
     * Diese Methode serialisiert ein Objekt vom Typ "Transaction" in ein Json-Element
     * @param transaction Das zu serialisierende Transaktionsobjekt
     * @param typeOfTransaction Typ der Transaktion
     * @param context Kontext für die Json-Serialisierung
     * @return Die serialisierte Transaktion in Form eines Json-Elementes
     */
   @Override
    public JsonElement serialize(Transaction transaction, Type typeOfTransaction, JsonSerializationContext context) {

       JsonObject typ = new JsonObject(); // Json-Objekt für die Art der Transaktion
       JsonObject data = new JsonObject(); // Json-Objekt für die Nutzdaten der Transaktion


       if (transaction instanceof Payment) {
           // Attribute der Payment-Klasse hinzufügen
           typ.addProperty("CLASSNAME", "Payment");
           data.addProperty("incomingInterest", ((Payment) transaction).getIncomingInterest());
           data.addProperty("outgoingInterest", ((Payment) transaction).getOutgoingInterest());
       } else if (transaction instanceof IncomingTransfer) {
           // Attribute der IncomingTransfer-Klasse hinzufügen
           typ.addProperty("CLASSNAME", "IncomingTransfer");
           data.addProperty("sender", ((IncomingTransfer) transaction).getSender());
           data.addProperty("recipient", ((IncomingTransfer) transaction).getRecipient());
       } else if (transaction instanceof OutgoingTransfer) {
           // Attribute der OutgoingTransfer-Klasse hinzufügen
           typ.addProperty("CLASSNAME", "OutgoingTransfer");
           data.addProperty("sender", ((OutgoingTransfer) transaction).getSender());
           data.addProperty("recipient", ((OutgoingTransfer) transaction).getRecipient());
       }

       // Gemeinsame Attribute hinzufügen
       data.addProperty("date", transaction.getDate());
       data.addProperty("amount", transaction.getAmount());
       data.addProperty("description", transaction.getDescription());

        // Typ und Nutzdaten des Json-Objektes zusammenfügen
       typ.add("INSTANCE", data);

       return typ;

   }

    /**
     * Diese Methode deserialisiert ein Json-Element in ein Objekt vom Typ "Transaction"
     * @param json Das zu deserialisierende Json-Element
     * @param typeOfTransaction Der Typ der Transaktion
     * @param context Kontext für die Json-Deserialisierung
     * @return Das deserialisierte Json-Element in Form eines Transaktionobjektes
     */
   @Override
    public Transaction deserialize(JsonElement json, Type typeOfTransaction, JsonDeserializationContext context) {
       JsonObject obj = (JsonObject) json; // Json-Element wird in ein Json-Objekt umgewandelt, da
                                           // JsonObject als Unterklasse von JsonElement mehr Methoden
                                           // zur Verfügung stellt
       JsonObject data = (JsonObject) obj.get("INSTANCE"); // Nutzdaten des Json-Objektes extrahieren
       String className = obj.get("CLASSNAME").getAsString(); // Typ der Transaktion extrahieren

       // Gemeinsame Attribute aus den Nutzdaten extrahieren
       String date = data.get("date").getAsString();
       double amount = data.get("amount").getAsDouble();
       String description = data.get("description").getAsString();

       try {
           /*
            * Fallunterscheidung nach Typ der Transaktion.
            * Spezifische Daten werden extrahiert und ein neues Objekt
            * vom entsprechenden Typ wird zurückgegeben.
            */
           if (className.equals("Payment")) {
               double incomingInterest = data.get("incomingInterest").getAsDouble();
               double outgoingInterest = data.get("outgoingInterest").getAsDouble();
               return new Payment(date, amount, description, incomingInterest, outgoingInterest);
           } else if (className.equals("IncomingTransfer")) {
               String sender = data.get("sender").getAsString();
               String recipient = data.get("recipient").getAsString();
               return new IncomingTransfer(date, amount, description, sender, recipient);
           } else {
               String sender = data.get("sender").getAsString();
               String recipient = data.get("recipient").getAsString();
               return new OutgoingTransfer(date, amount, description, sender, recipient);
           }
       } catch (TransactionAttributeException e) {
           System.out.println(e.getMessage());
       }
       return null;
   }

}

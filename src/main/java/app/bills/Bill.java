package app.bills;

import inventory.Item;
import orders.OrderItem;

import java.io.*;

public class Bill {
    private static final String billPath = "src/main/java/app/bills/bill.txt";
    private static final String creaditCardDetailsPath = "src/main/java/app/bills/cardDetails.csv";

    public Double generateTotal(Item stockItem, OrderItem orderedItem, int qtyLimit) {
        if (orderedItem.getQty() <= stockItem.getQty() && orderedItem.getQty() <= qtyLimit) {
            if (!cardAlreadyPresent(orderedItem.getCardNumber())) {
                this.insertNewCardDetails(orderedItem.getCardNumber());
            }
            return orderedItem.getQty() * stockItem.getPrice();
        } else {
            if(orderedItem.getQty() > stockItem.getQty()){
                this.writeBill("Not enough items of " + stockItem.getName() + " in stock");
                this.writeBill("Please correct quantities.");
                return 0.0;
            }else {
                this.writeBill("Out of permissible limit for the item " + stockItem.getName());
                this.writeBill("Please correct quantities.");
                return 0.0;
            }
        }
    }

    public void writeBill(String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(billPath, true))) {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cardAlreadyPresent(String cardNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(creaditCardDetailsPath))) {
            String line = "";
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) break;
                if (line.trim().equals(cardNumber)) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void insertNewCardDetails(String cardNumber) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(creaditCardDetailsPath, true))) {
            bufferedWriter.write(cardNumber);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

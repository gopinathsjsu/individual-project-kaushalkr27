package orders;

import inventory.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    static final String path = "src/main/java/orders/ordercsv/orders.csv";

    public Orders() {}

    public List<OrderItem> getOrderList(){
        List<OrderItem> orderItems = new ArrayList<>();

        String line = "";
        String splitBy = ",";
        int lineNumber = 1;
        String cardNumber = "";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Item> itemList = new ArrayList<>();

            while ((line = br.readLine()) != null){
                String[] item = line.split(splitBy);
                if(lineNumber!=1){
                    if(item.length > 2){
                        orderItems.add(new OrderItem(item[0].trim(), Integer.parseInt(item[1].trim()), item[2].trim()));
                        cardNumber = item[2].trim();
                    }else {
                        orderItems.add(new OrderItem(item[0].trim(), Integer.parseInt(item[1].trim()), cardNumber));
                    }
                }
                lineNumber++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return orderItems;
    }

    public static void main(String[] args) {
        Orders orders = new Orders();
        List<OrderItem> items = orders.getOrderList();
        System.out.println(items.toString());
    }
}

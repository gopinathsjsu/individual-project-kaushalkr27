package app;


import app.bills.Bill;
import inventory.FillUpStocks;
import inventory.Item;
import inventory.Stock;
import orders.OrderItem;
import orders.Orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Double> orderMap = new HashMap<String, Double>();
        FillUpStocks stockDB = new FillUpStocks();
        Orders orders = new Orders();

        Stock stock = stockDB.fillUpInventory();

        Map<String, Item> essentialsGoods = stock.getEssentials();
        Map<String, Item> luxuryGoods = stock.getLuxury();
        Map<String, Item> miscGoods = stock.getMiscellaneous();

        List<OrderItem> orderItems = orders.getOrderList();
        Bill totalBill = new Bill();

        boolean isOrderCorrect = true;

        for (OrderItem order : orderItems) {
            String item = order.getItem().toLowerCase();

            // search in essential
            if (essentialsGoods.containsKey(item)) {
                Item itemVal = essentialsGoods.get(item);
                Double total = totalBill.generateTotal(itemVal, order, 3);
                orderMap.put(order.getCardNumber(), orderMap.getOrDefault(order.getCardNumber(), 0.0) + total);
                if(total.equals(0.0)){
                    isOrderCorrect = false;
                    break;
                }
            } else if (luxuryGoods.containsKey(item)) {
                Item itemVal = luxuryGoods.get(item);
                Double total = totalBill.generateTotal(itemVal, order, 4);
                orderMap.put(order.getCardNumber(), orderMap.getOrDefault(order.getCardNumber(), 0.0) + total);
                if(total.equals(0.0)){
                    isOrderCorrect = false;
                    break;
                }
            } else if (miscGoods.containsKey(item)) {
                Item itemVal = miscGoods.get(item);
                Double total = totalBill.generateTotal(itemVal, order, 6);
                orderMap.put(order.getCardNumber(), orderMap.getOrDefault(order.getCardNumber(), 0.0) + total);
                if(total.equals(0.0)){
                    isOrderCorrect = false;
                    break;
                }
            }

        }

        for(Map.Entry<String, Double> entry : orderMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if(isOrderCorrect){
                for(OrderItem item: orderItems){
                    String out = item.getItem() + ", " + item.getQty();
                    totalBill.writeBill(String.format(out));
                }
                totalBill.writeBill(String.format("Amount paid, %s", value));
            }
            // do what you have to do here
            // In your case, another loop.
        }
    }
}
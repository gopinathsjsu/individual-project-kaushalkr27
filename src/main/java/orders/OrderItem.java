package orders;

public class OrderItem {
    private String item;
    private int qty;
    private String cardNumber;

    public OrderItem(String item, int qty, String cardNumber) {
        this.item = item;
        this.qty = qty;
        this.cardNumber = cardNumber;
    }

    public String getItem() {
        return item;
    }
    public int getQty() {
        return qty;
    }
    public String getCardNumber() {
        return cardNumber;
    }
}

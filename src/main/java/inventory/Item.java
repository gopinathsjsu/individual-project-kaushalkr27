package inventory;

public class Item {
    private String name;
    private Integer qty;
    private Double price;

    public Item(String name, Integer qty, Double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public Integer getQty() {
        return qty;
    }
    public Double getPrice() {
        return price;
    }

}

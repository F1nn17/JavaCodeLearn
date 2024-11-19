package practice.stream;

public class Order {
    private final String product;
    private final int price;

    public Order(String product, int price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }
}

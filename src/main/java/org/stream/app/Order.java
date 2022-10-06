package org.stream.app;

public class Order {

    private String id;
    private String product;
    private double amount;
    private int quantity;
    private double totalAmt;

    public Order() {}

    public Order(String id, String product, double amount, int quantity) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.quantity = quantity;
    }

    public Order(Order o, double totalAmt) {
        this.id = o.id;
        this.product = o.product;
        this.amount = o.amount;
        this.quantity = o.quantity;
        this.totalAmt = totalAmt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public double getTotalAmt() {
        return totalAmt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", totalAmt=" + totalAmt +
                '}';
    }
}

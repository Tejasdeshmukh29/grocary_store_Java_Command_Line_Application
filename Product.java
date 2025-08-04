
public class Product {

    private int quantity;
    private String nameProd;
    private int price;

    public Product(String nameProd, int price, int quantity) {
        this.nameProd = nameProd;
        this.price = price;
        this.quantity = quantity;
    }

    public int get_Quantity() {
        return quantity;
    }

    public String get_name_prod() {
        return nameProd;
    }

    public int get_price() {
        return price;
    }


    public void set_Price(int price) {
        this.price = price;
    }

    public void set_Quantity(int quantity) {
        this.quantity = quantity;
    }

    public void set_name_prod(String nameProd) {
        this.nameProd = nameProd;
    }
}
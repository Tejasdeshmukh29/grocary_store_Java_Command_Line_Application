class Product
{

private int Quantity;
private String name_prod;
private int price;

 public Product(String name_prod, int price, int Quantity)
 {
   this.name_prod = name_prod;
   this.price = price;
   this.Quantity= Quantity;
 }

 public int get_Quantity()
 {
    return Quantity;
 }

 public String get_name_prod()
 {
    return name_prod;
 }

 public int get_price()
 {
    return price;
 }

 public void set_price(int price)
 {
    this.price = price;
 }

 public void set_Quantity(int Quantity)
 {
    this.Quantity = Quantity;
 }

 public void set_name_prod(String name_prod)
 {
    this.name_prod = name_prod;
 }


 
}



      //   item[0] = "Apple";
      //   price[0] = 0.50f;
      //   quantity[0] = 5;
      //   item[1] = "Banana";
      //   price[1] = 0.30f;
      //   quantity[1] = 5;
      //   item[2] = "Bread";
      //   price[2] = 2.00f;
      //   quantity[2] = 5;
      //   item[3] = "Milk";
      //   price[3] = 1.50f;
      //   quantity[3] = 5;
      //   item[4] = "Eggs";
      //   price[4] = 2.50f;
      //   quantity[4] = 5;
      //   item[5] = "Cheese";
      //   price[5] = 3.00f;
      //   quantity[5] = 5;
      //   item[6] = "Chicken";
      //   price[6] = 5.00f;
      //   quantity[6] = 5;
      //   item[7] = "Rice";
      //   price[7] = 1.00f;
      //   quantity[7] = 5;
      //   item[8] = "Pasta";
      //   price[8] = 1.20f;
      //   quantity[8] = 5;
      //   item[9] = "Tomato";
      //   price[9] = 0.80f;
      //   quantity[9] = 5;
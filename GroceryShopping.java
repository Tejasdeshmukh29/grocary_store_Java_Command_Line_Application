import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class GroceryShopping {

    public static String[] item = new String[10];
    public static float[] price = new float[10];
    public static int[] quantity = new int[10];
    float totalbill = 0.0f;

    class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }

    class oprations {

        public float avgPrice() {
            float avg = 0;
            for (float i : price) {
                avg += i;
            }
            return avg / (float)price.length;
        }

        public void search(String product) {
            int index = -1;
            for (int i = 0; i < item.length; i++) {
                if (item[i].equalsIgnoreCase(product)) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println(" item not found !");
                return;
            }
            System.out.println(" your item is available at index : " + index);
        }

        public float discount() {
            if (totalbill > 100) {
                totalbill -= (totalbill / 100) * 10;
            }
            return totalbill;
        }

        public List<Integer> filter(float threshould) {
            List<Integer> filterList = new ArrayList<>();
            for (int i = 0; i < price.length; i++) {
                if (price[i] < threshould) {
                    filterList.add(i);
                }
            }
            return filterList;
        }

        public void addproduct() {
            Scanner sc = new Scanner(System.in);
                while (true) {
                    try {
                        System.out.println("Enter the name of Product or Enter Finish to end shoopping : ");
                        String product = sc.nextLine();

                        if (product.equalsIgnoreCase("finish")) {
                            System.out.println("Your total bill is " + totalbill + "$");
                            System.out.println("Thanku for shopping with us !");
                            break;
                        }

                        int itemIndex = -1;
                        for (int i = 0; i < item.length; i++) {
                            if (item[i].equalsIgnoreCase(product)) {
                                itemIndex = i;
                                break;
                            }
                        }

                        if (itemIndex == -1) {
                            throw new ItemNotFoundException("Item '" + product + "not found .please try again. ");
                        }

                        System.out.println(" Enter the quantity of produt : ");
                        int Quantity = sc.nextInt();
                        sc.nextLine();
                        if (quantity[itemIndex] >= Quantity) {
                            float itemPrice = price[itemIndex] * (float) Quantity;
                            quantity[itemIndex] -= Quantity;
                            totalbill += itemPrice;
                            System.out.println("added " + Quantity + " X " + item[itemIndex]
                                    + " to the bill , current total :" + totalbill + " $");
                        } else {
                            System.out.println("sorry ! for inconvinice product in that quantity is not available ");
                        }
                    } catch (ItemNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid input please try again ");
                        sc.nextLine();

                    }
                }
            }
        
        public void printavailableitem() {
            for (int i = 0; i < item.length; i++) {
                System.out.println("(" + (i + 1) + ")   " + item[i] + "    " + price[i] + "$/-" + " only " + quantity[i]
                        + " available ");
            }
        }

    }

    public static void main(String[] args) {

        item[0] = "Apple";
        price[0] = 0.50f;
        quantity[0] = 5;
        item[1] = "Banana";
        price[1] = 0.30f;
        quantity[1] = 5;
        item[2] = "Bread";
        price[2] = 2.00f;
        quantity[2] = 5;
        item[3] = "Milk";
        price[3] = 1.50f;
        quantity[3] = 5;
        item[4] = "Eggs";
        price[4] = 2.50f;
        quantity[4] = 5;
        item[5] = "Cheese";
        price[5] = 3.00f;
        quantity[5] = 5;
        item[6] = "Chicken";
        price[6] = 5.00f;
        quantity[6] = 5;
        item[7] = "Rice";
        price[7] = 1.00f;
        quantity[7] = 5;
        item[8] = "Pasta";
        price[8] = 1.20f;
        quantity[8] = 5;
        item[9] = "Tomato";
        price[9] = 0.80f;
        quantity[9] = 5;

        System.out.println("---------WELCOME TO OUR GROCERY STORE--------  \n       WE HAVE FOLLOWING ITEMS:");

        GroceryShopping gs = new GroceryShopping();
        GroceryShopping.oprations op = gs.new oprations(); 
        op.printavailableitem();
        Scanner sc = new Scanner(System.in);
      

        while (true) {
            System.out.println();
            System.out.println("--- press 1 to add item in cart");
            System.out.println("--- press 2 for search an item");
            System.out.println("--- press 3  to calculate avrage of products");
            System.out.println("--- press 4 to Filter Items Below a Certain Price");
            System.out.println("--- press 5 to get Total Bill");
            System.out.println("--- press 6 for checking available items");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    op.addproduct();
                    break;

                case 2:
                    System.out.println("Enter product name ");
                    sc.nextLine();
                    String prod = sc.nextLine();
                    op.search(prod);
                    break;

                case 3:
                    System.out.println("avrage prize of all products is : " + op.avgPrice());
                    break;

                case 4:
                    System.out.println("Enter amount to filter items which come below of this price : ");
                    float tresould = sc.nextInt();
                    List<Integer> sepratedItems = op.filter(tresould);
                    for (int i : sepratedItems) {
                        System.out.println(
                                "(" +( i + 1) + ")   " + item[i] + "    " + price[i] + "$/-" + " only " + quantity[i]
                                        + " available ");
                    }
                    break;

                case 5:
                    System.out.println(" your total bill is : " + op.discount());
                    break;

                case 6:
                    op.printavailableitem();
                    break;

                default:
                    break;
            }
        }
    }
}
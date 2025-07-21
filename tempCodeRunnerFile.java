import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            return avg / (float) price.length;
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

        public void printavailableitem(List<Product> prodlist) {
        for (Product product : prodlist) {
            System.out.println(product.get_name_prod() + " price = " + product.get_price() + " Quantity = "
                    + product.get_Quantity());
        }
        }

    }

    public static void main(String[] args) {

        List<Product> prodlist = new ArrayList<>();

        prodlist.add(new Product("apple", 100, 10));
        prodlist.add(new Product("banana", 100, 10));
        prodlist.add(new Product("kiwi", 100, 10));
        prodlist.add(new Product("pineapple", 100, 10));
        prodlist.add(new Product("chiku", 100, 10));
        System.out.println();
        System.out.println("---------WELCOME TO OUR GROCERY STORE--------  \n       WE HAVE FOLLOWING ITEMS:");
        System.out.println();


        GroceryShopping gs = new GroceryShopping();
        GroceryShopping.oprations op = gs.new oprations();
        op.printavailableitem(prodlist);
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
                                "(" + (i + 1) + ")   " + item[i] + "    " + price[i] + "$/-" + " only " + quantity[i]
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
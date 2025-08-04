import java.util.Scanner;

public class HomePage
{

    public static void main(String[] args) {
       
         Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println();
            System.out.println("---------------------------- Welcome to task managment System Choose option according to your need : -----------------------------");
            System.out.println();
            System.out.println("     --- 1. Grocery Shopping");
            System.out.println("     --- 2. To-Do List");
            System.out.println("     --- 3. Exit");
            System.out.println();

            int choice;
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1 -> {
                    System.out.println("You have chosen Grocery Shopping");
                    GroceryShopping groceryShopping = new GroceryShopping();
                 }

                case 2 -> {
                    System.out.println("You have chosen To-Do List");
                    To_Do_App todoApp = new To_Do_App();
                 }

                case 3 -> System.out.println("Exiting the Task Management System. Goodbye!");

                
            }
        }

    } 

}
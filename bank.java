import java.io.Reader;
import java.util.Scanner;

public class bank {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int pin = 28261024;
        int amount = 50000;
        System.out.println("-- WELCOME TO BANKING MANAGEMENT SYSTEM --");
        System.out.print("Enter your pin:");
        int p = sc.nextInt();
        if(p == pin)
        {
            System.out.println("Login Successfull");
        while (true) { 
            System.out.println("-- WELCOME TO BANKING MANAGEMENT SYSTEM --");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Check");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                System.out.print("Enter amount:");
                int am = sc.nextInt();
                amount += am;
                System.out.println("Amount Depsoited Successfully");
                break;
                case 2:
                System.out.print("Enter amount:");
                int wi = sc.nextInt();
                if(wi>amount)
                {
                    System.out.println("Insufficient Balance");
                }
                else{
                    amount -= wi;
                    System.out.println("Amount withdrawn Successfully");
                }
                break;
                case 3:
                System.out.println("Your balance is "+amount);
                break;
                case 4:
                System.out.println("Thank you for using the  banking management system");
                System.exit(0);
                break;
            }
        }
    }
        else{
            System.out.println("Invalid pin");
            System.out.println("Enter valid pin");
        }
    }
    }


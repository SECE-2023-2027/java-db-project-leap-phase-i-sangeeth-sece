import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private String name;
    private String accountNumber;
    private double amount;

    public Account(String name, String accountNumber, double amount) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void displayAccountDetails() {
        System.out.println("Account Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Amount: $" + amount);
    }
}

class AccountManager {
    private List<Account> accountList;

    public AccountManager() {
        accountList = new ArrayList<>();
    }

    public void createAccount(String name, String accountNumber, double amount) {
        Account account = new Account(name, accountNumber, amount);
        accountList.add(account);
        System.out.println("Account created successfully!");
    }

    // Method to display all accounts
    public void displayAccounts() {
        if (accountList.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            System.out.println("Account List:");
            for (Account account : accountList) {
                account.displayAccountDetails();
                System.out.println("------------------------");
            }
        }
    }
}

public class AccountMenuApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        int choice;

        do {
            // Menu display
            System.out.println("Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Display User List");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1: {
                    // Create Account
                    System.out.print("Enter Account Holder Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Initial Amount: ");
                    double amount = scanner.nextDouble();
                    accountManager.createAccount(name, accountNumber, amount);
                    break;
                }
                case 2: {
                    // Display User List
                    accountManager.displayAccounts();
                    break;
                }
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

        scanner.close();
    }
}
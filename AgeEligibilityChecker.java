import java.util.Scanner;

public class AgeEligibilityChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String name;
        System.out.println("Enter your name:");
        name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        switch (age >= 18 ? a : b) {
            case "a":
                System.out.println(name+" is eligible for voting.");
                break;
            case "b":
                System.out.println(name+" is not eligible for voting.");
                break;
        }

        scanner.close();
    }
}
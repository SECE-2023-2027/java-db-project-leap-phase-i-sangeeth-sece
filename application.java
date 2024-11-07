
import java.util.Scanner;

public class application {
    public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    int age;
    String name;
    System.out.println("Enter your name:");
    name = sc.nextLine();
    System.out.println("Enter your age:");
    age = reader.nextInt();
    if(age>=18)
    {
        System.out.println(name+" is eligible to vote");
        if(age>50)
        {
            System.out.print("You're a Senior citizen");
        }
    }
    else{
        System.out.println(name+" is not eligible to vote");
    }
   
    }
}

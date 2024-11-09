package com.org.Controller;
import com.org.service.*;
import com.org.Model.Bank;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class BankController{
	public static void main(String arg[]) {
		try {
			Bank n=new Bank();
			Service sc=new ServiceImplementation();
			n.db_Connect();
			System.out.println("Connection Successful...");
			boolean run=true;
			Scanner t=new Scanner(System.in);
			while(run) {
				System.out.println();
				System.out.println("1)Create Account");
				System.out.println("2)View Account");
				System.out.println("3)Update Account info");
				System.out.println("4)Deposit Amount");
				System.out.println("5)Withdraw amount");
				System.out.println("6)Transaction amount");
				System.out.println("7)View Transaction");
				System.out.println("8)Exit");
				System.out.println("Enter your choice:");
				int ch=t.nextInt();
				switch(ch) {
				case 1:
					sc.createaccount();
					break;
				case 2:
					sc.viewaccount();
					break;
				case 3:
					sc.updateaccount();
					break;
				case 4:
					sc.deposit();
					break;
				case 5:
					sc.withdraw();
					break;
				case 6:
					sc.amount_trans();
					break;
				case 7:
					sc.view_trans();
					break;
				case 8:
					run=false;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		} 
	}
}
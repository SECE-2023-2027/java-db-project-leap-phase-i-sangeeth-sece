package com.org.service;
import java.util.*;
import com.org.Model.Bank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class ServiceImplementation implements Service{
	Scanner t=new Scanner(System.in);
	@Override
	public void createaccount() {
		Connection con;
		try {
			con = Bank.db_Connect();
			System.out.println("Enter the customer ID:");
			int c_id=t.nextInt();
			System.out.println("Enter your account type:");
			System.out.println("1)Savings Account\n2)Current Account");
			int ch=t.nextInt();
			String type="";
			if(ch==1) {
				type="Savings";
			}
			else if(ch==2) {
				type="Current";
			}
			double bal=0.0
			System.out.println("Enter your mobile no:");
			String phn=t.next();
			System.out.println("Enter your address:");
			String addr=t.next();
			String sql="insert into Account(customer_id,account_type,balance,address,mobile) values (?,?,?,?,?)";
			PreparedStatement stmnt=con.prepareStatement(sql);
			stmnt.setInt(1,c_id);
			stmnt.setString(2,type);
			stmnt.setDouble(3,bal);
			stmnt.setString(4,addr);
			stmnt.setString(5,phn);
			stmnt.executeUpdate();
			System.out.println("Record inserted successfully");
			
			String query="select account_id from Account where customer_id=? and account_type=?";
			PreparedStatement stmnt1=con.prepareStatement(query);
			stmnt1.setInt(1, c_id);
			stmnt1.setString(2,type);
			ResultSet rs=stmnt1.executeQuery();
			int acc_id=rs.next() ? rs.getInt("account_id") : 0;
			
			if(type.equals("Savings")) {
				String query1="insert into savings_account(account_id,interest_rate) values(?,?)";
				stmnt1=con.prepareStatement(query1);
				stmnt1.setInt(1,acc_id);
				stmnt1.setDouble(2,5.0);
				stmnt1.executeUpdate();
			}
			else {
				String query2="insert into current_account(account_id,overdraft_limit) values(?,?)";
				stmnt1=con.prepareStatement(query2);
				stmnt1.setInt(1,acc_id);
				stmnt1.setDouble(2,0.05);
				stmnt1.executeUpdate();
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void viewaccount() {		
		try {
			Connection con=Bank.db_Connect();
			String sql="select * from account where account_id=?";
			PreparedStatement stmnt=con.prepareStatement(sql);
			System.out.println("Enter your account id:");
			int acc_id=t.nextInt();
			stmnt.setInt(1,acc_id);
			ResultSet rs=stmnt.executeQuery();
			if(rs.next()) {
				System.out.println("Account ID : "+rs.getInt("account_id"));
				System.out.println("Customer ID : "+rs.getInt("customer_id"));
				System.out.println("Account Type : "+rs.getString("account_type"));
				System.out.println("Balance Amount : "+rs.getDouble("balance"));	
			}
			else {
				System.out.println("Account ID not found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void updateaccount() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Enter the account ID to be updated:");
			int acc_id=t.nextInt();
			Connection con=Bank.db_Connect();
			String sql="update Account set address=?,mobile=? where account_id=?";
			System.out.println("Enter the updated mobile no:");
			String phn=t.next();
			System.out.println("Enter the updated address:");
			String addr=t.next();
			PreparedStatement stmnt=con.prepareStatement(sql);
			stmnt.setString(1, addr);
			stmnt.setString(2, phn);
			stmnt.setInt(3, acc_id);
			stmnt.executeUpdate();
			System.out.println("Account info updated successfully");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		try {
			Connection con=Bank.db_Connect();
			System.out.println("Enter account ID:");
			int acc_id=t.nextInt();
			System.out.println("Enter deposit amount:");
			double amnt=t.nextInt();
			String query="Update Account set balance=balance+? where account_id=?";
			PreparedStatement stmnt=con.prepareStatement(query);
			stmnt.setDouble(1, amnt);
			stmnt.setInt(2, acc_id);
			stmnt.executeUpdate();
			System.out.println("Amount deposited successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		try {
			Connection con=Bank.db_Connect();
			System.out.println("Enter account ID:");
			int acc_id=t.nextInt();
			System.out.println("Enter the amount to be withdrawn:");
			double amnt=t.nextDouble();
			String sql="select * from account where account_id=?";
			PreparedStatement stmnt1=con.prepareStatement(sql);
			stmnt1.setInt(1,acc_id);
			ResultSet rs=stmnt1.executeQuery();
			if (rs.next()) {  
				double bal=rs.getDouble("balance");
				if(bal>amnt) {
					String query="Update Account set balance=balance-? where account_id=?";
					PreparedStatement stmnt=con.prepareStatement(query);
					stmnt.setDouble(1, amnt);
					stmnt.setInt(2, acc_id);
					stmnt.executeUpdate();
					System.out.println("Amount withdrawn successfully");
				}
				else {
					System.out.println("Insufficient Balance");
				}
			}else {
				System.out.println("Account not found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void amount_trans() {
		// TODO Auto-generated method stub
		try {
			Connection con=Bank.db_Connect();
			System.out.println("Enter your account ID:");
			int myacc_id=t.nextInt();
			System.out.println("Enter the destination account ID:");
			int destacc_id=t.nextInt();
			System.out.println("Enter the amount to be transacted:");
			double amnt=t.nextDouble();
			String sql="select * from account where account_id=?";
			PreparedStatement stmnt1=con.prepareStatement(sql);
			stmnt1.setInt(1,myacc_id);
			ResultSet rs=stmnt1.executeQuery();
			if (rs.next()) {  
				double bal=rs.getDouble("balance");
				if(bal>amnt) {
					String query="Update Account set balance=balance-? where account_id=?";
					PreparedStatement stmnt=con.prepareStatement(query);
					stmnt.setDouble(1, amnt);
					stmnt.setInt(2, myacc_id);
					stmnt.executeUpdate();
					String query1="Update account set balance=balance+? where account_id=?";
					PreparedStatement stmnt2=con.prepareStatement(query1);
					stmnt2.setDouble(1, amnt);
					stmnt2.setInt(2, destacc_id);
					stmnt2.executeUpdate();
					System.out.println("Transaction successful");
					String query2="insert into Transaction(account_id,transaction_type,amount) values(?,?,?)";
					PreparedStatement stmnt3=con.prepareStatement(query2);
					stmnt3.setInt(1, myacc_id);
					stmnt3.setString(2,"Money Transfer");
					stmnt3.setDouble(3, amnt);
					stmnt3.executeUpdate();
				}
				else {
					System.out.println("Insufficient Balance");
				}
			}
			else {
				System.out.println("Account not found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void view_trans() {
		// TODO Auto-generated method stub
		
		try{
			Connection con=Bank.db_Connect();
			String sql="select * from transaction where account_id=?";
			PreparedStatement stmnt=con.prepareStatement(sql);
			System.out.println("Enter your account id:");
			int acc_id=t.nextInt();
			stmnt.setInt(1,acc_id);
			ResultSet rs=stmnt.executeQuery();
			if(rs.next()) {
				System.out.println("Account ID : "+rs.getInt("account_id"));
				System.out.println("Transaction ID : "+rs.getInt("transaction_id"));
				System.out.println("GTransaction Type : "+rs.getString("transaction_type"));
				System.out.println("Transaction Amount : "+rs.getDouble("amount"));
				System.out.println("Transaction date : "+rs.getString("transaction_date"));
			}
			else {
				System.out.println("Account ID not found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
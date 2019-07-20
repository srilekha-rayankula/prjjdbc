package com.bank.ui;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bank.exception.*;
import com.bank.services.BankService;


public class BankUI 
{

	private static Pattern pattern;
	private Matcher matcher;

	private static final String USERNAME_PATTERN = "^[a-z_]{3,15}$";
	static BankUI bankUi = new BankUI();
	Scanner scanner = new Scanner(System.in);

	BankService bankService= new BankService();

	String customer_name,phone_no,city;
	int balance;
	int pin,account_no, account_no_2, res2;
	static boolean res;
	
	byte a;
	String city_pattern = "^[a-z_]{3,15}$";
	Random rand = new Random();

	public static void main(String[] args) throws Exception {
		bankUi.chooseAny();
	}

	
	public void showMenu() 
	{
		System.out.println("\n1. Create Account ");
		System.out.println("2. Show Balance ");
		System.out.println("3. Deposit Amount");
		System.out.println("4. Withdraw Amount");
		System.out.println("5. Fund Transfer");
		System.out.println("6. Account Statement");
		System.out.println("7. Log out");
		System.out.println("Enter your choice :");
	}

	public void chooseAny() throws Exception
	{
		System.out.println("----------Welcome to UI Bank----------");
		int close=0;
		while (close==0) {

		
			showMenu();
			int input=scanner.nextInt();
			
			
			
			switch (input)
			{
			
			case 1: {
				
				res=false;
				while(res==false ) {
					System.out.println("Enter Your Full Name : ");
					customer_name=scanner.next();
					pattern = Pattern.compile(USERNAME_PATTERN);
					matcher = pattern.matcher(customer_name);
			
					if(matcher.matches()) {
							res=true;
					}else {
						res=false;
					}
				}
				
				
				res=false;
				while(res==false ) {
					System.out.println("Enter Your Phone No : ");
					phone_no=scanner.nextLine();
					phone_no+=scanner.next();
					res = bankService.validatePhoneNo(phone_no);
					if(res==true) {
						if(phone_no.matches("[6-9][0-9]{9}")) {
							res=true;
						}else {
							System.out.println("Incorrect format !!");
							res=false;
						}
						
					}else {
						System.out.println("Account No Already Registered!!");
					}
				}
				
				res=false;
				while(res==false ) {
					System.out.println("Enter your City : ");
					city=scanner.next();
					pattern = Pattern.compile(city_pattern);
					matcher = pattern.matcher(city);
			
					if(matcher.matches()) {
							res=true;
					}else {
						res=false;
					}
				}
				
				res=false;
				while(res==false ) {
					System.out.println("Enter Balance : ");
					balance=scanner.nextInt();
					
					if(balance>5000) {
							res=true;
					}else {
						res=false;
					}
				}
				
				
				res=false;
				while(res==false ) {
					System.out.println("Set your 4 digit pin : ");
					pin=scanner.nextInt();	
					pattern = Pattern.compile(USERNAME_PATTERN);
					matcher = pattern.matcher(customer_name);
			
					if(matcher.matches()) {
							res=true;
					}else {
						res=false;
					}
				}
				
				account_no=rand.nextInt(100000); ;
				res2 = bankService.setBankDetails(account_no, customer_name, balance, city, phone_no, pin);
				if(res2==1) 
					{
						System.out.println("Account successfully created !");
						System.out.println("Your Account No. is : "+account_no);
						System.out.println("Your opening balance is : "+balance);
					}else 
					{
						System.out.println("Account Creation Failed !!");
					}
			
			}
			
			break;
			
			case 2: 
				res=false;
				while(res==false ) {
					System.out.println("Enter Your Account No : ");
					account_no=scanner.nextInt();
					res = bankService.validateAccountNo(account_no);
					if(res==false) {
						balance = bankService.getBalance(account_no);
						System.out.println("Current Balance : "+balance);
						res=true;
					}else {
						System.out.println("Account No Not Found!!");
					}
				}
				break;

			case 3: 
			{
				System.out.println("Enter Your Account No : ");
				account_no=scanner.nextInt();
				System.out.println("Enter amount to be deposited : ");
				balance=scanner.nextInt();
				balance = bankService.DepositAmount(account_no, balance);
				System.out.println("Updated Balance : "+balance);
				break;
			}

			case 4 : 
			{
				System.out.println("Enter Your Account No : ");
				account_no=scanner.nextInt();
				res = bankService.validateAccountNo(account_no);
				if(res==false) 
				{
					System.out.println("Enter amount to be withdrawn : ");
					int balance1=scanner.nextInt();
					res = bankService.checkBalance(account_no,balance1);
						if(res==true) {
							int bal=bankService.WithdrawAmount(account_no, balance1);
							System.out.println("Updated Balance : "+bal);
						}else {
							throw new LowBalanceException();
						}
				}else {
					System.out.println("Account Not Found!!");
				}
			break;
			}

			case 5 :

				System.out.println("Enter Your Account No : ");
				account_no=scanner.nextInt();

				res = bankService.validateAccountNo(account_no);

				if(res==false) 
				{
					System.out.println("Enter account no. in which you want to transfer : ");
					account_no_2 = scanner.nextInt();

					res = bankService.validateAccountNo(account_no_2);

					if(res==false) 
					{
						System.out.println("Enter Amount : ");
						int amount = scanner.nextInt();
						res = bankService.checkBalance(account_no,amount);
						if(res==true) 
						{
							int fund_result = bankService.fundTransfer(account_no,account_no_2,amount);
							System.out.println("Updated Balance : "+fund_result );
						}else{
							System.out.println("Your balance is low!!");
						}
					}else 
					{
						System.out.println("Destination Account not found!!");
					}
				}else 
				{
					System.out.println("Source Account not found");
				}

				break;

			case 6 : 
				System.out.println("Enter Your Account No : ");
				account_no=scanner.nextInt();

				res = bankService.validateAccountNo(account_no);
				if(res==false) {

					String st=bankService.getTransaction(account_no);

					System.out.println("----------Account Statement----------\n");

					System.out.println(st);

				}else {
					System.out.println("Account not exist!");
				}
				break;

			case 7 :
				System.out.println("You have been successfully logged out");
				System.out.println("Do you want to exit ? press 0 to continue 1 to exit");
				close=scanner.nextInt();
				if(close==0) 
				{
					close=0;
				}else {
					System.exit(0);
				}

			default:{
				System.out.println("Please enter valid choice!");
				chooseAny();
			}
			}
			
	}}


}


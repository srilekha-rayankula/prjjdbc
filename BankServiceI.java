package com.bank.services;

import java.sql.SQLException;
import com.bank.exception.AccountNotExistException;


public interface BankServiceI {

	public int setBankDetails(int account_no, String customer_name, int balance, String city, String phone_no, int pin) throws Exception ;
		
	public int getBalance(int account_no) throws Exception ;
	
	public boolean validateAccountNo(int account_no) throws Exception ;

	public int DepositAmount(int account_no, int balance) throws Exception ;

	public int WithdrawAmount(int account_no, int balance) throws Exception ;

	public int fundTransfer(int account_no, int account_no_2, int amount) throws Exception ;

	public String getTransaction(int account_no) throws  AccountNotExistException, SQLException ;
	
	public boolean checkBalance(int account_no, int amount) throws Exception;

	public boolean validatePhoneNo(String phone_no) throws Exception ;
}


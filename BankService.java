package com.bank.services;

import java.sql.SQLException;
import java.util.Random;
import com.bank.bean.BankBean;
import com.bank.dao.BankDao;
import com.bank.exception.AccountNotExistException;
import com.bank.exception.LowBalanceException;

public class BankService {
	BankDao bankDao = new BankDao();
	boolean res;
	int balance;
	long uniqueID;
	Random rand = new Random();

	public int setBankDetails(int account_no, String customer_name, int balance, String city, String phone_no, int pin) throws Exception {
		BankBean bankBean = new BankBean();
		bankBean.setAccount_no(account_no);
		bankBean.setCustomer_name(customer_name);
		bankBean.setBalance(balance);
		bankBean.setPin(pin);
		bankBean.setCity(city);
		bankBean.setPhone_no(phone_no);

		int res = bankDao.setData(bankBean);
		return res;
	}

	public int getBalance(int account_no) throws Exception {
		
		balance =bankDao.getBalance(account_no);
		return balance;

	}

	public boolean validateAccountNo(int account_no) throws Exception {
		res = bankDao.validateAccountNo(account_no);
		if(res==false) {
			return true;
		}else {
			
			return false;
		}
	}

	public int DepositAmount(int account_no, int balance) throws Exception {
		int old_balance =bankDao.getBalance(account_no);
		balance = bankDao.DepositAmount(account_no,balance+old_balance);
		return balance;

	}

	public int WithdrawAmount(int account_no, int balance) throws Exception {
		int old_balance =bankDao.getBalance(account_no);
		if(old_balance<balance) {
			throw new LowBalanceException();
		}else {
		balance = bankDao.WithdrawAmount(account_no,old_balance-balance);
		System.out.println(balance);
		return  balance;
	}}

	public int fundTransfer(int account_no, int account_no_2, int amount) throws Exception {

		if(checkBalance(account_no, amount)) {
			int old_balance1 =bankDao.getBalance(account_no)-amount;
			int old_balance2 =bankDao.getBalance(account_no_2)+amount;
			balance = bankDao.fundTransfer(account_no, account_no_2,old_balance1,old_balance2);

		}else {
			throw new LowBalanceException();
		}
		return balance;

	}
	
	public String getTransaction(int account_no) throws  AccountNotExistException, SQLException {
		String st=bankDao.getTransaction(account_no);
		if(st!=null) {
			return st;
		}else {
			throw new AccountNotExistException();
		}
		
	}

	public boolean checkBalance(int account_no, int amount) throws Exception{

		if(bankDao.getBalance(account_no)>amount) {

			return true;
		}else {
			throw new LowBalanceException();
		}

	}

	public boolean validatePhoneNo(String phone_no) throws Exception {
		res = bankDao.validatePhoneNo(phone_no);
		if(res==false) {
			return true;
		}else {
			
			return false;
		}
	}

}

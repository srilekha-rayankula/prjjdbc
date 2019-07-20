package com.bank.dao;

import java.sql.SQLException;

import com.bank.bean.BankBean;

public interface BankDaoI {	

	public int setData(BankBean bankBean) throws Exception ;
	public int getBalance(int account_no) throws Exception ;
	public boolean validateAccountNo(int account_no) throws Exception ;
	public int DepositAmount(int account_no, int balance) throws SQLException ;
	public int WithdrawAmount(int account_no, int balance) throws SQLException ;
	public int fundTransfer(int account_no, int account_no_2, int amount1, int amount2) throws Exception ;
	public String getTransaction(int account_no) throws SQLException ;
	public boolean checkBalance(int account_no, int amount);
}

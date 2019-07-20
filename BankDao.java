package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import com.bank.bean.BankBean;


public class BankDao implements BankDaoI{

	BankBean bankBean = new BankBean();
	BankBean bankBean2 = new BankBean();
	int bal,bal2,res;
	String trans_type,trans_desc;
	long trans_id1,trans_id2;
	Random rand = new Random();
	Connection conn=null;
	PreparedStatement pstmt=null;

	public int setData(BankBean bankBean) throws Exception {
		String createAccount="insert into bank values (?,?,?,?,?,?)";
		conn=BankDB.getConnection();
		pstmt = conn.prepareStatement(createAccount);
		pstmt.setInt(1, bankBean.getAccount_no());
		pstmt.setString(2, bankBean.getCustomer_name());
		pstmt.setInt(3, bankBean.getBalance());
		pstmt.setString(4, bankBean.getCity());
		pstmt.setString(5, bankBean.getPhone_no());
		pstmt.setInt(6, bankBean.getPin());
		res=pstmt.executeUpdate();
		conn.close();
		return res;
	}

	public int getBalance(int account_no) throws Exception  {
		String checkBalance="select balance from bank where account_no=?";
		conn=BankDB.getConnection();
		pstmt = conn.prepareStatement(checkBalance);
		pstmt.setInt(1,account_no);
		ResultSet res=pstmt.executeQuery();
			if(res.next())
			{
				bal=res.getInt(1);
				return bal;
			}
			return bal;
		}
		
	public boolean validateAccountNo(int account_no) throws Exception {
		String checkAccount="select account_no from bank where account_no=?";
		conn=BankDB.getConnection();
		pstmt = conn.prepareStatement(checkAccount);
		pstmt.setInt(1,account_no);
		ResultSet resultSet =pstmt.executeQuery();
		if(resultSet.next()) {
			return true;
		}else {
			return false;
		}
	}

	public int DepositAmount(int account_no, int balance) throws SQLException {
		String depositAmount = "update bank set balance = ? where account_no = ? ";
		pstmt = conn.prepareStatement(depositAmount);
		pstmt.setInt(1, balance);
		pstmt.setInt(2, account_no);
		pstmt.executeUpdate(); 
		
		trans_id1 = rand.nextInt(100000); 
		trans_type="Deposite";
		trans_desc="Transaction ID : "+trans_id1+" Account Credited by : "+balance+" \n";
		
		String saveTrans = "insert into trans values (?,?,?,?)";
		pstmt = conn.prepareStatement(saveTrans);
		pstmt.setLong(1, trans_id1);
		pstmt.setString(2, trans_type);
		pstmt.setInt(3, account_no);
		pstmt.setString(4, trans_desc);
		pstmt.executeUpdate(); 
		
		return balance;
	}

	public int WithdrawAmount(int account_no, int balance) throws SQLException {

		String Q_update = "update bank set balance = ? where account_no = ? ";
		pstmt = conn.prepareStatement(Q_update);
		pstmt.setInt(1, balance);
		pstmt.setInt(2, account_no);
		pstmt.executeUpdate(); 
		
		trans_id1 = rand.nextInt(100000); 
		trans_type="Withdraw";
		trans_desc="Transaction ID : "+trans_id1+" Updated Balance : "+balance+" \n";
		
		String Q_Trans = "insert into trans values (?,?,?,?)";
		
		pstmt = conn.prepareStatement(Q_Trans);
		
		pstmt = conn.prepareStatement(Q_Trans);
		pstmt.setLong(1, trans_id1);
		pstmt.setString(2, trans_type);
		pstmt.setInt(3, account_no);
		pstmt.setString(4, trans_desc);
		pstmt.executeUpdate(); 

		return balance;
	}

	public int fundTransfer(int account_no, int account_no_2, int amount1, int amount2) throws Exception {

		String Q_update = "update bank set balance = ? where account_no = ? ";
		pstmt = conn.prepareStatement(Q_update);

		pstmt.setInt(1,amount1);
		pstmt.setInt(2, account_no); 
		pstmt.executeUpdate(); 
		bankBean.setBalance(amount1);
		bankBean2.setBalance(amount2);

		pstmt.setInt(1,amount2);
		pstmt.setInt(2, account_no_2); 
		pstmt.executeUpdate(); 

		trans_id1 = rand.nextInt(100000);
		trans_id2 = rand.nextInt(100000);
		
		String trans1="Amount Transferred to : "+account_no_2+" Updated Balance :"+amount1+" \n";
		String trans2="Amount Received from : "+account_no_2+" Updated Balance :"+amount2+" \n";

		String Q_Trans = "insert into trans values (?,?,?,?)";
		pstmt = conn.prepareStatement(Q_Trans);
		trans_type="Fund Transfer";

		pstmt = conn.prepareStatement(Q_Trans);
		pstmt.setLong(1, trans_id1);
		pstmt.setString(2, trans_type);
		pstmt.setInt(3, account_no);
		pstmt.setString(4, trans1);
		pstmt.executeUpdate(); 
		
		pstmt = conn.prepareStatement(Q_Trans);

		pstmt = conn.prepareStatement(Q_Trans);
		pstmt.setLong(1, trans_id2);
		pstmt.setString(2, trans_type);
		pstmt.setInt(3, account_no_2);
		pstmt.setString(4, trans2);
		pstmt.executeUpdate(); 
		
		int amt = getBalance(account_no);;
		return amt;

	}

	public String getTransaction(int account_no) throws SQLException {
		
		String getTrans = "select * from trans where account_id= ?";
		pstmt = conn.prepareStatement(getTrans);
		pstmt.setInt(1,account_no);
		ResultSet rs = pstmt.executeQuery(); 
		String str="";
		while(rs.next()) 
		{str=str+Integer.toString(rs.getInt(1))+"  "+	
		rs.getString(2) + "  "+	Integer.toString(rs.getInt(3)) +"  "+	
		rs.getString(4);}
		return str;
	}

	public boolean checkBalance(int account_no, int amount) {
		if(bankBean.getBalance()<amount) {
			return false;
		}else {
			return true;
		}

	}
	
	public boolean validatePhoneNo(String phone_no) throws Exception {
		String checkAccount="select phone_no from bank where phone_no=?";
		conn=BankDB.getConnection();
		pstmt = conn.prepareStatement(checkAccount);
		pstmt.setString(1,phone_no);
		ResultSet resultSet =pstmt.executeQuery();
		if(resultSet.next()) {
			return true;
		}else {
			return false;
		}
	}

}

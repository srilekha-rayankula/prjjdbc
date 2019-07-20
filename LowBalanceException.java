package com.bank.exception;

public class LowBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Insufficient Balance in your account";
	}

}

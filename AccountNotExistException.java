package com.bank.exception;

public class AccountNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Account No Found !!";
	}

}

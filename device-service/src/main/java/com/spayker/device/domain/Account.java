package com.spayker.device.domain;

import javax.validation.constraints.NotNull;

public class Account {

	@NotNull
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setPassword(String password) {
		this.accountId = accountId;
	}
}

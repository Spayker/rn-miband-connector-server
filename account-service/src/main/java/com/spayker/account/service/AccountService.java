package com.spayker.account.service;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.User;

public interface AccountService {

	Account findByName(String accountName);

	Account findById(String accountId);

	Account create(User user);

	void saveChanges(String name, Account update);
}

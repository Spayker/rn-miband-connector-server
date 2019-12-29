package com.spayker.device.service;

import com.spayker.device.domain.Account;
import com.spayker.device.domain.User;

public interface AccountService {

	Account findByName(String accountName);

	Account create(User user);

	void saveChanges(String name, Account update);
}

package com.spayker.account.repository;

import com.spayker.account.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository repository;

	@Test
	public void shouldFindAccountByName() {

		Account stub = getStubAccount();
		repository.save(stub);

		Account found = repository.findByName(stub.getName());
		assertEquals(stub.getLastSeen(), found.getLastSeen());
		assertEquals(stub.getNote(), found.getNote());
	}

	private Account getStubAccount() {

		Account account = new Account();
		account.setName("test");
		account.setNote("test note");
		account.setLastSeen(new Date());

		return account;
	}
}

package com.spayker.account.service;

import com.spayker.account.client.AuthServiceClient;
import com.spayker.account.domain.Account;
import com.spayker.account.domain.User;
import com.spayker.account.exception.AccountException;
import com.spayker.account.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceTest {

	@InjectMocks
	private AccountServiceImpl accountService;

	@Mock
	private AuthServiceClient authClient;

	@Mock
	private AccountRepository repository;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void shouldFindByName() {

		final Account account = Account.builder()
				.name("test")
				.build();

		when(accountService.findByName(account.getName())).thenReturn(account);
		Account found = accountService.findByName(account.getName());

		assertEquals(account, found);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNameIsEmpty() {
		accountService.findByName("");
	}

	@Test
	public void shouldCreateAccountWithGivenUser() {

		User user = new User();
		user.setUsername("test");

		Account account = accountService.create(user);

		assertEquals(user.getUsername(), account.getName());
		assertNotNull(account.getLastSeen());

		verify(authClient, times(1)).createUser(user);
		verify(repository, times(1)).save(account);
	}

	@Test
	public void shouldSaveChangesWhenUpdatedAccountGiven() {

		final Account update = Account.builder()
				.name("test")
				.lastSeen(new Date())
				.deviceIds(Collections.emptyList())
				.build();

		final Account account = Account.builder().build();

		when(accountService.findByName("test")).thenReturn(account);
		accountService.saveChanges("test", update);

		assertNotNull(account.getLastSeen());
		
		verify(repository, times(1)).save(account);
	}

	@Test(expected = AccountException.class)
	public void shouldFailWhenNoAccountsExistedWithGivenName() {
		final Account update = Account.builder().build();

		when(accountService.findByName("test")).thenReturn(null);
		accountService.saveChanges("test", update);
	}
}

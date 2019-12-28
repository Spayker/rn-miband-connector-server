package com.spayker.device.service;

import com.spayker.device.client.AuthServiceClient;
import com.spayker.device.domain.Device;
import com.spayker.device.domain.User;
import com.spayker.device.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service
public class DeviceServiceImpl implements DeviceService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuthServiceClient authClient;

	@Autowired
	private DeviceRepository repository;

	@Override
	public Device findByName(String accountName) {
		Assert.hasLength(accountName);
		return repository.findByName(accountName);
	}

	@Override
	public Device create(User user) {

		Device existing = repository.findByName(user.getUsername());
		Assert.isNull(existing, "account already exists: " + user.getUsername());

		authClient.createUser(user);
		Device account = new Device();
		account.setName(user.getUsername());
		account.setLastSeen(new Date());

		repository.save(account);

		log.info("new account has been created: " + account.getName());

		return account;
	}

	@Override
	public void saveChanges(String name, Device update) {

		Device device = repository.findByName(name);
		Assert.notNull(device, "can't find account with name " + name);

		device.setNote(update.getNote());
		device.setLastSeen(new Date());
		repository.save(device);

		log.debug("device {} changes has been saved", name);
	}
}

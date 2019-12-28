package com.spayker.device.service;

import com.spayker.device.domain.Device;
import com.spayker.device.domain.User;

public interface DeviceService {

	Device findByName(String accountName);

	Device create(User user);

	void saveChanges(String name, Device update);
}

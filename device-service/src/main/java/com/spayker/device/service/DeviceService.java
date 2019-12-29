package com.spayker.device.service;

import com.spayker.device.domain.Device;

public interface DeviceService {

	Device findByDeviceId(String deviceId);

	Device create(Device device);

	void saveChanges(Device update);
}

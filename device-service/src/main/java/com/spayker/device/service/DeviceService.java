package com.spayker.device.service;

import com.spayker.device.domain.Device;

import java.util.List;

public interface DeviceService {

	Device findByDeviceId(String deviceId);

	List<Device> findByUserId(String userId);

	Device create(Device device);

	void saveChanges(Device update);
}

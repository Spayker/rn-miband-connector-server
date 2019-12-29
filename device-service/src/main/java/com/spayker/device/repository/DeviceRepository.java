package com.spayker.device.repository;

import com.spayker.device.domain.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {

	Device findByDeviceId(String deviceId);

	List<Device> findByUserId(String userId);

}

package com.spayker.device.repository;

import com.spayker.device.domain.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {

	Device findByName(String name);

}

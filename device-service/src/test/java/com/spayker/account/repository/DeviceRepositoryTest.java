package com.spayker.device.repository;

import com.spayker.device.domain.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class DeviceRepositoryTest {

	@Autowired
	private DeviceRepository repository;

	@Test
	public void shouldFindDeviceByName() {

		Device stub = getStubDevice();
		repository.save(stub);

		Device found = repository.findByName(stub.getName());
		assertEquals(stub.getLastSeen(), found.getLastSeen());
		assertEquals(stub.getNote(), found.getNote());
	}

	private Device getStubDevice() {

		Device device = new Device();
		device.setName("test");
		device.setNote("test note");
		device.setLastSeen(new Date());

		return device;
	}
}

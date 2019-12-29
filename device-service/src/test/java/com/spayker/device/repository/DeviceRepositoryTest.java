package com.spayker.device.repository;

import com.spayker.device.domain.Device;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class DeviceRepositoryTest {

	@Autowired
	private DeviceRepository repository;

	@Test
	public void shouldFindDeviceById() {
		// given
		final Device stub = createStubDevice();

		// when
		repository.save(stub);

		// then
		Device found = repository.findByDeviceId(stub.getDeviceId());
		assertEquals(stub.getDeviceId(), found.getDeviceId());
		assertEquals(stub.getUserId(), found.getUserId());
		assertEquals(stub.getHrData(), found.getHrData());
		assertEquals(stub.getDate(), found.getDate());
		repository.delete(stub);
	}

	@Test
	public void shouldFindDeviceByUserId() {
		// given
		final int expectedFoundDevices = 1;
		final Device stub = createStubDevice();

		// when
		repository.save(stub);

		// then
		List<Device> foundDevices = repository.findByUserId(stub.getUserId());

		assertNotNull(foundDevices);
		assertFalse(foundDevices.isEmpty());
		assertEquals(expectedFoundDevices, foundDevices.size());

		Device foundDevice = foundDevices.get(0);
		assertNotNull(foundDevice);

		assertEquals(stub.getDeviceId(), foundDevice.getDeviceId());
		assertEquals(stub.getUserId(), foundDevice.getUserId());
		assertEquals(stub.getHrData(), foundDevice.getHrData());
		assertEquals(stub.getDate(), foundDevice.getDate());
		repository.delete(foundDevice);
	}

	private Device createStubDevice() {
		return Device.builder()
				.deviceId(RandomStringUtils.randomNumeric(10))
				.userId(RandomStringUtils.randomNumeric(10))
				.date(new Date())
				.hrData(RandomStringUtils.randomNumeric(2))
				.build();
	}
}

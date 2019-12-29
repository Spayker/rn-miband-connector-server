package com.spayker.device.repository;

import com.spayker.device.domain.Device;
import org.apache.commons.lang3.RandomStringUtils;
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
	public void shouldFindDeviceById() {
		// given
		final Device stub = createStubDevice();

		// when
		repository.save(stub);

		// then
		Device found = repository.findByDeviceId(stub.getDeviceId());
		assertEquals(stub.getDeviceId(), found.getDeviceId());
		assertEquals(stub.getHrData(), found.getHrData());
		assertEquals(stub.getDate(), found.getDate());
		repository.delete(stub);
	}

	private Device createStubDevice() {
		return Device.builder()
				.deviceId(RandomStringUtils.randomNumeric(10))
				.date(new Date())
				.hrData(RandomStringUtils.randomNumeric(2))
				.build();
	}
}

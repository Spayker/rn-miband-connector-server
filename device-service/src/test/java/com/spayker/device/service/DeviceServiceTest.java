package com.spayker.device.service;

import com.spayker.device.client.AccountServiceClient;
import com.spayker.device.domain.Device;
import com.spayker.device.repository.DeviceRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeviceServiceTest {

	@InjectMocks
	private DeviceServiceImpl deviceService;

	@Mock
	private DeviceRepository repository;

	@Mock
	private AccountServiceClient accountServiceClient;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void shouldFindDeviceById() {
		final Device device = Device.builder()
				.deviceId(RandomStringUtils.randomNumeric(10))
				.date(new Date().toString())
				.hrData(RandomStringUtils.randomNumeric(2))
				.build();

		when(deviceService.findByDeviceId(device.getDeviceId())).thenReturn(device);
		Device found = deviceService.findByDeviceId(device.getDeviceId());
		assertEquals(device, found);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNameIsEmpty() {
		deviceService.findByDeviceId("");
	}

	@Test
	public void shouldCreateDeviceWithGivenUser() {
		final int expectedCallTimes = 1;
		Device device = Device.builder()
				.userName(RandomStringUtils.randomAlphabetic(6))
				.deviceId(RandomStringUtils.randomNumeric(10))
				.hrData(RandomStringUtils.randomNumeric(10))
				.date(new Date().toString())
				.build();

		when(accountServiceClient.isAccountExist(anyString())).thenReturn(true);
		Device storedDevice = deviceService.create(device);

		assertEquals(storedDevice.getDeviceId(), device.getDeviceId());
		assertNotNull(storedDevice.getDate());
		verify(repository, times(expectedCallTimes)).save(device);
	}

	@Test
	public void shouldSaveChangesWhenUpdatedDeviceGiven() {
		final Device update = Device.builder()
				.deviceId("123123123")
				.date(new Date().toString())
				.hrData("99")
				.build();

		final Device device = getStubDevice();

		when(deviceService.findByDeviceId(update.getDeviceId())).thenReturn(device);
		deviceService.saveChanges(update);

		assertEquals(update.getDate(), device.getDate());
		assertEquals(update.getHrData(), device.getHrData());

		verify(repository, times(1)).save(device);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNoDevicesExistedWithGivenName() {
		final Device update = getStubDevice();

		when(deviceService.findByDeviceId("test")).thenReturn(null);
		deviceService.saveChanges(update);
	}

	private Device getStubDevice() {
		return Device.builder()
				.deviceId(RandomStringUtils.randomNumeric(10))
				.date(new Date().toString())
				.hrData(RandomStringUtils.randomNumeric(2))
				.build();
	}
}

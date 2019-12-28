package com.spayker.device.service;

import com.spayker.device.client.AuthServiceClient;
import com.spayker.device.domain.Device;
import com.spayker.device.domain.User;
import com.spayker.device.repository.DeviceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeviceServiceTest {

	@InjectMocks
	private DeviceServiceImpl deviceService;

	@Mock
	private AuthServiceClient authClient;

	@Mock
	private DeviceRepository repository;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void shouldFindByName() {

		final Device device = new Device();
		device.setName("test");

		when(deviceService.findByName(device.getName())).thenReturn(device);
		Device found = deviceService.findByName(device.getName());

		assertEquals(device, found);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNameIsEmpty() {
		deviceService.findByName("");
	}

	@Test
	public void shouldCreateDeviceWithGivenUser() {

		User user = new User();
		user.setUsername("test");

		Device device = deviceService.create(user);

		assertEquals(user.getUsername(), device.getName());
		assertNotNull(device.getLastSeen());

		verify(authClient, times(1)).createUser(user);
		verify(repository, times(1)).save(device);
	}

	@Test
	public void shouldSaveChangesWhenUpdatedDeviceGiven() {

		final Device update = new Device();
		update.setName("test");
		update.setNote("test note");

		final Device device = new Device();

		when(deviceService.findByName("test")).thenReturn(device);
		deviceService.saveChanges("test", update);

		assertEquals(update.getNote(), device.getNote());
		assertNotNull(device.getLastSeen());
		
		verify(repository, times(1)).save(device);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNoDevicesExistedWithGivenName() {
		final Device update = new Device();

		when(deviceService.findByName("test")).thenReturn(null);
		deviceService.saveChanges("test", update);
	}
}

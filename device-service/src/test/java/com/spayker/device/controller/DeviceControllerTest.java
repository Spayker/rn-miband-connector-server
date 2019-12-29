package com.spayker.device.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spayker.device.domain.Device;
import com.spayker.device.service.DeviceService;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private DeviceController deviceController;

	@Mock
	private DeviceService deviceService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
	}

//	@Test
//	public void shouldGetDeviceByName() throws Exception {
//
//		final Device device = new Device();
//		device.setName("test");
//
//		when(deviceService.findByName(device.getName())).thenReturn(device);
//
//		mockMvc.perform(get("/" + device.getName()))
//				.andExpect(jsonPath("$.name").value(device.getName()))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void shouldGetCurrentDevice() throws Exception {
//
//		final Device device = new Device();
//		device.setName("test");
//
//		when(deviceService.findByName(device.getName())).thenReturn(device);
//
//		mockMvc.perform(get("/current").principal(new UserPrincipal(device.getName())))
//				.andExpect(jsonPath("$.name").value(device.getName()))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void shouldSaveCurrentDevice() throws Exception {
//
//		final Device device = new Device();
//		device.setName("test");
//		device.setNote("test note");
//		device.setLastSeen(new Date());
//
//		String json = mapper.writeValueAsString(device);
//
//		mockMvc.perform(put("/current").principal(new UserPrincipal(device.getName())).contentType(MediaType.APPLICATION_JSON).content(json))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void shouldRegisterNewDevice() throws Exception {
//
//		final User user = new User();
//		user.setUsername("test");
//		user.setPassword("password");
//
//		String json = mapper.writeValueAsString(user);
//		System.out.println(json);
//		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void shouldFailOnValidationTryingToRegisterNewDevice() throws Exception {
//
//		final User user = new User();
//		user.setUsername("t");
//
//		String json = mapper.writeValueAsString(user);
//
//		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
//				.andExpect(status().isBadRequest());
//	}
}

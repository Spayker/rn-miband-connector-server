package com.spayker.device.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spayker.device.domain.Device;
import com.spayker.device.service.DeviceService;
import com.sun.security.auth.UserPrincipal;
import org.apache.commons.lang3.RandomStringUtils;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	@Test
	public void shouldGetDeviceById() throws Exception {

		final Device device = Device.builder()
				.deviceId(RandomStringUtils.randomNumeric(10))
				.hrData(RandomStringUtils.randomNumeric(2))
				.date(new Date().toString())
				.build();

		when(deviceService.findByDeviceId(device.getDeviceId())).thenReturn(device);

		mockMvc.perform(get("/device/" + device.getDeviceId()))
				.andExpect(jsonPath("$.deviceId").value(device.getDeviceId()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldRegisterNewDevice() throws Exception {

		final Device device = Device.builder()
				.userName("spayker")
				.hrData("0")
				.deviceId(RandomStringUtils.randomNumeric(10))
				.date(new Date().toString())
				.build();

		String json = mapper.writeValueAsString(device);
		System.out.println(json);
		mockMvc.perform(post("/")
				.principal(new UserPrincipal("spayker"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSaveCurrentDevice() throws Exception {

		final Device device = Device.builder()
				.deviceId(RandomStringUtils.randomNumeric(10))
				.userName(RandomStringUtils.randomNumeric(10))
				.hrData(RandomStringUtils.randomNumeric(2))
				.date(new Date().toString())
				.build();

		String json = mapper.writeValueAsString(device);

		mockMvc.perform(put("/device").principal(new UserPrincipal(device.getUserName())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToRegisterNewDevice() throws Exception {
		final Device device = null;
		String json = mapper.writeValueAsString(device);

		mockMvc.perform(post("/")
				.principal(new UserPrincipal("test"))
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}
}

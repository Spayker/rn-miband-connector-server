package com.spayker.device.client;

import com.spayker.device.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/mservicet/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void createUser(User user);

}

package com.spayker.device.client;

import com.spayker.device.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/mservicet/accounts", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void createUser(Account account);

}

package com.spayker.device.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/accounts/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	String getAccountByName(@PathVariable("name") String name);

}

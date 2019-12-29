package com.spayker.device.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	String getAccount(String accountId);

}

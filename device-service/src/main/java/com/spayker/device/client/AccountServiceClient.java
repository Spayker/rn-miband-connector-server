package com.spayker.device.client;

import com.spayker.device.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	Account getAccount(String accountId);

}

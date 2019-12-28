package com.spayker.device.controller;

import com.spayker.device.domain.Device;
import com.spayker.device.domain.User;
import com.spayker.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public Device getAccountByName(@PathVariable String name) {
		return deviceService.findByName(name);
	}

	@RequestMapping(path = "/current", method = RequestMethod.GET)
	public Device getCurrentAccount(Principal principal) {
		return deviceService.findByName(principal.getName());
	}

	@RequestMapping(path = "/current", method = RequestMethod.PUT)
	public void saveCurrentAccount(Principal principal, @Valid @RequestBody Device device) {
		deviceService.saveChanges(principal.getName(), device);
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Device createNewAccount(@Valid @RequestBody User user) {
		return deviceService.create(user);
	}
}

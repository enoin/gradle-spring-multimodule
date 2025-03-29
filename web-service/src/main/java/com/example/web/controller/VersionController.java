package com.example.web.controller;

import com.example.web.config.VersionProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	private final VersionProperties versionProperties;

	public VersionController(VersionProperties versionProperties) {
		this.versionProperties = versionProperties;
	}

	@GetMapping("/app/version")
	public String exportDocument() {
		return versionProperties.getVersion();
	}

}
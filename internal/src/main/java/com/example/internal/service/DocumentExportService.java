package com.example.internal.service;

import org.springframework.stereotype.Service;

@Service
public class DocumentExportService {

	public String export(final String doc) {
		return doc + "exported";
	}
}

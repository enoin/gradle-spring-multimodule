package com.example.web.controller;

import com.example.internal.service.DocumentExportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DocumentController {

	private final DocumentExportService documentExportService;

	public DocumentController(DocumentExportService documentExportService) {
		this.documentExportService = documentExportService;
	}

	@GetMapping("/document/export/{id}")
	public String exportDocument(final String doc) {
		return documentExportService.export(doc);
	}

}

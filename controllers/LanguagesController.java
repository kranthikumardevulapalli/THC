package com.thc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.thc.entity.Languages;
import com.thc.service.LanguageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class LanguagesController {

	@Autowired
	private LanguageService languageservice;

	@GetMapping("/languages")
	public ResponseEntity<List<Languages>> getAllLanguages() {
		List<Languages> langlist = languageservice.getAllLanguages();
		return new ResponseEntity<List<Languages>>(langlist, HttpStatus.OK);
	}

	@GetMapping("/languages/{id}")
	public ResponseEntity<Languages> getLanguagesById(@PathVariable("id") Integer id) {
		Languages languages = languageservice.getLanguagesById(id);
		return new ResponseEntity<Languages>(languages, HttpStatus.OK);
	}

	@PostMapping("/languages")
	public ResponseEntity<Void> addLanguage(@RequestBody Languages languages, UriComponentsBuilder builder) {
		boolean flag = languageservice.addLanguages(languages);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/languages/{id}").buildAndExpand(languages.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/languagesput")
	public ResponseEntity<Languages> updateLanguages(@RequestBody Languages languages) {
		languageservice.updateLanguages(languages);
		return new ResponseEntity<Languages>(languages, HttpStatus.OK);
	}

}

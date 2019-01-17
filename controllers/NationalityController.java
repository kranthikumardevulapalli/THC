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

import com.thc.entity.Nationality;
import com.thc.service.NationalityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v12/")
public class NationalityController {

	@Autowired
	private NationalityService nationalityService;

	@GetMapping("/nationality")
	public ResponseEntity<List<Nationality>> getAllNationality() {
		List<Nationality> nationlist = nationalityService.getAllLanguages();
		return new ResponseEntity<List<Nationality>>(nationlist, HttpStatus.OK);
	}

	@GetMapping("/nationality/{id}")
	public ResponseEntity<Nationality> getNationalityById(@PathVariable("id") Integer id) {
		Nationality nationbyid = nationalityService.getNationalityById(id);
		return new ResponseEntity<Nationality>(nationbyid, HttpStatus.OK);
	}

	@PostMapping("/nationality")
	public ResponseEntity<Void> addNationality(@RequestBody Nationality nationality, UriComponentsBuilder builder) {
		boolean flag = nationalityService.addNationality(nationality);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/languages/{id}").buildAndExpand(nationality.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/nationalityput")
	public ResponseEntity<Nationality> updateNationality(@RequestBody Nationality nationality) {
		nationalityService.updateNationality(nationality);
		return new ResponseEntity<Nationality>(nationality, HttpStatus.OK);
	}

}

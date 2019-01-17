package com.thc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.thc.entity.FamilyDiseases;
import com.thc.entity.FamilyHistoryTxn;
import com.thc.entity.FamilyHistoryTxnDtl;
import com.thc.service.FamilyHistoryDiseasesServices;
import com.thc.service.FamilyHistoryTxnDtlService;
import com.thc.service.FamilyHistoryTxnServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v13")
public class FamilyHistoryController {

	@Autowired
	private FamilyHistoryDiseasesServices familyDiseases;

	@Autowired
	private FamilyHistoryTxnServices familyTxnService;

	@Autowired
	private FamilyHistoryTxnDtlService familydtlsService;

	@GetMapping("/editfamilyhistorydiseases")
	public ResponseEntity<List<FamilyDiseases>> getAllDiseases() {
		List<FamilyDiseases> diseaseslist = familyDiseases.getAllDiseases();
		return new ResponseEntity<List<FamilyDiseases>>(diseaseslist, HttpStatus.OK);
	}

	@GetMapping("/editfamilyhistory/{mrno}")
	public ResponseEntity<FamilyHistoryTxn> getAllTxnByMrno(@PathVariable("mrno") String mrno) {
		FamilyHistoryTxn txnlist = familyTxnService.getByMrNo(mrno);
		return new ResponseEntity<FamilyHistoryTxn>(txnlist, HttpStatus.OK);
	}

	@RequestMapping(value = "/newfamilyhistorypost", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Void> addTxnDtls(@RequestBody FamilyHistoryTxn familyhistorytxn,
			UriComponentsBuilder builder) {
		FamilyHistoryTxn txn = new FamilyHistoryTxn();
		txn.setMedicalRecordNo(familyhistorytxn.getMedicalRecordNo());
		txn.setAdditionalNotes(familyhistorytxn.getAdditionalNotes());

		FamilyHistoryTxnDtl txndtl;

		List<FamilyHistoryTxnDtl> list = new ArrayList<>();

		for (int i = 0; i < familyhistorytxn.getFamilyHistoryTxnDtls().size(); i++) {

			txndtl = new FamilyHistoryTxnDtl();
			txndtl.setFamilyHistoryTxn(txn);
			txndtl.setBrother(familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getBrother());
			txndtl.setMother(familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getMother());
			txndtl.setFather((familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getFather()));
			txndtl.setSister((familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getSister()));
			txndtl.setMaternal((familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getMaternal()));
			txndtl.setFamilyDiseases((familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getFamilyDiseases()));
			txndtl.setBrother(familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getBrother());
			txndtl.setPaternal(familyhistorytxn.getFamilyHistoryTxnDtls().get(i).getPaternal());
			list.add(txndtl);
		}

		txn.setFamilyHistoryTxnDtls(list);

		familyTxnService.addTxn(txn);

		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/updatefamilyhistoryput", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<FamilyHistoryTxn> updateTxnDtls(@RequestBody FamilyHistoryTxn familyhistorytxns,
			UriComponentsBuilder builder) {

		familydtlsService.deleteByfamilyHistoryTxn(familyhistorytxns.getId());

		FamilyHistoryTxn txnData = new FamilyHistoryTxn();
		txnData.setId(familyhistorytxns.getId());
		txnData.setMedicalRecordNo(familyhistorytxns.getMedicalRecordNo());
		txnData.setAdditionalNotes(familyhistorytxns.getAdditionalNotes());

		FamilyHistoryTxnDtl txndtl;

		List<FamilyHistoryTxnDtl> list = new ArrayList<>();

		for (int i = 0; i < familyhistorytxns.getFamilyHistoryTxnDtls().size(); i++) {

			txndtl = new FamilyHistoryTxnDtl();
			txndtl.setFamilyHistoryTxn(txnData);
			txndtl.setBrother(familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getBrother());
			txndtl.setMother(familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getMother());
			txndtl.setFather((familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getFather()));
			txndtl.setSister((familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getSister()));
			txndtl.setMaternal((familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getMaternal()));
			txndtl.setFamilyDiseases((familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getFamilyDiseases()));
			txndtl.setBrother(familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getBrother());
			txndtl.setPaternal(familyhistorytxns.getFamilyHistoryTxnDtls().get(i).getPaternal());
			list.add(txndtl);
		}

		txnData.setFamilyHistoryTxnDtls(list);

		familyTxnService.updateFamilyHistoryTxn(txnData);

		return new ResponseEntity<FamilyHistoryTxn>(familyhistorytxns, HttpStatus.CREATED);
	}

}

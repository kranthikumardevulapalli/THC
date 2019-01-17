package com.thc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thc.entity.FamilyHistoryTxn;
import com.thc.repository.FamilyHistoryTxnDtlsRepository;
import com.thc.repository.FamilyHistoryTxnRepository;

@Service
public class FamilyHistoryTxnServices implements IFamilyHistoryTxn {

	@Autowired
	private FamilyHistoryTxnRepository familyhistorytxnRepository;
	@Autowired
	private FamilyHistoryTxnDtlsRepository familyhistorytxnDtlsRepository;
	
	@Override
	public FamilyHistoryTxn getByMrNo(String mrno) {
		FamilyHistoryTxn obj = familyhistorytxnRepository.getByMedicalRecordNo(mrno).get(0);
		return obj;
	}

	@Override
	public FamilyHistoryTxn addTxn(FamilyHistoryTxn familyhistoryTxn) {
		familyhistorytxnRepository.save(familyhistoryTxn);
		return familyhistoryTxn;
	 }

	@Override
	public FamilyHistoryTxn getAll() {
		return (FamilyHistoryTxn) familyhistorytxnRepository.findAll();
	}

	@Override
	public FamilyHistoryTxn getById(int id) {
		FamilyHistoryTxn txnData=familyhistorytxnRepository.findById(id).get(0);
		return txnData;
	}

	@Override
	public void updateFamilyHistoryTxn(FamilyHistoryTxn familyHistoryTxn) {
		familyhistorytxnRepository.save(familyHistoryTxn);
	}

	

	
}

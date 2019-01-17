package com.thc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thc.entity.FamilyHistoryTxnDtl;
import com.thc.repository.FamilyHistoryTxnDtlsRepository;

@Service
public class FamilyHistoryTxnDtlService implements IFamilyHistoryTxnDtls {

	@Autowired
	private FamilyHistoryTxnDtlsRepository familyhistorytxnDtlsRepository;

	@Override
	public void deleteByfamilyHistoryTxn(int id) {		
		List<FamilyHistoryTxnDtl> list = familyhistorytxnDtlsRepository.findByFamilyHistoryTxnId(id);
		familyhistorytxnDtlsRepository.deleteAll(list);
	}
}

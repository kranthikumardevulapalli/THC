package com.thc.service;

import com.thc.entity.FamilyHistoryTxn;


public interface IFamilyHistoryTxn {
	
	FamilyHistoryTxn getById(int id);
	
	FamilyHistoryTxn getByMrNo(String mrno);
	
	FamilyHistoryTxn addTxn(FamilyHistoryTxn familyhistoryTxn);
	
	FamilyHistoryTxn getAll();
	
	void updateFamilyHistoryTxn(FamilyHistoryTxn familyHistoryTxn);
	
	
}

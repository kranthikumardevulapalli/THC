package com.thc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thc.entity.FamilyDiseases;
import com.thc.repository.FamilyDiseasesRepository;

@Service
public class FamilyHistoryDiseasesServices implements IFamilyDiseasesServices {

	@Autowired
	private FamilyDiseasesRepository familyDiseasesRepository;

	@Override
	public List<FamilyDiseases> getAllDiseases() {
		List<FamilyDiseases> familyd=new ArrayList<>();
		familyDiseasesRepository.findAll().forEach(e->familyd.add(e));
		return familyd;
	}

}

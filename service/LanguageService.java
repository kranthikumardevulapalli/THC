package com.thc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thc.entity.Languages;
import com.thc.repository.LanguageRepository;

@Service
public class LanguageService implements ILnaguageService {

	@Autowired
	private LanguageRepository languagerepository;

	@Override
	public List<Languages> getAllLanguages() {
		List<Languages> langlist = new ArrayList<>();
		languagerepository.findAll().forEach(e -> langlist.add(e));
		return langlist;
	}

	@Override
	public Languages getLanguagesById(int id) {
		Languages obj = languagerepository.findById(id).get(0);
		return obj;
	}

	@Override
	public boolean addLanguages(Languages language) {
		List<Languages> langlist2 = languagerepository.findByNameAndActive(language.getName(), language.getActive());
		if (langlist2.size() > 0) {
			return false;
		} else {
			languagerepository.save(language);
			return true;
		}

	}

	@Override
	public void updateLanguages(Languages languages) {
		languagerepository.save(languages);
	}

	@Override
	public void deleteLanguages(int id) {
		languagerepository.delete(getLanguagesById(id));
	}

}

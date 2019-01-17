package com.thc.service;

import java.util.List;

import com.thc.entity.Languages;

public interface ILnaguageService {

	List<Languages> getAllLanguages();

	Languages getLanguagesById(int id);

	boolean addLanguages(Languages language);

	void updateLanguages(Languages languages);

	void deleteLanguages(int id);

}

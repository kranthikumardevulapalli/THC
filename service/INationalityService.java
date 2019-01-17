package com.thc.service;

import java.util.List;

import com.thc.entity.Nationality;

public interface INationalityService {
	List<Nationality> getAllLanguages();

	Nationality getNationalityById(int id);

	boolean addNationality(Nationality nationality);

	void updateNationality(Nationality nationality);

	void deleteNationality(int id);

}

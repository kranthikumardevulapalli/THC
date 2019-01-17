package com.thc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thc.entity.Nationality;
import com.thc.repository.NationalityRepository;
@Service
public class NationalityService implements INationalityService {

	@Autowired
	private NationalityRepository nationalityrepository;

	@Override
	public List<Nationality> getAllLanguages() {
		List<Nationality> nationlist = new ArrayList<>();
		nationalityrepository.findAll().forEach(e -> nationlist.add(e));
		return nationlist;
	}

	@Override
	public Nationality getNationalityById(int id) {
		Nationality obj = nationalityrepository.findById(id).get(0);
		return obj;
	}

	@Override
	public boolean addNationality(Nationality nationality) {
		List<Nationality> nationlist2 = nationalityrepository.findBynationNameAndActive(nationality.getNationName(),
				nationality.getActive());
		if (nationlist2.size() > 0) {
			return false;
		} else {
			nationalityrepository.save(nationality);
			return true;
		}
	}

	@Override
	public void updateNationality(Nationality nationality) {
		nationalityrepository.save(nationality);
	}

	@Override
	public void deleteNationality(int id) {
		nationalityrepository.delete(getNationalityById(id));
	}

}

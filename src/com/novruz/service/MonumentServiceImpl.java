package com.novruz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novruz.dao.MonumentDAO;
import com.novruz.entity.Monument;
import com.novruz.entity.MonumentPhotos;
import com.novruz.entity.MonumentTypes;

@Service
public class MonumentServiceImpl implements MonumentService {

	@Autowired
	private MonumentDAO monumentDAO;
	
	@Override
	@Transactional
	public List<Monument> getMonuments() {
		return monumentDAO.getCustomers();
	}

	@Override
	@Transactional
	public List<MonumentTypes> getMonumentTypes() {
		return monumentDAO.getMonumentTypes();
	}

	@Override
	@Transactional
	public List<Monument> getMonumentByName(String searchTerm) {
		return monumentDAO.getMonumentByName(searchTerm);
	}

	@Override
	@Transactional
	public List<String> getCities() {
		
		return monumentDAO.getCities();
	}

	@Override
	@Transactional
	public List<Integer> getCenturies() {
		
		return monumentDAO.getCenturies();
	}

	@Override
	@Transactional
	public List<Monument> getMonumentByCityTypeCenturyName(String city, String type, String century, String name) {
		
		return monumentDAO.getMonumentByCityTypeCenturyName(city, type, century, name);
	}

	@Override
	@Transactional
	public List<MonumentPhotos> getPhotosByMonumentId(int id) {
		
		return monumentDAO.getPhotosByMonumentId(id);
	}

	
	

}

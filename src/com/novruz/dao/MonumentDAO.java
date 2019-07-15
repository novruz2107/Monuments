package com.novruz.dao;

import java.util.List;

import com.novruz.entity.*;

public interface MonumentDAO {
	
	public List<Monument> getCustomers();

	public List<MonumentTypes> getMonumentTypes();

	public List<Monument> getMonumentByName(String searchTerm);

	public List<String> getCities();

	public List<Integer> getCenturies();
	
	public List<Monument> getMonumentByCityTypeCenturyName(String city, String type, String century, String name);

	public List<MonumentPhotos> getPhotosByMonumentId(int id);
}

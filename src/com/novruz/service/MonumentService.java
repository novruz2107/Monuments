package com.novruz.service;

import java.util.List;

import com.novruz.entity.*;

public interface MonumentService {
	
	public List<String> getCities();
	
	public List<Integer> getCenturies(); 

	public List<Monument> getMonuments();
	
	public List<MonumentTypes> getMonumentTypes();

	public List<Monument> getMonumentByName(String searchTerm);
	
	public List<Monument> getMonumentByCityTypeCenturyName(String city, String type, String century, String name);
	
	public List<MonumentPhotos> getPhotosByMonumentId(int id);

}

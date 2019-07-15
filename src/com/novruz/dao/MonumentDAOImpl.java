package com.novruz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.novruz.entity.Monument;
import com.novruz.entity.MonumentPhotos;
import com.novruz.entity.MonumentTypes;

@Repository
public class MonumentDAOImpl implements MonumentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Monument> getCustomers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Monument> query = currentSession.createQuery("from Monument", Monument.class);
		
		List<Monument> monuments = query.getResultList();
		return monuments;
	}

	@Override
	public List<MonumentTypes> getMonumentTypes() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<MonumentTypes> query = currentSession.createQuery("from MonumentTypes", MonumentTypes.class);
		
		List<MonumentTypes> monumentTypes = query.getResultList();
		return monumentTypes;
	}

	@Override
	public List<Monument> getMonumentByName(String searchTerm) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Monument> query = currentSession.createQuery("from Monument as m where m.name like '%"+searchTerm+"%'", Monument.class);
				
		List<Monument> monuments = query.getResultList();
		return monuments;
	}

	@Override
	public List<String> getCities() {
		Session currentSession =sessionFactory.getCurrentSession();
		Query<String> query = currentSession.createQuery("select distinct locateName from Monument");
		
		List<String> cities = query.getResultList();
		return cities;
	}

	@Override
	public List<Integer> getCenturies() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Integer> query = currentSession.createQuery("select distinct date1 from Monument order by date1");
		
		
		return query.getResultList();
	}
	
	public int getTypeIdByTypeName(String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Integer> query = currentSession.createQuery("select id from MonumentTypes as m where m.name = '"+name+"'");
		return query.uniqueResult();
	}

	@Override
	public List<Monument> getMonumentByCityTypeCenturyName(String city, String type, String century, String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		if(city=="") city="%"+city+"%";
		if(century=="") century="%"+century+"%";
		if(name==null) name="";
		if(type==null || type.equals("")) {
			type="%"+type+"%";
		}
		else {
			type = String.valueOf(getTypeIdByTypeName(type));
		}
				
		Query<Monument> query = currentSession.createQuery("from Monument as m where m.locateName like '"+city+"' and m.monumentTypeId like '"+type+"' and m.date1 like '"+century+"' and m.name like '%"+name+"%'");
		return query.getResultList();
	}

	@Override
	public List<MonumentPhotos> getPhotosByMonumentId(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<MonumentPhotos> query = currentSession.createQuery("from MonumentPhotos as m where m.monumentId like '" + String.valueOf(id) +"'");
		return query.getResultList();
	}

	
	
	

}

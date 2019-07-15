package com.novruz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="monuments_information")
public class Monument {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="monument_name")
	private String name;
	
	@Column(name="locate_name")
	private String locateName;
	
	@Column(name="x")
	private float x;
	
	@Column(name="y")
	private float y;
	
	@Column(name="monument_type_id")
	private int monumentTypeId;
	
	@Column(name="added_information")
	private String addedInformation;
	
	@Column(name="is_world_important")
	private int isWorldImportant;
	
	@Column(name="date1")
	private int date1;
	
	@Column(name="date2")
	private int date2;
	
	@Column(name="monument_created_history")
	private String monumentCreatedHistory;
	
	@Column(name="street_name")
	private String streetName;
	
	public Monument() {
		
	}
	
	public Monument(String name, String locateName, float x, float y, int monumentTypeId, String addedInformation,
			int isWorldImportant, int date1, int date2, String monumentCreatedHistory, String streetName) {
		super();
		this.name = name;
		this.locateName = locateName;
		this.x = x;
		this.y = y;
		this.monumentTypeId = monumentTypeId;
		this.addedInformation = addedInformation;
		this.isWorldImportant = isWorldImportant;
		this.date1 = date1;
		this.date2 = date2;
		this.monumentCreatedHistory = monumentCreatedHistory;
		this.streetName = streetName;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getMonumentTypeId() {
		return monumentTypeId;
	}

	public void setMonumentTypeId(int monumentTypeId) {
		this.monumentTypeId = monumentTypeId;
	}

	public String getAddedInformation() {
		return addedInformation;
	}

	public void setAddedInformation(String addedInformation) {
		this.addedInformation = addedInformation;
	}

	public int getIsWorldImportant() {
		return isWorldImportant;
	}

	public void setIsWorldImportant(int isWorldImportant) {
		this.isWorldImportant = isWorldImportant;
	}

	public int getDate1() {
		return date1;
	}

	public void setDate1(int date1) {
		this.date1 = date1;
	}

	public int getDate2() {
		return date2;
	}

	public void setDate2(int date2) {
		this.date2 = date2;
	}

	public String getLocateName() {
		return locateName;
	}

	public void setLocateName(String locateName) {
		this.locateName = locateName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonumentCreatedHistory() {
		return monumentCreatedHistory;
	}

	public void setMonumentCreatedHistory(String monumentCreatedHistory) {
		this.monumentCreatedHistory = monumentCreatedHistory;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	

}

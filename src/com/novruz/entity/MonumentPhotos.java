package com.novruz.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="monument_photos")
public class MonumentPhotos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="monument_id")
	private int monumentId;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] photo;
	
	public MonumentPhotos() {
		
	}

	public MonumentPhotos(int monumentId, byte[] photo) {
		super();
		this.monumentId = monumentId;
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonumentId() {
		return monumentId;
	}

	public void setMonumentId(int monumentId) {
		this.monumentId = monumentId;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	

}

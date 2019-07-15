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

import com.mysql.cj.jdbc.Blob;

@Entity
@Table(name="monument_types")
public class MonumentTypes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="type_name")
	private String name;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] photo;
	
	@Column(name="parent_id")
	private int parentId;
	
	public MonumentTypes() {
		
	}

	public MonumentTypes(String name, byte[] photo, int parentId) {
		super();
		this.name = name;
		this.photo = photo;
		this.parentId = parentId;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}

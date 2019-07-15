package com.novruz.helper;

public class ImagePath {
	
	private int id;
	
	private String path;
	private String name;
	

	public ImagePath(int id, String path, String name) {
		super();
		this.id = id;
		this.path = path;
		this.name = name;
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}

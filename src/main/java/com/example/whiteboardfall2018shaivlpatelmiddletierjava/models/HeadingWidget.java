package com.example.whiteboardfall2018shaivlpatelmiddletierjava.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HeadingWidget {
	@Id
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	

}

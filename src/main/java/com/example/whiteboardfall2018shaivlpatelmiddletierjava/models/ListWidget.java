package com.example.whiteboardfall2018shaivlpatelmiddletierjava.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ListWidget  {
	private String options;
	@Id
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
}

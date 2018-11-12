package com.example.whiteboardfall2018shaivlpatelmiddletierjava.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParagraphWidget {
	@Id
private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

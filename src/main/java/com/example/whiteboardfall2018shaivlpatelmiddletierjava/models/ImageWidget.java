package com.example.whiteboardfall2018shaivlpatelmiddletierjava.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ImageWidget {
	@Id
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}

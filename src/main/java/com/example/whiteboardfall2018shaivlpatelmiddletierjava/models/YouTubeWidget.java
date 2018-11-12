package com.example.whiteboardfall2018shaivlpatelmiddletierjava.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class YouTubeWidget {
	@Id
	private String src;
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
}

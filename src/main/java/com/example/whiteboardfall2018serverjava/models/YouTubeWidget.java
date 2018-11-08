package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class YouTubeWidget extends Widget {
	private String src;
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
}

/*
CREATE TABLE you_tube_widget (
  src varchar(255) DEFAULT NULL,
  id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES widget (id)
);
*/
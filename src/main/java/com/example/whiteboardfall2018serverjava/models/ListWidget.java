package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget {
	private String options;
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
}

/*
CREATE TABLE list_widget (
	options varchar(255) DEFAULT NULL,
	id int(11) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES widget (id)
);
*/
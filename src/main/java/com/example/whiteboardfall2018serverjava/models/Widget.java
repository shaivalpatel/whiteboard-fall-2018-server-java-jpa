package com.example.whiteboardfall2018serverjava.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String widgetType;
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	@ManyToOne()
	@JsonIgnore
	private Topic topic;
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Widget() {}
	public Widget(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}

/*

CREATE TABLE widget (
  dtype varchar(31) NOT NULL,
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(255) DEFAULT NULL,
  options varchar(255) DEFAULT NULL,
  src varchar(255) DEFAULT NULL,
  topic_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topic (id)
);

CREATE TABLE widget (
	id int(11) NOT NULL AUTO_INCREMENT,
	title varchar(255) DEFAULT NULL,
	topic_id int(11) DEFAULT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (topic_id) REFERENCES topic (id)
);
*/
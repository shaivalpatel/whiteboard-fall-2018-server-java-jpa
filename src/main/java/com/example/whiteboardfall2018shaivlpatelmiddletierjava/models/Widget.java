package com.example.whiteboardfall2018shaivlpatelmiddletierjava.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	private String title;
	private String Heading;
	private String Link;
	private String ListType;
	private String Text;
	private int WidgetOrder;
	private String WidgetType;
	
	public String getHeading() {
		return Heading;
	}
	public void setHeading(String heading) {
		Heading = heading;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public String getListType() {
		return ListType;
	}
	public void setListType(String listType) {
		ListType = listType;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public int getWidgetOrder() {
		return WidgetOrder;
	}
	public void setWidgetOrder(int widgetOrder) {
		WidgetOrder = widgetOrder;
	}
	public String getWidgetType() {
		return WidgetType;
	}
	public void setWidgetType(String widgetType) {
		WidgetType = widgetType;
	}
	@ManyToOne
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

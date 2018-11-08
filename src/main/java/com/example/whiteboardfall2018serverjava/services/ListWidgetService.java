package com.example.whiteboardfall2018serverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.ListWidget;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.ListWidgetRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;
import com.example.whiteboardfall2018serverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class ListWidgetService {
	WidgetRepository widgetRepository;
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ListWidgetRepository listWidgetRepository;
	@PostMapping("/api/topic/{topicId}/widget/list")
	public List<Widget> createListWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody ListWidget listWidget) {
		listWidget.setWidgetType("LIST");
		Topic topic = topicRepository.findById(topicId).get();
		listWidget.setTopic(topic);
		listWidget = listWidgetRepository.save(listWidget);
		return topicRepository.findById(topicId).get().getWidgets();
	}
	
	public List<Widget> findWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		System.out.println("hello");
		return (List<Widget>)
				topicRepository.findById(topicId)
				.get().getWidgets();
	}
	
	public List<Widget> deleteWidget(@PathVariable("topicId") int topicId, @PathVariable("widgetId") int id,
		     @RequestBody Widget newWidget){
		
		widgetRepository.deleteById(id);
		return findWidgetsForTopic(topicId);
		
	}
	
	
	@PutMapping("/api/widget/list")
	
	public Widget updateWidget(
	     @PathVariable("widgetId") int id,
	     @RequestBody ListWidget newWidget) {
	Widget widget = listWidgetRepository.findById(id).get()
	widget.setId(newWidget.getId());
	widget.setTitle(newWidget.getTitle());
	return widgetRepository.save(newWidget);}
}

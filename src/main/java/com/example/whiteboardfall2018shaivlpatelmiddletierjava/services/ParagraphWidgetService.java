package com.example.whiteboardfall2018shaivlpatelmiddletierjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Widget;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.TopicRepository;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.WidgetRepository;

public class ParagraphWidgetService {

	@Autowired
	UserService userService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	TopicRepository topicRepository;
	@GetMapping("/api/widget/list")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}

	@GetMapping("/api/widget/paragraph/{wid}")
	public Widget findWidgetById(@PathVariable ("wid") int wid) {
		return widgetRepository.findById(wid).get();

	}

	@GetMapping("api/topic/{tid}/paragraph/widget")
	public List<Widget> findWidgetForTopic(@PathVariable ("tid")int tid) {
		return (List<Widget>) topicRepository.findById(tid).get().getWidgets();
	}

	@DeleteMapping("/api/topic/{tid}/widget/paragraph/{wid}")
	public List<Widget> deleteWidget(@PathVariable ("wid") int wid, 
			@PathVariable ("tid") int tid) {
		widgetRepository.deleteById(wid);
		return topicRepository.findById(tid).get().getWidgets();

	}
	
	@PostMapping("/api/topic/{tid}/widget/paragraph/{wid}")
	public List<Widget> addWidget(@PathVariable ("wid") int wid, 
			@PathVariable ("tid") int tid,@RequestBody Widget widget){
		System.out.println(widget.getListType());
		widget.setTopic(topicRepository.findById(tid).get());
		widgetRepository.save(widget);
		System.out.println(topicRepository.findById(tid).get().getWidgets());
		return topicRepository.findById(tid).get().getWidgets();
	}
	

	@PutMapping("api/topic/{tid}/widget/paragraph/{wid}")
	public List<Widget> updateWidget(@PathVariable ("wid") int wid, 
			@PathVariable ("tid") int tid, @RequestBody Widget newWidget){
		Widget data = widgetRepository.findById(wid).get();
		if(data !=null) {
			Widget widget = data;
			widget.setHeading(newWidget.getHeading());
			widget.setLink(newWidget.getLink());
			widget.setListType(newWidget.getListType());
			widget.setText(newWidget.getText());
			widget.setTopic(newWidget.getTopic());
			widget.setWidgetOrder(newWidget.getWidgetOrder());
			widget.setWidgetType(newWidget.getWidgetType());
			return topicRepository.findById(tid).get().getWidgets();
		}
		else {
			return null;
		}
	}

}

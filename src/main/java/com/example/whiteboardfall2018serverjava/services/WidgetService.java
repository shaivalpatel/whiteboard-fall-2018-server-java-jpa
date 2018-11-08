package com.example.whiteboardfall2018serverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.Lesson;
import com.example.whiteboardfall2018serverjava.models.Module;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.User;
import com.example.whiteboardfall2018serverjava.models.Widget;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;
import com.example.whiteboardfall2018serverjava.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {
	@Autowired
	UserService userService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	TopicRepository topicRepository;
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		System.out.println("hello");
		return (List<Widget>)
				topicRepository.findById(topicId)
				.get().getWidgets();
	}
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget")
	
	@PutMapping("/api/widget")
	
	public Widget updateWidget(
	     @PathVariable("widgetId") int id,
	     @RequestBody Widget newWidget) {
	Widget widget = widgetRepository.findById(id).get();
	widget.setId(newWidget.getId());
	widget.setTitle(newWidget.getTitle());
	return widgetRepository.save(newWidget);}
	
	
	@DeleteMapping("/api/widget")
	public List<Widget> deleteWidget(@PathVariable("topicId") int topicId, @PathVariable("widgetId") int id,
		     @RequestBody Widget newWidget){
		widgetRepository.deleteById(id);
		return findWidgetsForTopic(topicId);
	}

	@GetMapping("/api/user/{userId}/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
	public List<Widget> findWidgetForTopic(
			@PathVariable("userId") int userId,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId,
			@PathVariable("topicId") int topicId) {
		User user = userService.findUserById(userId);
		for(Course course: user.getCourses()) {
			if(course.getId() == courseId) {
				for(Module module: course.getModules()) {
					if(module.getId() == moduleId) {
						for(Lesson lesson: module.getLessons()) {
							if(lesson.getId() == lessonId) {
								for(Topic topic: lesson.getTopics()) {
									if(topic.getId() == topicId) {
										return topic.getWidgets();
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
}

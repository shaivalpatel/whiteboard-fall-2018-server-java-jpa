package com.example.whiteboardfall2018serverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.Lesson;
import com.example.whiteboardfall2018serverjava.models.Module;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.User;
import com.example.whiteboardfall2018serverjava.repositories.LessonRepository;
import com.example.whiteboardfall2018serverjava.repositories.TopicRepository;

@RestController
@CrossOrigin(origins="*")
public class TopicService {
	@Autowired
	UserService userService;
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	LessonRepository lessonRepository;
	@GetMapping("/api/lesson/{lessonId}/topic")
	public List<Topic> findTopicsForLesson(@PathVariable("lessonId") int lessonId) {
		return (List<Topic>) lessonRepository.findById(lessonId).get().getTopics();
	}
	@GetMapping("/api/topic")
	public List<Topic> findAllTopics() {
		System.out.println("hello");
		return (List<Topic>) topicRepository.findAll();
	}
	@GetMapping("/api/user/{userId}/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public List<Topic> findTopicsForLessonId(
			@PathVariable("userId") int userId,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId) {
		User user = userService.findUserById(userId);
		for(Course course: user.getCourses()) {
			if(course.getId() == courseId) {
				for(Module module: course.getModules()) {
					if(module.getId() == moduleId) {
						for(Lesson lesson: module.getLessons()) {
							if(lesson.getId() == lessonId) {
								return lesson.getTopics();
							}
						}
					}
				}
			}
		}
		return null;
	}
}

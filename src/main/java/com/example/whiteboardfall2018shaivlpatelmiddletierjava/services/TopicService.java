package com.example.whiteboardfall2018shaivlpatelmiddletierjava.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Course;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Lesson;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Module;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Topic;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.User;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.LessonRepository;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.TopicRepository;



@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class TopicService {
	@Autowired
	UserService userService;
	
	@Autowired
	TopicRepository topicRepository; 
	
	@Autowired
	LessonRepository lessonRepository;
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lid}/topic")
	public List<Topic> findTopicsForLessonId(
			HttpSession session,
			@PathVariable("lid") int lid) {
			return lessonRepository.findById(lid).get().getTopics();
	}

	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lid}/topic")
	public List<Topic> createTopic(
			HttpSession session,
			
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lid") int lid,
			@RequestBody Topic topic) {
		topicRepository.save(topic);
		return lessonRepository.findById(moduleId).get().getTopics();
		}

	@GetMapping("/api/user/{userId}/course/{courseId}/module/{moduleId}/lesson/{lid}/topic/{tid}")
	public Topic findTopicById(
			@PathVariable("userId") int userId,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lid") int lid,
			@PathVariable("tid") int tid,
			@RequestBody Topic topic) {
		return topicRepository.findById(tid).get();
	}
	
	@PutMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lid}/topic/{tid}")
	public List<Topic> updateTopic(
			HttpSession session,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lid") int lid,
			@PathVariable("tid") int tid,
			@RequestBody Topic topic) {
		Topic oldTopic = topicRepository.findById(tid).get();
		
		oldTopic.setTitle(topic.getTitle());
		topicRepository.save(oldTopic);
		return lessonRepository.findById(lid).get().getTopics();
	}

	
	@DeleteMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lid}/topic/{tid}")
	public List<Topic> deleteTopic(
			HttpSession session,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lid") int lid,
			@PathVariable("tid") int tid) {
		topicRepository.deleteById(tid);
		return lessonRepository.findById(lid).get().getTopics();
	}

}

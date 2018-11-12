package com.example.whiteboardfall2018shaivlpatelmiddletierjava.services;

import java.util.ArrayList;
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
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.CourseRepository;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.LessonRepository;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.ModuleRepository;





@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class LessonService {
	@Autowired
	UserService userService;
	List<Lesson> lessons = new ArrayList<Lesson>();
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findLessonsForCourseId(
			HttpSession session,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId) {
		System.out.println("hello");
		User currentUser = (User)session.getAttribute("currentUser");
		for(Course course: currentUser.getCourses()) {
			if(course.getId() == courseId) {
				for(Module module: course.getModules()) {
					if(module.getId() == moduleId) {
						lessons =module.getLessons();
						return module.getLessons();
					}
				}
			}
		}
		return null;
	}

	@GetMapping("/api/user/{userId}/course/{courseId}/module/{moduleId}/lesson/{lid}")
	public List<Lesson> findLessonsForCourseId(
			@PathVariable("userId") int userId,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lid") int lid) {
		 return moduleRepository.findById(moduleId).get().getLessons();
	}

	@PutMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lid}")
	public List<Lesson> updatelesson(
			HttpSession session,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lid") int lid,
			@RequestBody Lesson  lesson) {
		Lesson oldLesson=lessonRepository.findById(lid).get();
		oldLesson.setTitle(lesson.getTitle());
		lessonRepository.save(oldLesson);
		return moduleRepository.findById(moduleId).get().getLessons();
	}


	@DeleteMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lid}")
	public List<Lesson> deletelesson(
			HttpSession session,

			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lid") int lid) {
		
		lessonRepository.deleteById(lid);
		return moduleRepository.findById(moduleId).get().getLessons();
		
	}


	@PostMapping ("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> createLesson(HttpSession session,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,@RequestBody Lesson lesson){
		lessonRepository.save(lesson);
		return moduleRepository.findById(moduleId).get().getLessons();
		}
}

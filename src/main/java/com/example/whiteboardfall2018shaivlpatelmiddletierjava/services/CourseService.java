package com.example.whiteboardfall2018shaivlpatelmiddletierjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Course;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.User;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.CourseRepository;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.UserRepository;



@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	int id,cid;
	List<Course> courses = null;
	@GetMapping("/api/user/{userId}/course")
	public List<Course> findAllCourses(@PathVariable("userId") int userId) {
		User user = userService.findUserById(userId);
	    return user.getCourses();
	}
	
	
	@PutMapping("/api/{userId}/course/{cid}")
	public List<Course> updateCourse(
			@PathVariable ("cid") int courseId,
			@PathVariable ("userId") int userId,
			@RequestBody Course newCourse){
		
		Course old =courseRepository.findById(cid).get();
		old.setTitle(newCourse.getTitle());
		courseRepository.save(old);
		return userRepository.findById(userId).get().getCourses();
	}
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourse(){
		return courseRepository.findAll();
	}
	
	@RequestMapping(value="/api/user/{userId}/course/{cid}",method = RequestMethod.DELETE)
	public List<Course> deleteCourse(
			@PathVariable ("userId") int userId,
			@PathVariable ("cid") int cid){
		User user= courseRepository.findById(cid).get().getUser();
		courseRepository.deleteById(cid);
		return user.getCourses();
	}
	
	@PostMapping("/api/user/{userId}/course")
	public List<Course> createCourse(
			@PathVariable("userId") int userId,
			@RequestBody Course course) {
		courseRepository.save(course);
		return userRepository.findById(userId).get().getCourses();
	}
	
	public Course findCourseById(int courseId) {
		return courseRepository.findById(courseId).get();
	}
	
	}

package com.example.whiteboardfall2018serverjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.User;

@RestController
@CrossOrigin(origins = "*")
public class CourseService {

	@Autowired
	UserService userService;

	List<Course> courses = null;
	@GetMapping("/api/user/{userId}/course")
	public List<Course> findAllCourses(@PathVariable("userId") int userId) {
		User user = userService.findUserById(userId);
	    return user.getCourses();
	}

	@PostMapping("/api/user/{userId}/course")
	public List<Course> createCourse(
			@PathVariable("userId") int userId,
			@RequestBody Course course) {
		User user = userService.findUserById(userId);
		user.getCourses().add(course);
		return user.getCourses();
	}

	public Course findCourseById(int courseId) {
		List<User> users = userService.findAllUsers();
		for(User user: users) {
			List<Course> courses = user.getCourses();
			for(Course course: courses) {
				if(course.getId() == courseId)
					return course;
			}
		}
		return null;
	}
}

package com.example.whiteboardfall2018shaivlpatelmiddletierjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Course;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Lesson;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Module;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Topic;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.User;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Widget;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.UserRepository;


@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class UserService {


	@Autowired 
	UserRepository userRepository;
	HttpSession maintainSession;
	static List<User> users = new ArrayList<User>();
	static String[] usernames    = {"alice", "bob", "charlie"};
	static String[] courseTitles = {"cs5200", "cs5610", "cs5500"};
	static String[] moduleTitles = {"Module 1", "Module 2"};
	static String[] lessonTitles = {"lesson 1", "lesson 2"};
	static String[] topicTitles = {"topic 1", "topic 2"};
	static String[] widgetTitles = {"widget 1", "widget 2"};
	{
		List<Widget> widgets = new ArrayList<Widget>();
		for(String widgetTitle : widgetTitles) {
			widgets.add(new Widget(widgetTitle));
		}
		List<Topic> topics = new ArrayList<Topic>();
		for(String topicTitle : topicTitles) {
			Topic topic = new Topic(topicTitle);
			if(topicTitle.equals("topic 1")) {
				topic.setWidgets(widgets);
			}
			topics.add(topic);
		}
		List<Lesson> lessons = new ArrayList<Lesson>();
		for(String lessonTitle : lessonTitles) {
			Lesson lesson = new Lesson(lessonTitle);
			if(lessonTitle.equals("lesson 1")) {
				lesson.setTopics(topics);
			}
			lessons.add(lesson);
		}
		List<Module> modules = new ArrayList<Module>();
		for(String moduleTitle: moduleTitles) {
			Module module = new Module(moduleTitle);
			if(moduleTitle.equals("Module 1")) {
				module.setLessons(lessons);
			}
			modules.add(module);
		}
		List<Course> courses = new ArrayList<Course>();
		for(String courseTitle: courseTitles) {
			Course course = new Course(courseTitle);
			if(courseTitle.equals("cs5200")) {
				course.setModules(modules);
			}
			courses.add(course);
		}
		for(String username: usernames) {
			User user = new User(username);
			if(username.equals("alice")) {
				user.setCourses(courses);
			}
			users.add(user);
		}
	}

	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>)userRepository.findAll();
	}

	public User findUserById(int userId) {
		return userRepository.findById(userId).get();
	}

	@PostMapping("/api/user")
	public List<User> createUser(@RequestBody User user) {
		userRepository.save(user);
		return (List<User>)userRepository.findAll();
	}

	@PostMapping("/api/register")
	public User register(
			@RequestBody User user,
			HttpSession session) {
		session.setAttribute("currentUser", user);
		maintainSession =session;
		userRepository.save(user);
		return user;
	}

	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		System.out.println("helo");
		User currentUser = (User)maintainSession.getAttribute("currentUser");

		return (User)userRepository.findById(currentUser.getId()).get();

	}

	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@PostMapping("/api/login")
	public User login(
			@RequestBody User credentials,
			HttpSession session) 
	{

		String username = credentials.getUsername();
		String pass = credentials.getPassword();
		System.out.println(userRepository.findByusername(username).size() == 1);
		if(userRepository.findByusername(username).size() == 1)
		{
			User dbUser = userRepository.findByusername(username).get(0);
			if(dbUser.getPassword().equals(pass))
			{
				System.out.println("password");
				session.setAttribute("currentUser", dbUser);
				maintainSession=session;
				return dbUser;
			}
			else {
				return null;
			}

		}

		else {


			return null;
		}
		
	}




}

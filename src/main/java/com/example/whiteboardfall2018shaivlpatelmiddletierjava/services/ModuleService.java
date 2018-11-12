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
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.Module;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.User;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.CourseRepository;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.ModuleRepository;
import com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories.UserRepository;


@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" , allowedHeaders = "*")
public class ModuleService {
	int uid;
	int cid;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseService courseService;
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findModulesForCourse(
			@PathVariable("courseId") int courseId,
			HttpSession session) {
		
		return  courseRepository.findById(courseId).get().getModules();
		
		
				
	}
	@DeleteMapping ("/api/course/{cid}/module/{mid}")
	public List<Module> deleteModule(@PathVariable ("mid") int mid,
			@PathVariable ("cid") int cid,
			HttpSession session){
		
		
		moduleRepository.deleteById(mid);
		return courseRepository.findById(cid).get().getModules();
		
		
	}
	
	@PutMapping ("/api/course/{cid}/module/{mid}")
	public List<Module> updateModule(@PathVariable ("mid") int mid,
			@PathVariable ("cid") int cid,
			HttpSession session,
			@RequestBody Module module){
		Module oldMOdule=moduleRepository.findById(mid).get();
		oldMOdule.setTitle(module.getTitle());
		moduleRepository.save(oldMOdule);
		return courseRepository.findById(cid).get().getModules();
		
	}
	@PostMapping("/api/course/{cid}/module")
	public List<Module> createModule(@PathVariable ("cid") int cid,HttpSession session,@RequestBody Module module){
		module.setCourse(courseRepository.findById(cid).get());
		moduleRepository.save(module);
		
		List<Module> moduleList = courseRepository.findById(cid).get().getModules();
		return moduleList;
		
	}
	
	
	@GetMapping("/api/course/{cid}/module/{mid}")
	public Module findModuleById(@PathVariable ("mid") int mid,
			@PathVariable ("cid") int cid,
			HttpSession session) {
		return moduleRepository.findById(mid).get();
		}
		
	





}

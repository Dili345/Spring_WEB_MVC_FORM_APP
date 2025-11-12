package com.jtcindia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jtcindia.binding.Student;
import com.jtcindia.entity.StudentEntity;
import com.jtcindia.repo.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository repo;
	
	
	

	// method to load Student form
	@GetMapping("/")
	public String loadForm(Model model) {

		LoadformDate(model);
 
		return "index";
	}

	private void LoadformDate(Model model) {
		List<String> coursesList = new ArrayList();
		coursesList.add("Java");
		coursesList.add("DevOps");
		coursesList.add("AWS");
		coursesList.add("Python");

		List<String> timingsList = new ArrayList();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");

		// Courses and Timings Data I want to send to UI

		// To Send this Data to UI useing model.addAttributes().

		Student student = new Student();// This is from binding obj.
		// Sending form Binding obj. to the UI by using model,
		// model.addAttribute(Key,Value).
		model.addAttribute("courses", coursesList); // Sending Courses
		model.addAttribute("timeings", timingsList); // Sending Timing
		model.addAttribute("student", student); // Sending Student obj.
	}

	// method is save Student from data

	@PostMapping("/save")
	public String handleSubmit(Student s, Model model) {
	 

		// Logic to Save
		
		StudentEntity entity = new StudentEntity();
		
		//copy date from binding obj. to entity obj.
		
		BeanUtils.copyProperties(s, entity);
		entity.setTimings(Arrays.toString(s.getTimings()));
		
		repo.save(entity);

		model.addAttribute("mssg", "Student Saved");

		LoadformDate(model);

		return "index";

	}

	// method to display saaved a students data

}

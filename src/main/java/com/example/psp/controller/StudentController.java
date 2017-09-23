package com.example.psp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.psp.model.StudentModel;
import com.example.psp.service.InMemoryStudentService;
import com.example.psp.service.StudentService;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value="name", required=true) String name,
			@RequestParam(value="npm", required=true) String npm,
			@RequestParam(value="gpa", required=true) double gpa) {
		StudentModel student = new StudentModel(name, npm, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	@RequestMapping(value= {"/student/delete/{npm}", "/student/delete"})
	public String delete(Model model, @PathVariable Optional<String> npm) {
		if(npm.isPresent()) {
			boolean isDeleted = studentService.deleteStudent(npm.get());
			if(isDeleted) {
				return "delete";							
			} else {
				model.addAttribute("error", "NPM Tidak Ditemukan. Proses Delete Dibatalkan");
				return "error";
			}
		} else {
			model.addAttribute("error", "NPM Kosong. Proses Delete Dibatalkan");
			return "error";
		}
	}
	
	/*
	 * Overlap Function
	 */
//	@RequestMapping("/student/view")
//	public String view(Model model, @RequestParam(value="npm", required=true) String npm) {
//		StudentModel student = studentService.selectStudent(npm);
//		if(student == null) {
//			model.addAttribute("error", "NPM Tidak Ditemukan");
//			return "error";
//		}
//		else {
//			model.addAttribute("student", student);			
//			return "view";
//		}
//	}
	
	@RequestMapping(value= {"/student/view/{npm}", "/student/view"})
	public String viewPath(Model model, @PathVariable Optional<String> npm) {
		if(npm.isPresent()) {
			StudentModel student = studentService.selectStudent(npm.get());
			if(student != null) {
				model.addAttribute("student", student);
				return "view";							
			} else {
				model.addAttribute("error", "NPM Tidak Ditemukan");
				return "error";
			}
		} else {
			model.addAttribute("error", "NPM Kosong");
			return "error";
		}
	}
	
	@RequestMapping("/student/viewall")
	public String viewAll(Model model) {
		List<StudentModel> students = studentService.selectAllStudent();
		model.addAttribute("students", students);
		return "viewall";
	}
}

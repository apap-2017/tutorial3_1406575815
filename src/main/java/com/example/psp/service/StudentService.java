package com.example.psp.service;

import java.util.List;

import com.example.psp.model.StudentModel;

public interface StudentService {
	StudentModel selectStudent(String npm);
	
	List<StudentModel> selectAllStudent();
	
	void addStudent(StudentModel student);
	
	boolean deleteStudent(String npm);
}

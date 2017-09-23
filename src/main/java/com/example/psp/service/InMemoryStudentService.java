package com.example.psp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.psp.model.StudentModel;

public class InMemoryStudentService implements StudentService {
	List<StudentModel> listStaticStudent;
	
	public InMemoryStudentService() {
		listStaticStudent = new ArrayList<>();
	}
	
	@Override
	public StudentModel selectStudent(String npm) {
		Iterator<StudentModel> itr = listStaticStudent.iterator();
		while(itr.hasNext()) {
			StudentModel temp = itr.next();
			if(temp.getNpm().equals(npm)) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<StudentModel> selectAllStudent() {
		return listStaticStudent;
	}

	@Override
	public void addStudent(StudentModel student) {
		try {
			listStaticStudent.add(student);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteStudent(String npm) {
		Iterator<StudentModel> itr = listStaticStudent.iterator();
		while(itr.hasNext()) {
			StudentModel temp = itr.next();
			if(temp.getNpm().equals(npm)) {
				itr.remove();
				return true;
			}
		}
		return false;
	}

}

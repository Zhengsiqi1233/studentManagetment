package com.datang.hrb.service.Impl;


import java.sql.Connection;

import com.datang.hrb.dao.StudentGet;
import com.datang.hrb.service.StudentService;
import com.datang.hrb.vo.Student;
import com.datang.hrb.vo.User;

public class StudentServiceImpl implements StudentService{
	 private StudentGet studentget = new StudentGet();

	@Override
	public int addStudent(Student s)  {
		// TODO Auto-generated method stub
		return studentget.add(s);
	}

	@Override
	public int deleteStudent(Student s) {
		// TODO Auto-generated method stub
		return studentget.delete(s);
	}
	

}

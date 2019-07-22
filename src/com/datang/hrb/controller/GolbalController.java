package com.datang.hrb.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.dao.ConnectionUtil;
import com.datang.hrb.service.LoginService;
import com.datang.hrb.service.RegisterService;
import com.datang.hrb.service.StudentService;
import com.datang.hrb.service.Impl.LoginServiceImpl;
import com.datang.hrb.service.Impl.RegisterServiceImpl;
import com.datang.hrb.service.Impl.StudentServiceImpl;
import com.datang.hrb.vo.Student;
import com.datang.hrb.vo.User;

public class GolbalController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("run in doGet");

		resp.sendRedirect("ok.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String sno = req.getParameter("sno");
		String sname = req.getParameter("sname");
		String sex = req.getParameter("sex");
		String cname = req.getParameter("cname");
		String sdept = req.getParameter("sdept");
		String ssname = req.getParameter("ssname");
		String smail = req.getParameter("smail");
		String sphone = req.getParameter("sphone");
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		PreparedStatement ps = null;
		Connection conn = ConnectionUtil.getConnection();
		String action = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".do"));
		if(action.equals("add")) {//增加
			
			if(sno != "" && sname != "" && sex != "" && cname != "" && sdept != "" && ssname != "" && smail != "" && sphone != "" ) {
			
				StudentService studentService = new StudentServiceImpl();
				Student s = new Student();
				s.setSno(sno);
				s.setSname(ssname);
				s.setSex(sex);
				s.setCname(cname);
				s.setSdept(sdept);
				s.setSsname(ssname);
				s.setSmail(smail);
				s.setSphone(sphone);
				int i = studentService.addStudent(s);
				if (i ==1) {
					
					resp.sendRedirect("student_list.jsp");
				} else {
					resp.sendRedirect("error.jsp");
				}
			} else {
				resp.sendRedirect("error.jsp");
			}
		}
		if(action.equals("delete")) {
			StudentService studentService = new StudentServiceImpl();
			Student s = new Student();
			s.setSno(sno);
			resp.sendRedirect("student_list.jsp");
		}
		if(action.equals("register")) {
				
				if(username != ""&&password != "") {
				RegisterService registerService = new RegisterServiceImpl();
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				int i = registerService.saveUser(user);
				if(i==1) {
					resp.sendRedirect("register_success.jsp");
				}else {
					resp.sendRedirect("register_fail.jsp");
				}
				}else {
					resp.sendRedirect("register_fail.jsp");
				}
			}
		if (action.equals("login")) {
			try {
				ps = conn.prepareStatement("select * from student");
				ResultSet rs = ps.executeQuery();

				if (rs != null && rs.next()) {
					List<Student> StudentList = new ArrayList<Student>();
					while (rs.next()) {
						Student s = new Student();
						s.setSno(rs.getString(1));
						s.setSname(rs.getString(2));
						s.setSex(rs.getString(3));
						s.setCname(rs.getString(4));
						s.setSdept(rs.getString(5));
						s.setSsname(rs.getString(6));
						s.setSmail(rs.getString(7));
						s.setSphone(rs.getString(8));
						
						StudentList.add(s);
					}
					session.setAttribute("StudentList", StudentList);
					resp.sendRedirect("student_list.jsp");
				} else {
					resp.sendRedirect("error.jsp");
				}
			} catch (SQLException e) {
				resp.sendRedirect("error.jsp");
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		
			
		
	
	}
 
}

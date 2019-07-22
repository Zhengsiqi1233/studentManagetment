package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.datang.hrb.vo.Student;

public class StudentGet {
	public int add(Student s)  {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		int result = 0;
		try {
			ps = conn.prepareStatement("insert into student(sno,sname,sex,cname,sdept,ssname,smail,sphone) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, s.getSno());
			ps.setString(2, s.getSname());
			ps.setString(3, s.getSex());
			ps.setString(4, s.getCname());
			ps.setString(5, s.getSdept());
			ps.setString(6, s.getSsname());
			ps.setString(7, s.getSmail());
			ps.setString(8, s.getSphone());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
			return result;
	}
	
	public int delete(Student s) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		int result = 0;
		try {
			ps = conn.prepareStatement("delete from student where sno=? ");
			ps.setString(1, s.getSno());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		return result;
	}
}

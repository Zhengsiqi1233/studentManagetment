package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.datang.hrb.vo.User;

public class UserDao {
	public String getUsersByUsername(String username) {//登陆
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		String password = null;
		try {
			ps = conn.prepareStatement("select * from user where username=?");
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			password = rs.getString(2);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return password;
		
	}public int saveUser(User user){//注册
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		int result = 0;
		try {
			ps = conn.prepareStatement("insert into user(username,password) values(?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
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
}

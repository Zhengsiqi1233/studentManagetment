package com.datang.hrb.service.Impl;


import com.datang.hrb.dao.StudentGet;
import com.datang.hrb.dao.UserDao;
import com.datang.hrb.service.LoginService;
import com.datang.hrb.vo.User;

public class LoginServiceImpl implements LoginService {

	@Override
	public String login(User user) {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDao();
		String password = userdao.getUsersByUsername(user.getUsername());
		if(password.equals(user.getPassword())) {
			return "login_success.jsp";
		}else {
			return "login_fail.jsp";
		}
	}
}

package com.javaweb.dao.impl;

import java.util.List;

import com.javaweb.dao.IUserDAO;
import com.javaweb.mapper.UserMapper;
import com.javaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("select * from user as u");
		sql.append(" inner join role as r on r.id = u.roleid");
		sql.append(" where username = ? and password = ? and status = ?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}
}

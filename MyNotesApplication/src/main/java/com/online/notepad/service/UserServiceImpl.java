/**
 * 
 */
package com.online.notepad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.notepad.dao.UserDao;
import com.online.notepad.model.User;

/**
 * @author Syamu
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	/* (non-Javadoc)
	 * @see com.online.notepad.service.UserService#save(com.online.notepad.model.User)
	 */
	@Override
	public User save(User user) {
		User userNew = userDao.save(user);
		return userNew;
	}

}

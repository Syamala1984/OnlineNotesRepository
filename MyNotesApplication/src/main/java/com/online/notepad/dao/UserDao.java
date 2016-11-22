/**
 * 
 */
package com.online.notepad.dao;

import org.springframework.stereotype.Repository;

import com.online.notepad.model.User;

/**
 * @author Syamu
 *
 */
@Repository
public interface UserDao {

	User login(User user);

	User save(User user);

}

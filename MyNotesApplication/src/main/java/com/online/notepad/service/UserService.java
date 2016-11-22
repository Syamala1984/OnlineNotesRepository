/**
 * 
 */
package com.online.notepad.service;

import org.springframework.stereotype.Service;

import com.online.notepad.model.User;

/**
 * @author Syamu
 *
 */
@Service
public interface UserService {

	User login(User user);

	User save(User user);

}

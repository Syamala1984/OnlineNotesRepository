/**
 * 
 */
package com.online.notepad.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.online.notepad.model.User;
import com.online.notepad.service.UserService;
import com.online.notepad.validator.LoginFormValidator;

/**
 * @author Syamu
 * 
 */
@Controller
public class LoginController {

	final static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	/*
	 * @Autowired LoginFormValidator loginFormValidator;
	 * 
	 * // Set a form validator
	 * 
	 * @InitBinder protected void initBinder(WebDataBinder binder) {
	 * binder.setValidator(loginFormValidator); }
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		logger.info("====== Welcome Index ======");
		return new ModelAndView("index","user",new User());
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(@ModelAttribute("command") User user, BindingResult result) {
		logger.info("====== Welcome loginForm ======");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("user", new User());
		modelView.setViewName("login");
		return modelView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("command") User user, BindingResult result, SessionStatus status,
			HttpServletRequest request) {
		logger.info("*********  login  *********");
		logger.info("User Info  ::::::::::::::::::  " + user.toString());

		String viewName = "login";
		ModelAndView mav = new ModelAndView(viewName);

		// Login Form Validation
		LoginFormValidator loginFormValidator = new LoginFormValidator();
		loginFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "login";
		}

		User userData = userService.login(user);

		status.setComplete();
		if (userData == null) {
			logger.info(" **********Invalid User");
			mav.addObject("user", new User());
			request.getSession().setAttribute("LOGGEDIN_USER", null);
			mav.getModel().put("ERROR", "Invalid UserName and Password");
			mav.setViewName(viewName);
			return "login";
		} else {
			logger.info(" ***********valid User");
			viewName = "notepadMain";
			request.getSession().setAttribute("LOGGEDIN_USER", userData);
			return "redirect:folders.do";
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user, BindingResult result, SessionStatus status,
			HttpServletRequest request) {
		logger.info("====== Welcome createUser ======");
		
		// Login Form Validation
		LoginFormValidator loginFormValidator = new LoginFormValidator();
		loginFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "login";
		}
		
		User userNew = userService.save(user);
		request.getSession().setAttribute("LOGGEDIN_USER", userNew);
		return "redirect:folders.do";
	}
}

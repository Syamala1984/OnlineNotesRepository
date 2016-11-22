/**
 * 
 */
package com.online.notepad.common.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.online.notepad.model.User;

/**
 * @author Syamu
 * 
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	final static Logger logger = Logger
			.getLogger(AuthenticationInterceptor.class);

	public AuthenticationInterceptor() {
		logger.info("*********AuthenticationInterceptor*******");
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		
		logger.info("*********URI*******"+uri);

		if (!uri.endsWith("login.do") && !uri.endsWith("logout.do")) {
			User userData = (User) request.getSession().getAttribute(
					"LOGGEDIN_USER");
			logger.info("*********userData*******"+userData);

			if (userData == null) {
				response.sendRedirect("login.do");
				return false;
			}
		}
		return true;
	}
}

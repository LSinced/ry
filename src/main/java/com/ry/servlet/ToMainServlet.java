/**
 * 
 */
package com.ry.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author wanglei
 * 上午9:49:17
 */
//@WebServlet(name="ToMainServlet",urlPatterns="/main",loadOnStartup=-1)
public class ToMainServlet extends HttpServlet {
	static Logger logger=LoggerFactory.getLogger(ToMainServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("跳转");
		request.getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

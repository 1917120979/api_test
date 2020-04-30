package api.servlet;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import api.bean.User;
import api.dao.UserDAO;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);
	
	private UserDAO uDAO = new UserDAO();
	 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			String method = (String) request.getAttribute("method");
			logger.debug("method>>"+method);
			Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			String redirect = m.invoke(this, request, response).toString();
			if (redirect.startsWith("@")) {
				response.sendRedirect(redirect.substring(1));
			}else if(redirect.startsWith("%")) {
				response.getWriter().print(redirect.substring(1));
			}else {
				request.getRequestDispatcher(redirect).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = uDAO.getUsername(username);
		
		JSONObject json = new JSONObject();
		if (null != user) {
			if (user.getPassword().equals(password)) {
				request.getSession().setAttribute("user", user);
				json.put("code", "0");
				json.put("msg", "success");
				json.put("data", "null");				
				logger.debug("返回值:"+json.toJSONString());
			}else {
				json.put("code", "402");
				json.put("msg", "password is error!");
				json.put("data", "null");
			}			
		}else {
			json.put("code", "401");
			json.put("msg", "username is not exsit!");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}
	
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User bean = new User();
		bean.setUsername(username);
		bean.setPassword(password);
		bean.setRole(1);
		User user = uDAO.getUsername(username);
		JSONObject json = new JSONObject();
		
		if (null == user) {
			if (uDAO.add(bean)) {
				json.put("code", "0");
				json.put("msg", "success");
				json.put("data", bean);
			}else {
				json.put("code", "401");
				json.put("msg", "fail");
				json.put("data", "null");
			}
		}else {
			json.put("code", "403");
			json.put("msg", "username is exsit!");
			json.put("data", "null");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}
	
	public String exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return "/pages/login.jsp";
	}
	
	public String registerPage(HttpServletRequest request, HttpServletResponse response) {
		return "/pages/register.jsp";
	}
	
	public String loginPage(HttpServletRequest request, HttpServletResponse response) {
		return "/pages/login.jsp";
	}
}

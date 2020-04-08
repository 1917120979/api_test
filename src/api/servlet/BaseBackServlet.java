package api.servlet;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.dao.ApiAttributeDAO;
import api.dao.ApiInfoDAO;
import api.dao.ProjectDAO;
import api.dao.VariableDAO;
import api.dao.ApiResultDAO;
import api.dao.ConfigDAO;
import api.util.Page;

public abstract class BaseBackServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(BaseBackServlet.class);
	 
	public abstract String add(HttpServletRequest request, HttpServletResponse response,Page page);
	public abstract String delete(HttpServletRequest request, HttpServletResponse response,Page page);
	public abstract String edit(HttpServletRequest request, HttpServletResponse response,Page page);
	public abstract String update(HttpServletRequest request, HttpServletResponse response,Page page);
	public abstract String list(HttpServletRequest request, HttpServletResponse response,Page page);
	
	protected ApiInfoDAO aiDAO = new ApiInfoDAO();
	protected ApiAttributeDAO aattDAO = new ApiAttributeDAO();
	protected ApiResultDAO arDAO = new ApiResultDAO();
	protected ConfigDAO configDAO = new ConfigDAO();
	protected ProjectDAO projectDAO = new ProjectDAO();
	protected VariableDAO variableDAO = new VariableDAO();
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			int start = 0;
			int count = 10;
			try {
				start = Integer.parseInt(request.getParameter("page.start"));
			} catch (Exception e) {

			}
			try {
				count = Integer.parseInt(request.getParameter("page.count"));
			} catch (Exception e) {
				
			}
			
			Page page = new Page(start, count);
			String method = (String) request.getAttribute("method");
			Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class, Page.class);
			String redirect = m.invoke(this, request, response,page).toString();
			
			if (redirect.startsWith("@")) {
				response.sendRedirect(redirect.substring(1));
			}else if(redirect.startsWith("%")) {
				response.getWriter().print(redirect.substring(1));
			}else {
				request.getRequestDispatcher(redirect).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Map<String, Object> parseParam(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement();
				String value = request.getParameter(name);
				params.put(name, value);		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info(params.toString());
		return params;
		
	}
	
	public <T> T parseClass(Map<String, Object>  params, Class<T> clazz) {
		
		try {
			T t = clazz.newInstance();
			for (String key : params.keySet()) {
				Object value = params.get(key);						
				if (value != null) {
					Field field = clazz.getDeclaredField(key);
					field.setAccessible(true);
					field.set(t, value);
				}
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;				
	}
	
	public InputStream parseUplooad(HttpServletRequest request, Map<String, String> params) {
		InputStream is = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			factory.setSizeThreshold(1024*1024);
			
			List<?> items = upload.parseRequest(request);
			Iterator< ?> iter = items.iterator();
			while(iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					is = item.getInputStream();
				}else {
					String key = item.getFieldName();
					String value = item.getString("utf-8");
					params.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}
}

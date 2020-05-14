package api.servlet;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.bean.Testcase;
import api.bean.User;
import api.dao.AttributeDAO;
import api.dao.ApiDAO;
import api.dao.AssertDAO;
import api.dao.DebugResultDAO;
import api.dao.ExtractorDAO;
import api.dao.TestcaseDAO;
import api.dao.UserDAO;
import api.dao.ProjectDAO;
import api.dao.VariableDAO;
import api.dao.GroupDAO;

/**
 * 
 * @ClassName: BaseBackServlet
 * @Description:抽象类继承httpservlet,处理过滤器转发的请求 ，定义抽象方法
 * @author: Durant2035
 * @date: 2020年4月15日 下午8:38:08
 * @Copyright:
 */
@SuppressWarnings("serial")
public abstract class BaseBackServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(BaseBackServlet.class);

	public abstract String add(HttpServletRequest request, HttpServletResponse response);

	public abstract String delete(HttpServletRequest request, HttpServletResponse response);

	public abstract String edit(HttpServletRequest request, HttpServletResponse response);

	public abstract String update(HttpServletRequest request, HttpServletResponse response);

	public abstract String list(HttpServletRequest request, HttpServletResponse response);

	protected ApiDAO apiDAO = new ApiDAO();
	protected AttributeDAO attrDAO = new AttributeDAO();
	protected TestcaseDAO tcDAO = new TestcaseDAO();

	protected GroupDAO gDAO = new GroupDAO();
	protected ExtractorDAO reDAO = new ExtractorDAO();
	protected AssertDAO assertDAO = new AssertDAO();
	protected DebugResultDAO drDAO = new DebugResultDAO();

	protected ProjectDAO pDAO = new ProjectDAO();
	protected UserDAO uDAO = new UserDAO();
	protected VariableDAO vDAO = new VariableDAO();
	protected User user = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			String method = (String) request.getAttribute("method");
			Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			String redirect = m.invoke(this, request, response).toString();
			logger.debug("跳转的目标是>>>" + redirect);
			if (redirect.startsWith("@")) {
				response.sendRedirect(redirect.substring(1));
			} else if (redirect.startsWith("%")) {
				response.getWriter().print(redirect.substring(1));
			} else {
				request.getRequestDispatcher(redirect).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public InputStream parseUplooad(HttpServletRequest request, Map<String, String> params) {
		InputStream is = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			factory.setSizeThreshold(1024 * 1024);

			List<?> items = upload.parseRequest(request);
			Iterator<?> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					is = item.getInputStream();
				} else {
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

	public String getExtractorValue(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		String value = "";
		if (matcher.find()) {
			value = matcher.group(1);
		}
		logger.debug("表达式是>>>" + regex + "；提取到的内容是>>>" + value);
		return value;
	}
}

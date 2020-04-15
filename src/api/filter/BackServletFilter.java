package api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName:  BackServletFilter   
 * @Description:过滤器，处理所有配置的请求，组装servlet并服务端跳转到相应的servlet  
 * @author: Durant2035
 * @date:   2020年4月15日 下午8:35:57      
 * @Copyright:
 */
public class BackServletFilter implements Filter{
	 private static final Logger logger = LoggerFactory.getLogger(BackServletFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String contextPath = request.getServletContext().getContextPath();
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		logger.debug("本次的requestUri是>>>"+uri);
		if (uri.startsWith("/admin_")) {
			String servletPath = StringUtils.substringBetween(uri, "_","_")+"Servlet";
			String method = StringUtils.substringAfterLast(uri, "_");
			request.setAttribute("method", method);
			req.getRequestDispatcher("/"+servletPath).forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

package api.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

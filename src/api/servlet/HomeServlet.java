package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HomeServlet extends  BaseBackServlet{

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {
		int pNum = pDAO.getTotal();
		int apiNum = apiDAO.getTotal();
		int userNum = uDAO.getTotal();
		
		request.setAttribute("pNum", pNum);
		request.setAttribute("apiNum", apiNum);
		request.setAttribute("userNum", userNum);
		return "/pages/home.jsp";
	}

}

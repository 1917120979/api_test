package api.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("serial")
public class TestServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) {
		String name =request.getParameter("name");
		JSONObject json = new JSONObject();		
		if (name.equals("小黑")) {
			json.put("msg", "你好，我是小黑！");
			json.put("code", 0);
			json.put("data", "{\"name\":\"小黑\",\"性别\":\"男\"}");
		}else {
			json.put("msg", "我不认识你！");
			json.put("code", 401);
			json.put("data", "");
		}
		response.setContentType("text/html;charset=UTF-8");
		return "%"+json.toJSONString();
	}
	
	
}

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import dao.ReplyDAO;

@WebServlet("*.rp")
public class ReplyController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/x-json; charset=UTF-8");
		
		JSONArray list = new JSONArray();
		ReplyDAO rdao = new ReplyDAO();
		String reply = req.getParameter("reply");
		String id = req.getParameter("id");
		
		String uri = req.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".rp"));
		
		if(com != null && com.trim().equals("rinsert")) {
			rdao.register(id, reply);
			
		}else if(com !=null && com.trim().equals("rlist")){
			list = rdao.rlist(id);
			resp.getWriter().print(list);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

}
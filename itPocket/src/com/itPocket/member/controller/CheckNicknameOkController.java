package com.itPocket.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.itPocket.Action;
import com.itPocket.Result;
import com.itPocket.member.dao.MemberDAO;

public class CheckNicknameOkController implements Action {
	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		String memberNickname =req.getParameter("memberNickname");
		MemberDAO memberDAO = new MemberDAO();
		boolean check = memberDAO.selectNickname(memberNickname) == null;
		JSONObject result = new JSONObject();
		try {
			result.put("check", check);
			result.put("length", memberNickname.length());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		PrintWriter out = resp.getWriter();
		out.print(result.toString());
		out.close();

		return null;
	}

}

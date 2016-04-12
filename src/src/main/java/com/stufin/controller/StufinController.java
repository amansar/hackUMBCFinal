package com.stufin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stufin.dao.UserDao;
import com.stufin.entity.User;
import com.stufin.vo.InputWrapper;

@Controller
public class StufinController {
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping("/home")
	public String displayHome(@RequestParam("id") String param, HttpServletRequest htRequest) {
		ObjectMapper om  = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		InputWrapper iw = null;
		try {
			iw = om.readValue(param, InputWrapper.class);
			insertUserIfDoesntExist(iw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = htRequest.getSession();
		session.setAttribute("userEmailId", iw.getEmailId());
		session.setAttribute("userName", iw.getUserName());
		return "createProfile";
	}
	
	@RequestMapping("/search")
	public String displayHome() {
		return "search";
	}
	
	private void insertUserIfDoesntExist(InputWrapper wrapper) {
		User user = userDao.getByColumn(User.class, "emailId", wrapper.getEmailId().trim());
		if (user != null ) {
		} else {
			user = new User();
			user.setName(wrapper.getUserName());
			user.setEmailId(wrapper.getEmailId());
			userDao.addEntity(user);
		}
	}

}

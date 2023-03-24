package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ibf2022.batch2.ssf.frontcontroller.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProtectedController {

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected
	@GetMapping(path = "/protected/{link}")
	public String getProtected(Model model, HttpSession session, User user, @PathVariable String link) {
		user = getUser(session);
		if (user.getIsAuthenticated()) {
			return link;
		}
		return "view0";
	}


	private User getUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			user = new User();
			session.setAttribute("user", user);
		}
		return user;
	}
}

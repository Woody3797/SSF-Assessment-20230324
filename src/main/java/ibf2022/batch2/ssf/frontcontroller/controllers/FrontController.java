package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.frontcontroller.model.User;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class FrontController {

	// TODO: Task 2, Task 3, Task 4, Task 6
	@Autowired
	AuthenticationService authService;

	@GetMapping(path = { "/", "/index" })
	public String getLandingPage(Model model, HttpSession session) {
		User user = getUser(session);
		session.invalidate();
		model.addAttribute("user", user);
		return "view0";
	}

	@PostMapping(path = "/login")
	public String getLogin(Model model, HttpSession session, @Valid @ModelAttribute User user, BindingResult binding) throws Exception {
		if (binding.hasErrors()) {
			return "view0";
		}

		try {
			ResponseEntity<String> response = authService.authenticate(user.getUsername(), user.getPassword());
			if (response.getBody().toLowerCase().contains("authenticated")) {
				user.setIsAuthenticated(true);
				session.setAttribute("user", user);
				model.addAttribute("user", user);
				return "view1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setLoginAttempts(user.getLoginAttempts() + 1);
		model.addAttribute("user", user);
		
		return "view0";
	}

	@GetMapping(path = "/logout")
	public String logout(Model model, HttpSession session, User user) {
		user = new User();
		session.invalidate();
		model.addAttribute("user", user);
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

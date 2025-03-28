package com.example.demo;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsercController {
	@Autowired
	private UsercService service;

	@Autowired
	private HttpSession session;

	public UsercController(HttpSession session) {
		this.session = session;
	}

	@RequestMapping("/selectAll")
	public String getAllUserc(Model m) {
		List<Userc> usercs = service.selectAll();

		m.addAttribute("usercs", usercs);

		return "selectAll";
	}

	@RequestMapping("/loginform")
	public String loginform() {
		return "index";

	}

	@PostMapping("/sendlogin")
	public String sarchIdAndPassword(
			Model m,
			@RequestParam("id") String id,
			@RequestParam("password") String password) {
		this.session.setAttribute("id", id);
		this.session.setAttribute("password", password);

		if (id.isEmpty() || password.isEmpty()) {
			m.addAttribute("msg", "未入力の項目があります");
			return "loginform";
		}

		List<Userc> usercs = service.findUsercByIdAndPassword(id, password);

		if (usercs.size() == 0) {
			usercs = null;
			m.addAttribute("msg", "入力に誤りがあります");
			return "loginform";
		}

		m.addAttribute("usercs", usercs);

		return "mainmenu";

	}

	@RequestMapping("/deleteForm")
	public String deleteForm() {
		return "deleteForm";
	}

	@PostMapping("/delete")
	public String delete(
			Model m,
			@RequestParam("id") String id) {
		int numId = Integer.parseInt(id);
		service.delete(numId);
		m.addAttribute("msg", "削除が正常に完了しました");

		return "result";
	}
}

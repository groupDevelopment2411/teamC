package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsercController {
	@Autowired
	private UsercService service;

	@RequestMapping("/selectAll")
	public String getAllUserc(Model m) {
		List<Userc> usercs = service.selectAll();

		m.addAttribute("usercs", usercs);

		return "selectAll";
	}

	
	
	@GetMapping("/sendlogin")
	public String sendlogin(
			@RequestParam("id") String id,
			@RequestParam("password") String password) {

		return "sendlogin";
	}

	@RequestMapping("/sendlogin")
	public String sarchIdAndPassword(
			Model m,
			@RequestParam("id") String id,
			@RequestParam("password") String password) {
		
		

		List<Userc> usercs = service.findUsercByIdAndPassword(id, password);

		if (id.isEmpty() || password.isEmpty()) {
			return "loginpage";
		}

		if (usercs.size() == 0) {
			usercs = null;
		}

		m.addAttribute("usercs", usercs);

		return "sendlogin";

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

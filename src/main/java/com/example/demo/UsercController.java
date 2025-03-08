package com.example.demo;

import java.util.List;

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

	@RequestMapping("/selectAll")
	public String getAllUserc(Model m) {
		List<Userc> usercs = service.selectAll();

		m.addAttribute("usercs", usercs);

		return "selectAll";
	}

	@RequestMapping("/sendlogin")
	public String sendlogin() {
		return "sendlogin";
	}

	@RequestMapping("/loginpage")
	public String getMatchIdUserc(
			Model m,
			@RequestParam("id") String id,
			@RequestParam("password") String password) {
		int numId = Integer.parseInt(id);
		List<Userc> usercs = service.selectById(numId, password);

		if (usercs.size() == 0) {
			usercs = null;
		}

		m.addAttribute("userc", usercs);

		return "loginpage";
	}

	@RequestMapping("/insertForm")
	public String insertForm() {
		return "insertForm";
	}

	@PostMapping("/insert")
	public String insertUserc(
			Model m,
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("password") String password) {
		int numId = Integer.parseInt(id);
		Userc userc = new Userc(numId, name, password);
		service.insert(userc);
		m.addAttribute("msg", "登録が正常に完了しました");

		return "result";
	}

	@RequestMapping("/updateForm")
	public String updateForm() {
		return "updateForm";
	}

	@PostMapping("/update")
	public String update(
			Model m,
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("password") String password) {
		int numId = Integer.parseInt(id);
		Userc userc = new Userc(numId, name, password);
		service.update(userc);
		m.addAttribute("msg", "更新が正常に完了しました");

		return "result";
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

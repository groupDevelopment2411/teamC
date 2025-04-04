package com.example.demo;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Validated
@Controller
public class UsercController {
	
	@Autowired
	private UsercService service;

	@Autowired
	private HttpSession session;

	public UsercController(HttpSession session) {
		this.session = session;
	}

	@RequestMapping("/first")
	public String first() {
		return "delete";
	}

	@RequestMapping("/second")
	public String second() {
		return "kensaku";
	}

	
	@RequestMapping("/kensakuform")
	public String searchUsercById(Model m,
		@RequestParam("id") int id) {
		List<Userc> usercs = service.searchUsercById(id);

		m.addAttribute("usercs", usercs);

		return "result";
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

		//if (id.isEmpty() || password.isEmpty()) {
			//m.addAttribute("msg", "未入力の項目があります");}
		
		
		List<Userc> usercs = service.findUsercByIdAndPassword(id, password);

		if (usercs.size() == 0) {
			usercs = null;
			m.addAttribute("msg", "入力に誤りがあります");
			return "loginform";
		}
		m.addAttribute("usercs", usercs);

		return "mainmenu";}
	


	@RequestMapping("/result")
	public String result() {
		return "delete";}
	
	
	@PostMapping("/deleteform")
	public String deleteSelectedUsers(
	    @RequestParam(value = "selectedIds", required = false) List<String> selectedIds,  // 🔥 String に変更
	    Model m,
	    HttpSession session) {

	    Object sessionIdObj = session.getAttribute("id");
	    if (sessionIdObj == null) {
	        m.addAttribute("msg", "ログインしていません。");
	        return "delete";
	    }

	    if (selectedIds == null || selectedIds.isEmpty()) {
	       // m.addAttribute("msg", "削除する項目を選択してください。");
	    } else {
	        // 数値のIDだけを抽出して変換
	        List<Integer> filteredIds = selectedIds.stream()
	            .filter(id -> id.matches("\\d+")) // 🔥 数字のみのデータを抽出
	            .map(Integer::parseInt) // 🔥 int に変換
	            .toList();

	        if (filteredIds.isEmpty()) {
	            m.addAttribute("msg", "有効なIDが選択されていません。");
	        } else {
	            service.deleteUsercsByIds(filteredIds);
	            m.addAttribute("msg", "選択したユーザーを削除しました。");
	        }
	    }

	    // 残っているユーザーを表示
	    List<Userc> remainingUsercs = service.getAllUsercs();
	    m.addAttribute("usercs", remainingUsercs);

	    return "deleteresult";
	}

	    

	    

}
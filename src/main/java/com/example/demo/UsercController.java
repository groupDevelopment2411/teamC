package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

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

	@RequestMapping("/third")
	public String third() {
		return "index";
	}
	
	@RequestMapping("/fourth")
	public String fourth() {
		return "mainmenu";
	}
	
	@RequestMapping("/fifth")
	public String fifth() {
		return "deleteconfirm";
	}
	
	@RequestMapping("/kensakuform")
	public String searchUsercById(Model m, @RequestParam("id") int id) {
		List<Userc> usercs = service.searchUsercById(id);
		m.addAttribute("usercs", usercs);
		return "result";
	}

	@RequestMapping("/loginform")
	public String loginform() {
		return "index";
	}

	@PostMapping("/sendlogin")
	public String searchIdAndPassword(Model m,
	                                  @RequestParam("id") String id,
	                                  @RequestParam("password") String password) {
		this.session.setAttribute("id", id);
		this.session.setAttribute("password", password);

		List<Userc> usercs = service.findUsercByIdAndPassword(id, password);

		if (usercs.isEmpty()) {
			m.addAttribute("msg", "入力に誤りがあります");
			return "loginform";
		}
		m.addAttribute("usercs", usercs);

		return "mainmenu";
	}

	/** 🔥 削除の確認画面を表示 */
	@PostMapping("/deleteconfirm")
	public String confirmDelete(
	    @RequestParam(value = "selectedIds", required = false) List<String> selectedIds,
	    Model m) {

	    if (selectedIds == null || selectedIds.isEmpty()) {
	        m.addAttribute("msg", "削除する項目を選択してください。");
	        return "fifth";
	    }

	    // 数値のみのIDを抽出して変換
	    List<Integer> filteredIds = selectedIds.stream()
	        .filter(id -> id.matches("\\d+"))
	        .map(Integer::parseInt)
	        .collect(Collectors.toList());

	    if (filteredIds.isEmpty()) {
	        m.addAttribute("msg", "有効なIDが選択されていません。");
	        return "userlist";
	    }

	    List<Userc> selectedUsercs = service.findUsersByIds(filteredIds);
	    m.addAttribute("selectedUsercs", selectedUsercs);
	    m.addAttribute("selectedIds", filteredIds); // 🔥 削除処理で使う

	    return "deleteconfirm"; // 確認画面へ
	}

	/** 🔥 実際に削除処理を実行 */
	@PostMapping("/delete")
	public String deleteUsers(
	    @RequestParam(value = "selectedIds", required = false) List<Integer> selectedIds,
	    Model m) {

	    if (selectedIds == null || selectedIds.isEmpty()) {
	        m.addAttribute("msg", "削除する項目が選択されていません。");
	        return "deleteresult";
	    }

	    service.deleteUsercsByIds(selectedIds);
	    m.addAttribute("msg", "選択したユーザーを削除しました。");

	    List<Userc> remainingUsercs = service.getAllUsercs();
	    m.addAttribute("usercs", remainingUsercs);

	    return "deleteresult"; // 削除結果画面へ
	}
}

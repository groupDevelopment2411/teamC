package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

	@RequestMapping("/sixth")
	public String sixh() {
		return "Allform";
	}

	@RequestMapping("/seventh")
	public String seventh() {
		return "insert";
	}
	@RequestMapping("/eighth")
	public String eighth() {
		return "update";
	}

	@RequestMapping("/kensakuform")
	public String searchUsercById(Model m, @RequestParam(value = "id", required = false) Integer id) {
	    if (id == null) {
	        m.addAttribute("msg", "IDを入力してください");
	        return "kensaku";
	    }

	    List<Userc> usercs = service.searchUsercById(id);
	    m.addAttribute("selectedIds", usercs.get(0).getId());

	    if (usercs.isEmpty()) {
	        m.addAttribute("msg", "該当するユーザーが見つかりません");
	        return "kensaku";
	    }

	    return "deleteconfirm";
	}


	@GetMapping("/login")
	public String showLoginForm(Model m) {
		m.addAttribute("userc", new Userc()); 
		return "loginform"; 
	}

	@RequestMapping("/loginform")
	public String loginform(Model m) {
		m.addAttribute("userc", new Userc()); 
		return "index";
	}
	@PostMapping("/sendlogin")
	public String searchIdAndPassword(Model m,
	        @RequestParam("id") String id,
	        @RequestParam("password") String password) {

	    m.addAttribute("id", id);
	    m.addAttribute("password", password);

	    List<Userc> usercs = service.findUsercByIdAndPassword(id, password);

	    if (usercs.isEmpty()) {
	        m.addAttribute("msg", "入力に誤りがあります");
	        return "loginform";
	    }

	   
	    Userc loginUser = usercs.get(0); 
	    session.setAttribute("id", loginUser.getId());
	    session.setAttribute("password", loginUser.getPassword());
	    session.setAttribute("name", loginUser.getName());
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String loginTime = LocalDateTime.now().format(formatter);
	    session.setAttribute("loginTime", loginTime);

	    m.addAttribute("usercs", usercs);
	    return "mainmenu";
	}


	//削除の確認画面を表示 //
	@PostMapping("/kensaku")
	public String confirmDelete(
	        @RequestParam(value = "selectedIds", required = false) List<String> selectedIds,
	        Model m) {

	    if (selectedIds == null || selectedIds.isEmpty()) {
	        m.addAttribute("msg", "削除する項目を選択してください。");
	        return "kensakuform";
	    }

	    // 数値のみのIDを抽出して変換
	    List<Integer> filteredIds = selectedIds.stream()
	            .filter(id -> id.matches("\\d+"))
	            .map(Integer::parseInt)
	            .collect(Collectors.toList());

	    if (filteredIds.isEmpty()) {
	        m.addAttribute("msg", "有効なIDが選択されていません。");
	        return "kensakuform";
	    }

	    List<Userc> selectedUsercs = service.findUsersByIds(filteredIds);
	    m.addAttribute("selectedUsercs", selectedUsercs);
	    m.addAttribute("selectedIds", filteredIds); // 🔥 削除処理で使う

	    return "deleteconfirm"; // 確認画面へ
	}


	@PostMapping("/delete")
	public String deleteUsers(
	        @RequestParam(value = "selectedIds", required = false) List<Integer> selectedIds,
	        Model m,
	        HttpSession session) {

	    if (selectedIds == null || selectedIds.isEmpty()) {
	        m.addAttribute("msg", "削除する項目が選択されていません。");
	        return "deleteresult";
	    }

	    // ログイン中のユーザーIDを取得
	    Object sessionIdObj = session.getAttribute("id");
	    if (sessionIdObj == null) {
	        m.addAttribute("msg", "ログインしていません。");
	        return "loginform"; // ログイン画面に戻る
	    }

	    int loggedInUserId;
	    try {
	        loggedInUserId = Integer.parseInt(sessionIdObj.toString()); // 数値に変換
	    } catch (NumberFormatException e) {
	        m.addAttribute("msg", "ログイン情報が不正です。");
	        return "loginform";
	    }

	    // 削除対象のIDリストにログイン中のIDが含まれていないか確認
	    if (selectedIds.contains(loggedInUserId)) {
	        m.addAttribute("msg", "ログイン中のIDは削除できません。");

	        // 再度、削除確認画面のデータをセットする
	        List<Userc> selectedUsercs = service.findUsersByIds(selectedIds);
	        m.addAttribute("selectedUsercs", selectedUsercs);
	        m.addAttribute("selectedIds", selectedIds);

	        return "deleteconfirm"; // 確認画面に戻る
	    }

	    // ログイン中のIDが含まれていなければ削除実行
	    service.deleteUsercsByIds(selectedIds);
	    m.addAttribute("msg", "選択したユーザーを削除しました。");

	    // 残っているユーザーを表示
	    List<Userc> remainingUsercs = service.getAllUsercs();
	    m.addAttribute("usercs", remainingUsercs);

	    return "deleteresult"; // 削除結果画面へ
	}

	@RequestMapping("/All")
	public String getAllUsercs(Model m) {
		List<Userc> usercs = service.selectAll();

		m.addAttribute("usercs", usercs);

		return "result";
	}

	@GetMapping("/deleteconfirm")
	public String showDeleteConfirm(@RequestParam("id") int id, Model model) {
	    Userc userc = service.findById(id); // ← serviceを使う！
	    model.addAttribute("userc", userc);
	    return "deleteconfirm";
	}
	@PostMapping("/deleteconfirm")
	public String showDeleteConfirm(@RequestParam("selectedIds") List<Integer> ids, Model m) {
	    List<Userc> usercs = service.findUsersByIds(ids);
	    m.addAttribute("selectedUsercs", usercs);
	    m.addAttribute("selectedIds", ids);
	    return "deleteconfirm";
	}

}
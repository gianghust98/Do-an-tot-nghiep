package com.webtoeic.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.bind.support.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webtoeic.entities.NguoiDung;
import com.webtoeic.grpc.TransferStudentInfoClient;
import com.webtoeic.service.BaiTapTuVungService;
import com.webtoeic.service.NguoiDungService;
import com.webtoeic.service.SlideBannerService;

@Controller
@SessionAttributes("loggedInUser")
public class ClientController {
	@Autowired
	private SlideBannerService slideBannerService;
	@Autowired
	private BaiTapTuVungService baitaptuvungService;

	@Autowired
	private NguoiDungService nguoiDungService;
	
	@Autowired
	private TransferStudentInfoClient transferClient ;
	
	@ModelAttribute("loggedInUser")
	public NguoiDung loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NguoiDung nguoiDung = new NguoiDung();
		if (auth.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			String principal = auth.getPrincipal().toString();
			String[] part = principal.split(",");
			String name = part[2].split("=")[1];
			nguoiDung.setHoTen(name);
			nguoiDung.setLoginOauth2(true);
			return nguoiDung;
		} else {
			return nguoiDungService.findByEmail(auth.getName());
		}
	}

	public NguoiDung getSessionUser(HttpServletRequest request) {
		NguoiDung nguoiDung = (NguoiDung) request.getSession().getAttribute("loggedInUser");
		return nguoiDung;
	}

	@GetMapping(value = { "/home", "/" })
	public String home(Model model, @AuthenticationPrincipal OAuth2User oauth2User, HttpServletRequest request) {
		model.addAttribute("listslidebanner", slideBannerService.findAll());
		return "client/home";
	}

	@GetMapping(value = "test")
	public String testmarkdown() {
		return "client/testMardown";
	}

	@GetMapping(value = "/profile")
	public String profilePage(Model model, HttpServletRequest request, @AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("user", getSessionUser(request));
		return "client/profile";
	}

	@PostMapping("/profile/update")
	public String updateNguoiDung(@ModelAttribute NguoiDung nd, HttpServletRequest request) {
		NguoiDung currentUser = getSessionUser(request);
		currentUser.setDiaChi(nd.getDiaChi());
		currentUser.setHoTen(nd.getHoTen());
		currentUser.setSoDienThoai(nd.getSoDienThoai());
		nguoiDungService.updateUser(currentUser);
		return "redirect:/profile";
	}
	
	@PostMapping(value = "/register/save-image", consumes = "multipart/form-data")
	@ResponseBody
	public String addImage(@RequestParam("file_image") MultipartFile file_image) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mess = "Upload image successfully!";
		NguoiDung nguoiDung = nguoiDungService.findByEmail(auth.getName());
		try {		
			System.out.println(transferClient.ImgRegister(nguoiDung.getId(),file_image.getBytes()));

		} catch (Exception e) {
			System.out.println("ErrorReadFile:" + e);
		}
		return mess;
					
	}
	
	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			//request.removeAttribute("message");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}



}

package com.webtoeic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.webtoeic.api.admin.BaiThiThuApi;
import com.webtoeic.entities.CauHoiBaiThiThu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.webtoeic.entities.NguoiDung;
import com.webtoeic.entities.VaiTro;
import com.webtoeic.service.NguoiDungService;
import com.webtoeic.service.SecurityService;
import com.webtoeic.validator.NguoiDungValidator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private NguoiDungService nguoiDungService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private NguoiDungValidator nguoiDungValidator;

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("newUser", new NguoiDung());
		return "register";
	}

	@PostMapping("/register")
	public String registerProcess(@ModelAttribute("newUser") @Valid NguoiDung nguoiDung
			, BindingResult bindingResult,
			Model model) {

		nguoiDungValidator.validate(nguoiDung, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}
		nguoiDung.setVaiTro(VaiTro.ROLE_MEMBER);
		nguoiDungService.saveUser(nguoiDung);
		securityService.autologin(nguoiDung.getEmail(), nguoiDung.getConfirmPassword());
		return "redirect:/";
	}
	@PostMapping(value = "/register/save-image", consumes = "multipart/form-data")
	@ResponseBody
	public List<String> addImage(@RequestParam("file_image") MultipartFile file_image){
		List<String> response = new ArrayList<String>();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NguoiDung nguoiDung = nguoiDungService.findByEmail(auth.getName());

		try {
			// save file upload to local folder
			Path pathImage = Paths.get(rootDirectory + "/resources/file/images/exam/" + "" + "." + file_image.getOriginalFilename());
			file_image.transferTo(new File(pathImage.toString()));
			nguoiDung.setMultipartFile(file_image.getOriginalFilename());
			nguoiDungService.saveUser(nguoiDung);
		} catch (Exception e) {
			response.add(e.toString());
			System.out.println("ErrorReadFileExcel:" + e);

		}
		return response;
	}

}

package com.webtoeic.controller.client;

import java.io.File;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.ls.LSOutput;

import com.webtoeic.entities.BaiThiThu;
import com.webtoeic.entities.CauHoiBaiThiThu;
import com.webtoeic.entities.KetQuaBaiTest;
import com.webtoeic.entities.NguoiDung;
import com.webtoeic.grpc.TransferStudentInfoClient;
import com.webtoeic.service.BaiThiThuService;
import com.webtoeic.service.CauHoiBaiThiThuService;
import com.webtoeic.service.KetQuaBaiTestService;
import com.webtoeic.service.NguoiDungService;
import com.webtoeic.grpc.TransferStudentInfoClient;


@Controller
public class BaiFullTestListeningController {
	
	@Autowired
	BaiThiThuService baithithuServie;
	
	
	@Autowired
	CauHoiBaiThiThuService cauhoibaithithuService;
	
	@Autowired
	KetQuaBaiTestService ketquabaitestService;
	
	@Autowired
	BaiThiThuService baithithuService;
	
	@Autowired
	private NguoiDungService nguoiDungService;
	
//	@Autowired
//	private TransferStudentInfoClient transferClient ;
//	
//	public String authUserTest;
//	public int countFalse = 0;
//	public String check = "false";
//	public int count = 0;
	
	@ModelAttribute("loggedInUser")
	public NguoiDung getSessionUser(HttpServletRequest request) {
		return (NguoiDung) request.getSession().getAttribute("loggedInUser");
	}
	
//	@PostMapping("/takePicture/duringTest")
//	public void beforeTest(ModelMap model,@RequestParam("canvasImage") MultipartFile file,@RequestParam("idExam") int idBaiThi ) throws IOException {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		NguoiDung nguoiDung = nguoiDungService.findByEmail(auth.getName());
//		
//		authUserTest = transferClient.imgAuth(nguoiDung.getEmail(),file.getBytes());
//		System.out.println("during test: "+ authUserTest);
//		count = count+1;
//		if(check.equals(authUserTest)) {
//			countFalse = countFalse + 1 ;
//			System.out.println("countFalse: "+ countFalse);
//		}
//		
//		BaiThiThu baithithu = new BaiThiThu();
//		Integer idBaiThiThu = idBaiThi;
//		
//		System.out.println("idBaiThiThu: "+ idBaiThiThu);
//	
//		baithithu.setBaithithuid(idBaiThiThu);
//		
//		KetQuaBaiTest ketQuaBaiTest = new KetQuaBaiTest();
//		ketQuaBaiTest.setBaithithu(baithithu);
//		ketQuaBaiTest.setNguoidung(nguoiDung);
//		
//		System.out.println("countFalse2: "+ countFalse);
//		if(count == 6) {
//			ketQuaBaiTest.setCount_false(countFalse);
//			if(countFalse > 2) {
//				ketQuaBaiTest.setStatus("Rejected");
//			}else {
//				ketQuaBaiTest.setStatus("Approve");
//			}
//			ketquabaitestService.save(ketQuaBaiTest);
//			
//		}
//		
		

	@RequestMapping(value="/showResultListening/{examId}/{socaudung}",method=RequestMethod.POST)
	public String showResult(Model model,@RequestBody String[] jsonAnswerUser,
							@PathVariable("examId") int examId,
							@PathVariable("socaudung") String socaudung) {
	
	List<CauHoiBaiThiThu> list = cauhoibaithithuService.getListCauHoi(baithithuServie.getBaiThiThu(examId).get(0));
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	NguoiDung currentUser = nguoiDungService.findByEmail(auth.getName());
	
	 for (int i = 0;i<50;i++) {
		 list.get(i).setDapAnUser(jsonAnswerUser[i]);
		
     }
	 
	// save so cau dung vao db
//	 	Date time = new Date();
//		KetQuaBaiTest ketquabaitest = new KetQuaBaiTest();
//		ketquabaitest.setNgaythi(time);
//		ketquabaitest.setBaithithu(baithithuService.getBaiThiThu(examId).get(0));
//		ketquabaitest.setCorrectlisten(socaudung);
//		ketquabaitest.setNguoidung(currentUser);
//		
//		ketquabaitestService.save(ketquabaitest);
	 
	 model.addAttribute("socaudung",socaudung);
	 model.addAttribute("listQuestion",list);
		
		return "client/listeningResult";
	}
	
	
	

	
	

}

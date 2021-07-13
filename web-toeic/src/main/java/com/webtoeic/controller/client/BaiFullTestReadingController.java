package com.webtoeic.controller.client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webtoeic.entities.CauHoiBaiThiThu;
import com.webtoeic.entities.KetQuaBaiTest;
import com.webtoeic.entities.NguoiDung;
import com.webtoeic.service.BaiThiThuService;
import com.webtoeic.service.CauHoiBaiThiThuService;
import com.webtoeic.service.NguoiDungService;



@Controller
public class BaiFullTestReadingController {
	@Autowired
	BaiThiThuService baithithuServie;
	
	@Autowired
	CauHoiBaiThiThuService cauhoibaithithuService;
	
	@Autowired
	private NguoiDungService nguoiDungService;
	
	public String socauListeningCorrect = "0" ;
	public String countLeaveL = "0";
	
	@GetMapping("/doExam/reading")
	public String ReadingExam(Model model,@RequestParam("idExam") int id) {		
		try {
				System.out.println("reading test begins!");
				List<CauHoiBaiThiThu> list = cauhoibaithithuService.getListCauHoi(baithithuServie.getBaiThiThu(id).get(0));
				model.addAttribute("listQuestion",list);
				model.addAttribute("socauListeningCorrect",socauListeningCorrect);		
				model.addAttribute("countLeaveListening",countLeaveL);	
				return "client/fullTestReading";
				
		}catch(Exception e) {
			System.out.println("error:"+e);
			return "client/error";
		}		
	}
	
	@RequestMapping(value="/reading/{examId}/{socaudung}/{countMouseLeaveL}",method=RequestMethod.POST)
	public String DetailReading(Model model,@PathVariable("examId") int id,@RequestBody String[] jsonAnswerUser,
			@PathVariable("socaudung") String socaudung,@PathVariable("countMouseLeaveL") String countMouseLeaveL) {		
		List<CauHoiBaiThiThu> list = cauhoibaithithuService.getListCauHoi(baithithuServie.getBaiThiThu(id).get(0));
		socauListeningCorrect = socaudung;
		countLeaveL = countMouseLeaveL;
		System.out.println("socauListeningCorrect: "+ socaudung);
		System.out.println("countLeaveL: "+ countLeaveL);
		model.addAttribute("listQuestion",list);
//		model.addAttribute("socauListeningCorrect",socaudung);		
		 return "client/fullTestReading";
	}
	
	@RequestMapping(value="/showResultReading/{examId}/{socaudung}",method=RequestMethod.POST)
	public String showResult(Model model,@RequestBody String[] jsonAnswerUser,
							@PathVariable("examId") int examId,
							@PathVariable("socaudung") int socaudung) {
	
	List<CauHoiBaiThiThu> list = cauhoibaithithuService.getListCauHoi(baithithuServie.getBaiThiThu(examId).get(0));
	
	// list có 100 câu hỏi, phần reading từ câu 51 đến 100
	 for (int i = 0;i<50;i++) {
		 list.get(i+50).setDapAnUser(jsonAnswerUser[i]);
     }
		 
	model.addAttribute("listQuestion",list);
	model.addAttribute("socaudung",socaudung);
//	model.addAttribute("socauListeningCorrect",socaudung);

			
		return "client/readingResult";
	}
	

}

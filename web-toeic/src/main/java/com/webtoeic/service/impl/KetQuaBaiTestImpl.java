package com.webtoeic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoeic.entities.BaiThiThu;
import com.webtoeic.entities.KetQuaBaiTest;
import com.webtoeic.entities.NguoiDung;
import com.webtoeic.repository.KetQuaBaiTestRepository;
import com.webtoeic.service.KetQuaBaiTestService;

@Service
public class KetQuaBaiTestImpl implements KetQuaBaiTestService{
	@Autowired
	KetQuaBaiTestRepository ketquabaitestRepo;
	
	@Override
	public KetQuaBaiTest save(KetQuaBaiTest ketquabaitest) {
		return ketquabaitestRepo.save(ketquabaitest);
	}

	@Override
	public List<KetQuaBaiTest> findAllKetQua() {
		List<KetQuaBaiTest> listKetQuaBaiTests = ketquabaitestRepo.findAll();
		return listKetQuaBaiTests;
	}

	@Override
	public KetQuaBaiTest findKetQuaBaiTest(Integer idbaithi) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public KetQuaBaiTest findKetQuaBaiTest(Integer idbaithi) {
//		return ketquabaitestRepo.findKetQuaBaiTest(idbaithi);
//	}
	
}

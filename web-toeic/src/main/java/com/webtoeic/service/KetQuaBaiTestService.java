package com.webtoeic.service;

import java.util.List;
import java.util.Optional;

import com.webtoeic.entities.BaiThiThu;
import com.webtoeic.entities.KetQuaBaiTest;

public interface KetQuaBaiTestService {
	KetQuaBaiTest save(KetQuaBaiTest ketquabaitest);
	List<KetQuaBaiTest> findAllKetQua();
	KetQuaBaiTest findKetQuaBaiTest(Integer idbaithi);
}

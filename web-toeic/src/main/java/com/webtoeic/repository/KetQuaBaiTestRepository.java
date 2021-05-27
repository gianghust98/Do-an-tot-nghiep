package com.webtoeic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webtoeic.entities.BaiThiThu;
import com.webtoeic.entities.KetQuaBaiTest;

@Repository
public interface KetQuaBaiTestRepository extends JpaRepository<KetQuaBaiTest,Integer> {

	Optional<KetQuaBaiTest> findByBaithithu(Integer id);
//	String sql = "FROM ket_qua WHERE ket_qua.nguoidungid = ?1"; 
//	@Query(sql)
//	KetQuaBaiTest findKetQuaBaiTest(Integer baithithuid);
	
}

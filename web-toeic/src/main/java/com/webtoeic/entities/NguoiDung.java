package com.webtoeic.entities;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webtoeic.jpautil.VaiTroTypeConverter;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class NguoiDung {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String email;

	@JsonIgnore
	private String password;

	@Transient
	@JsonIgnore
	private String confirmPassword;
	private String hoTen;
	private String soDienThoai;
	private String diaChi;
	private String multipartFile;

	public String getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(String multipartFile) {
		this.multipartFile = multipartFile;
	}

	@Transient
	private boolean loginOauth2;

	public boolean isLoginOauth2() {
		return loginOauth2;
	}

	public void setLoginOauth2(boolean loginOauth2) {
		this.loginOauth2 = loginOauth2;
	}

	@Convert(converter = VaiTroTypeConverter.class)
	private VaiTro vaiTro;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public NguoiDung(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public NguoiDung() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NguoiDung [id=" + id + ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", hoTen=" + hoTen
				+ ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", vaiTro=" + vaiTro + "]";
	}

}

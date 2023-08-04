package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sangdata") // 실제 테이블과 클래스 명이 다르면 적어 준다.
public class SangpumTable {
	@Id
	private int code;
	private String sang;
	private String su;
	@Column(name = "dan") // 멤버변수와 이름 다를 때 써줘야 함
	private String dan;

	protected SangpumTable() {
		// 빈 생성자
	}

	public SangpumTable(int code, String sang, String su, String dan) {
		this.code = code;
		this.sang = sang;
		this.su = su;
		this.dan = dan;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSang() {
		return sang;
	}

	public void setSang(String sang) {
		this.sang = sang;
	}

	public String getSu() {
		return su;
	}

	public void setSu(String su) {
		this.su = su;
	}

	public String getDan() {
		return dan;
	}

	public void setDan(String dan) {
		this.dan = dan;
	}

	public void changeName(String newName) {
		this.sang = newName;
	}
}

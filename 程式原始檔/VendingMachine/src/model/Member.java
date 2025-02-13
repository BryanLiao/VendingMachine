package model;
import java.io.Serializable;
public class Member implements Serializable {
	private int id;
	private String name;
	private String account;
	private String password;
	private String phone;
	private String mobile;
	private String address;
	
	public Member() {
		super();
	}

	public Member(String name, String account, String password, String phone, String mobile, String address) {
		super();
		this.name = name;
		this.account = account;
		this.password = password;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		
	}
	
}

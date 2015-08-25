package wxz.test.json;

import java.util.Date;

public class Student {
	private int id;
	private String name;
	private String email;
	private String address;
	private Birthday birthday;
	
	private Date date = new Date();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Birthday getBirthday() {
		return birthday;
	}
	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Student [address=" + address + ", birthday=" + birthday
				+ ", date=" + date + ", email=" + email + ", id=" + id
				+ ", name=" + name + "]";
	}
	
}

package entity;

import java.util.Date;

//Ñ§ÉúÀà
public class Students {
	
	private String sid;
	private String sname;
	private String gender;
	private Date birthday;
	private String address;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Students(){
		
	}
	public Students(String address, String sname, Date birthday, String gender,
			String sid) {
//		super();
		this.address = address;
		this.sname = sname;
		this.birthday = birthday;
		this.gender = gender;
		this.sid = sid;
	}
	public String toString() {
		return "Students [sid = "+sid+",sname = "+sname+",gender = "+gender+
		",birthday = "+birthday+",address = "+address+"]";
	}
	
	
}

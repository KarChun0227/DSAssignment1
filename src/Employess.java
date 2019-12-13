import java.sql.Date;

public class Employess {

	private int SSN;
	private String name;
	private String address;
	private double salary;
	private String gender;
	private Date BDate;
	
	
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int sSN) {
		SSN = sSN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBDate() {
		return BDate;
	}
	public void setBDate(Date date) {
		BDate = date;
	}
	
	
	
}

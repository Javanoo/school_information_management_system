package mdps_sms;

import java.util.Date;

public abstract class AbstractPerson implements Person {
	private static final long serialVersionUID = 1814959407778504394L;
	private String name;
	private String gender;
	private Date dateOfBirth;
	private String nationality;
	private String role;
	private Contact contact;
	private Date dateRegistered;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Contact getContact() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Date getDateCreated() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getDateRegistered() {
		return dateRegistered;
	}
	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
}
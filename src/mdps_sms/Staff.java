package mdps_sms;

import java.io.Serializable;
import java.util.Date;

public abstract class Staff implements Cloneable, Comparable<Staff>, Serializable {
	private static final long serialVersionUID = 1814959407778504394L;
	final transient String STUDENT_ROLE = "Student";
	final transient String PARENT_ROLE = "Parent";
	final transient String TEACHER_ROLE = "Teacher";
	final transient String STAFF_ROLE = "Staff member";
	final transient String ADMINISTRATOR_ROLE = "Admin";
	
	private String name;
	private String gender;
	private Date dateOfBirth;
	private String nationality;
	private String role;
	private Contact contact;
	private Date dateRegistered;
	private StringBuilder note;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName() + " " + getRole();
	}
	
	@Override
	public int compareTo(Staff o) {
		// TODO Auto-generated method stub
		return getName().compareToIgnoreCase(o.getName());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Date getDateRegistered() {
		return dateRegistered;
	}
	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	
	public StringBuilder getNote() {
		return note;
	}
	public void setNote(StringBuilder note) {
		this.note = note;
	}
}
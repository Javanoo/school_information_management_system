package mdps_sms.util;

import java.io.Serializable;
import java.util.Date;


public abstract class Person implements Cloneable, Comparable<Person>, Serializable {
	private static final long serialVersionUID = 1814959407778504394L;
	private String name = "unknown";
	private String gender = "unknown";
	private String role = "unknown";
	private String qualification = "unknown";
	private String salary = "unknown";
	private String location = "unknown";
	private String[] phone = {"none", "none"};
	private String[] email = {"none", "none"};
	private Date dateRegistered = new Date();
	private String description = "no description";
	
	Person(){}
	
	@Override
	public int compareTo(Person o) {
		return this.getName().compareTo(o.getName());
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * Returns a name plus role string description of this object.
	 * @return string description
	 */
	@Override
	public String toString() {
		return this.getName() + " "  + this.getRole();
	}
	
	/**
	 * Returns the name value assigned to this object
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * updates the name property of this object
	 * @param name the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the role value assigned to this object 
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * updates the role property of this object
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * Returns the qualification value assigned to this object
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}
	
	/**
	 * updates the qualification property assigned to this object
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	/**
	 * Returns the salary value of this object
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}
	
	/**
	 * updates the salary property assigned to this object
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	/**
	 * Returns the location value assigned to this object
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * updates the location property of this object
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Returns an array value consisting of 1 or 2 values. 
	 * These values are phone numbers assigned to this object.
	 * @return the phone array
	 */
	public String getPhone() {
		return phone[0];
	}
	
	/**
	 * updates the phone property assigned to this object.
	 * <p>
	 * The phone value should be an array, consisting of either 1 or 2 values.
	 * if not the phone property of this object won't be updated.
	 * 
	 * @param phone the phone to set
	 */
	public void setPhone(String[] phone) {
		if(phone.length > 0)
			this.phone = phone;
	}
	
	/**
	 * Returns an array value consisting of 1 or 2 values.
	 * These values are email addresses assigned to this object.
	 * 
	 * @return the email array
	 */
	public String getEmail() {
		return email == null ? "none" : email[0];
	}
	
	/**
	 * updates the email property assigned to this object.
	 * <p>
	 * The email value should be an array, consisting of either 1 or 2 values.
	 * if not, the email property of this object won't be updated.
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String[] email) {
		if(phone.length > 0)
			this.email = email;
	}

	
	/**
	 * Returns the date value of when this object was instantiated
	 * @return the dateRegistered
	 */
	public Date getDateRegistered() {
		return dateRegistered;
	}

	/**
	 * updates the dateRegistered property of this object.
	 * @param dateRegistered the dateRegistered to set
	 */
	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	
	/**
	 * Returns the gender value assigned to this object
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * updates the gender property assigned to this object
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	

	/**
	 * Returns a description value assigned to this object
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	

	/**
	 * updates the description property assigned to this object
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
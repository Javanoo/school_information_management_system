/**
 * 
 */
package mdps_sms;

import java.io.Serializable;

/**
 * This establishes the basis of all stakeholder classes
 * look up for abstract classes for partial implementations.
 */
public interface Person extends Cloneable, Comparable<Person>, Serializable {
	String STUDENT_ROLE = "Student";
	String PARENT_ROLE = "Parent";
	String TEACHER_ROLE = "Teacher";
	String STAFF_ROLE = "Staff member";
	
	//return person's name 
	public String getName();
	
	//set a person's name
	public void setName(String name);
	
	//return person's contact info
	public Contact getContact();
	
	//set person's contact info
	public void setContact(Contact contact);
	
	//return's date created
	public java.util.Date getDateCreated();
}
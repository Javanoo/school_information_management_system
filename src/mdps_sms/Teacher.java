/**
 * 
 */
package mdps_sms;

import java.util.Date;
import java.util.LinkedList;

/**
 * 
 */
public class Teacher extends Staff {
	private static final long serialVersionUID = -168455056110625037L;
	private LinkedList<String> subjects = new LinkedList<>();
	private LinkedList<SchoolClass> classRoom = new LinkedList<>();
	
	Teacher(){}
	
	Teacher(String name, String Gender, Date dob, String nationality, Contact contact, StringBuilder note){
		//inherited methods
		setName(name);
		setGender(Gender);
		setDateOfBirth(dob);
		setNationality(nationality);
		setRole(STUDENT_ROLE);
		setContact(contact);
		setDateRegistered(new Date());
		setNote(note);
	}
	
	public LinkedList<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(LinkedList<String> subjects) {
		this.subjects = subjects;
	}

	public LinkedList<SchoolClass> getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(LinkedList<SchoolClass> classRoom) {
		this.classRoom = classRoom;
	}
}

/**
 * 
 */
package mdps_sms.util;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * 
 */
public class Teacher extends Person {
	private static final long serialVersionUID = -168455056110625037L;
	private TreeSet<String> subjects = new TreeSet<>();
	private TreeSet<SchoolClass> classroom = new TreeSet<>();
	
	Teacher(){}
	
	Teacher(String name, String gender, String[] subjects, String qualification, SchoolClass[] classroom, 
			String location, String[] phone, String[] email, String salary, String description){
		this.setName(name);
		this.setGender(gender);
		this.setRole("Teacher");
		this.setSubjects(new TreeSet<>(Arrays.asList(subjects)));
		this.setQualification(qualification);
		this.setClassroom(new TreeSet<>(Arrays.asList(classroom)));
		this.setLocation(location);
		this.setSalary(salary);
		this.setPhone(phone);
		this.setEmail(email);
		this.setSalary(salary);
		this.setDescription(description);
		this.setDateRegistered(new Date());
	}
	
	public synchronized void addSubject(String subject) {
		getSubjects().add(subject);
	}
	public synchronized void removeSubject(String subject) {
		getSubjects().remove(subject);
	}
	
	public synchronized void addClassroom(SchoolClass classroom) {
		getClassroom().add(classroom);
	}
	public synchronized void removeClassroom(SchoolClass classroom) {
		classroom.getTeachers().remove(this);
		getClassroom().remove(classroom);
	}

	/**
	 * @return the subjects
	 */
	public TreeSet<String> getSubjects() {
		return subjects;
	}

	/**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(TreeSet<String> subjects) {
		this.subjects = subjects;
	}

	/**
	 * @return the classRoom
	 */
	public TreeSet<SchoolClass> getClassroom() {
		return classroom;
	}

	/**
	 * @param classRoom the classRoom to set
	 */
	public void setClassroom(TreeSet<SchoolClass> classRoom) {
		this.classroom = classRoom;
	}
}

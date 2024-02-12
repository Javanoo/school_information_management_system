/**
 * 
 */
package mdps_sms;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
 */
public class SchoolClass implements Cloneable, Comparable<SchoolClass>, Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	LinkedList<Student> students = new LinkedList<>();
	LinkedList<String> subjects = new LinkedList<>();
	LinkedList<Teacher> teachers = new LinkedList<>();
	
	SchoolClass(){
		setName("0");
	}
	
	SchoolClass(String name, LinkedList<String> subjects, LinkedList<Student> students){
		this.setName(name);
		this.subjects = subjects;
		this.students = students;
	}
	
	SchoolClass(String name, LinkedList<String> subjects, LinkedList<Student> students,
			LinkedList<Teacher> teachers){
		this.setName(name);
		this.subjects = subjects;
		this.students = students;
		this.teachers = teachers;
	}
	
	public LinkedList<Student> getStudents(){
		return students;
	}
	
	public void setStudents(LinkedList<Student> students){
		this.students = students;
	}
	
	public LinkedList<String> getSubjects(){
		return subjects;
	}
	
	public void setSubjects(LinkedList<String> subjects){
		this.subjects = subjects;
	}
	
	public LinkedList<Teacher> getTeachers(){
		return teachers;
	}
	
	public void setTeachers(LinkedList<Teacher> teachers){
		this.teachers = teachers;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(SchoolClass o) {
		// TODO Auto-generated method stub
		return o.getName().compareTo(name);
	}
	
	@Override
	public String toString() {
		String summary = "";
		summary = this.getName() + "\n" +  students.size() + " students\n" + 
					teachers.size() + " teachers\n" + subjects.size() + 
					" subjects";
		return summary;
	}
	
}

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
	private LinkedList<Student> students = new LinkedList<>();
	private LinkedList<String> subjects = new LinkedList<>();
	private LinkedList<Teacher> teachers = new LinkedList<>();
	
	SchoolClass(){
		setName("Unknown");
	}
	
	SchoolClass(String name, LinkedList<String> subjects, LinkedList<Teacher> teachers){
		setName(name);
		setSubjects(subjects);
		setTeachers(teachers);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Student> getStudents() {
		return students;
	}

	public void setStudents(LinkedList<Student> students) {
		this.students = students;
	}

	public LinkedList<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(LinkedList<String> subjects) {
		this.subjects = subjects;
	}

	public LinkedList<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(LinkedList<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public int compareTo(SchoolClass o) {
		// TODO Auto-generated method stub
		return getName().compareToIgnoreCase(o.getName());
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
//

/**
 * 
 */
package mdps_sms.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * 
 */
public class SchoolClass implements Cloneable, Comparable<SchoolClass>, Serializable {
	private static final long serialVersionUID = 1L;
	private String name = "unknown";
	private long fees = 0l; 
	private TreeSet<Student> students = new TreeSet<>();
	private TreeSet<String> subjects = new TreeSet<>();
	private TreeSet<String> freeSubjects = new TreeSet<>();
	private TreeSet<Teacher> teachers = new TreeSet<>();
	
	SchoolClass(){}
	
	SchoolClass(String name, long fees, TreeSet<String> subjects, TreeSet<Student> students, TreeSet<Teacher> teachers){
		setName(name);
		setFees(fees);
		setSubjects(subjects);
		setFreeSubjects(new TreeSet<>(subjects));
	}
	
	public boolean addStudent(Student student) {
		return students.add(student);
	}
	
	public boolean removeStudent(Student student) {
		return students.remove(student);
	}
	
	public boolean addTeacher(Teacher teacher, String...subjects) {
		if(this.freeSubjects.containsAll(Arrays.asList(subjects))) {
				freeSubjects.removeAll(Arrays.asList(subjects));
			return teachers.add(teacher);
		}
		return false;
	}
	
	public boolean removeTeacher(Teacher teacher) {
		freeSubjects.addAll(teacher.getSubjects());
		return teachers.remove(teacher);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeSet<Student> getStudents() {
		return students;
	}

	public void setStudents(TreeSet<Student> students) {
		this.students = students;
	}

	public TreeSet<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(TreeSet<String> subjects) {
		this.subjects = subjects;
	}

	public TreeSet<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(TreeSet<Teacher> teachers) {
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

	/**
	 * @return the fees
	 */
	public synchronized long getFees() {
		return fees;
	}

	/**
	 * @param fees the fees to set
	 */
	public synchronized void setFees(long fees) {
		this.fees = fees;
	}

	/**
	 * @return the freeSubjects
	 */
	public synchronized TreeSet<String> getFreeSubjects() {
		return freeSubjects;
	}

	/**
	 * @param freeSubjects the freeSubjects to set
	 */
	public synchronized void setFreeSubjects(TreeSet<String> freeSubjects) {
		this.freeSubjects = freeSubjects;
	}
}
//

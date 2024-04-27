/**
 *
 */
package mdps_sms.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 */
public class SchoolClass implements Cloneable, Comparable<SchoolClass>, Serializable {
	private static final long serialVersionUID = 1L;
	private String name = "unknown";
	private long fees = 0l;
	private ArrayList<Student> students = new ArrayList<>();
	private ArrayList<String> subjects = new ArrayList<>();
	private String subjectRep = "no subjects";
	private ArrayList<String> freeSubjects = new ArrayList<>();
	private ArrayList<Teacher> teachers = new ArrayList<>();
	private int numberOfStudents = students.size();
	private int numberOfTeachers = teachers.size();

	public SchoolClass(){}

	public SchoolClass(String name, long fees, ArrayList<String> subjects){
		setName(name);
		setFees(fees);
		setSubjects(subjects);
		setFreeSubjects(new ArrayList<>(subjects));

		if(subjects.size() != 0) {
			subjectRep = "";
			for(String elem : subjects) subjectRep += elem + ", ";
			subjectRep.stripTrailing();
			subjectRep = subjectRep.substring(0, subjectRep.lastIndexOf(','));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
	}

	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public int compareTo(SchoolClass o) {
		// TODO Auto-generated method stub
		return getName().compareTo(o.getName());
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
	public synchronized ArrayList<String> getFreeSubjects() {
		return freeSubjects;
	}

	/**
	 * @param freeSubjects the freeSubjects to set
	 */
	public synchronized void setFreeSubjects(ArrayList<String> freeSubjects) {
		this.freeSubjects = freeSubjects;
	}

	/**
	 * @return the subjectRep
	 */
	public synchronized String getSubjectRep() {
		return subjectRep;
	}

	/**
	 * @param subjectRep the subjectRep to set
	 */
	public synchronized void setSubjectRep(String subjectRep) {
		this.subjectRep = subjectRep;
	}

	/**
	 * @return the numberOfStudents
	 */
	public synchronized String getNumberOfStudents() {
		numberOfStudents = students.size();
		return numberOfStudents + "";
	}

	/**
	 * @return the numberOfTeachers
	 */
	public synchronized String getNumberOfTeachers() {
		numberOfTeachers = teachers.size();
		return numberOfTeachers + "";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SchoolClass) {
			return name.equals(((SchoolClass)obj).getName());
		}else return this == obj;
	}
}
//

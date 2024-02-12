/**
 * 
 */
package mdps_sms;

import java.util.Date;

/**
 * This is for the student module
 */
public class Student extends AbstractPerson {
	private static final long serialVersionUID = -4057680552881492210L;
	private Date dateOfBirth;
	
	private Parent parents;
	
	private StringBuilder note;
	private SchoolClass classRoom;
	private Date yearJoined;
	
	/*
	 * private AcademicRecord schoolProgression;
	 */
	
	public Student(String name, Parent parents){
		//TODO required constructor
		setRole(STUDENT_ROLE);
		this.name = name;
		this.parents = parents;
		parents.addChild(this);
		classRoom	= null;
	}
	
	public Student(String name, Parent parents, SchoolClass sClass){
		//TODO required constructor
		setRole(STUDENT_ROLE);
		classRoom	= sClass;
		this.parents = parents;
	}
	
	public SchoolClass getSchoolClass() {
		//TODO return the students class
		return classRoom;
	}
	
	public void setSchoolClass(SchoolClass sClass) {
		//TODO set the student in a certain class
		classRoom = sClass;
	}
	
	public Parent getParents() {
		//TODO obtain parent's information
		return parents;
	}
	
	public void setParents(Parent parents) {
		//TODO set the parents information
		this.parents = parents;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		String summary;
		summary = getName() + "\nt" + getParents().getName();
		return summary;
	}
	
}

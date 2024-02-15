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
	private SchoolClass classroom = new SchoolClass();
	private Parent[] parents = new Parent[2];
	
	Student() {}
	
	Student(String name, String Gender, Date dob, String nationality, StringBuilder note, Parent parents){
		//inherited methods
		setName(name);
		setGender(Gender);
		setDateOfBirth(dob);
		setNationality(nationality);
		setRole(STUDENT_ROLE);
		setDateRegistered(new Date());
		setNote(note);
		
		//the child's parent
		getParents()[0] = parents;
	}

	public SchoolClass getClassroom() {
		return classroom;
	}

	public void setClassroom(SchoolClass classroom) {
		this.classroom = classroom;
	}

	public Parent[] getParents() {
		return parents;
	}

	public void setParents(Parent[] parents) {
		this.parents = parents;
	}
}

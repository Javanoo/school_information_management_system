/**
 * 
 */
package mdps_sms;

import java.util.Date;

/**
 * This class is used to instantiate objects that hold information about students.
 * <p>
 * The class extends the Person to enable instantiating objects that hold student information, 
 * and disables any inherited methods that have no meaning or value to the student object.
 * 
 * @see Person SchoolClass Parents
 */
public class Student extends Person {
	private static final long serialVersionUID = -4057680552881492210L;
	private SchoolClass classroom = new SchoolClass();
	Parent[] parents = new Parent[2];
	
	
	//no-arg constructor
	Student() {super();}
	
	/**
	 * creates an object with the specified value arg set to their respective properties.
	 * 
	 * @param name
	 * @param gender
	 * @param classroom
	 * @param parents
	 * @param description
	 */
	Student(String name, String gender, SchoolClass classroom, Parent[] parents, String description){
		super();
		this.setName(name);
		this.setGender(gender);
		this.setParents(parents);
		this.setClassroom(classroom);
		this.setDescription(description);
		this.setDateRegistered(new Date());
	}
	
	public synchronized void addParent(Parent parent) {
		if(getParents()[0] == null) {
			getParents()[0] = parent;
		}else if(getParents()[1] == null) {
			getParents()[1] = parent;
		}else {
			System.out.println("No place for more parents.");
		}
	}
	
	public synchronized void removeParent(Parent parent) {
		for(Parent elem : getParents()) {
			if(elem == parent) {
				elem.removeChild(this);
				elem = null;
				break;
			}
		}
		System.out.println("No such parent associated with this student.");
	}

	/**
	 * Returns the class room value assigned to this object
	 * @return the classroom
	 */
	public SchoolClass getClassroom() {
		return classroom;
	}

	/**
	 * updates the class room property of this object.
	 * <p>
	 * When the class room property is updated, the value's students property is also updated.
	 * and if there was a previous value, that previous value is also updated to stop that previous value, 
	 * from recognizing this object as part of its students property value.
	 *  
	 * @param classroom the classroom to set
	 */
	public void setClassroom(SchoolClass classroom) {
		
		//remove student from previous class
		if(getClassroom() != null) {
			getClassroom().getStudents().remove(this);
		}
		
		//add student to new class
		classroom.getStudents().add(this);
		this.classroom = classroom;
	}

	/**
	 * Returns the parents value assigned to this object.
	 * The value returned is an array that holds 1 or 2 parent information of this object
	 * 
	 * @return the parents
	 */
	public Parent[] getParents() {
		return parents;
	}

	/**
	 * updates the parent property of this object.
	 * The parent value to be set to this object's parent property,  
	 * should be an array with 1 or 2 parent objects that hold parent information of this object.
	 * if the value array is empty, this object's parent property won't be update.
	 * 
	 * @param parents the parents to set
	 */
	public void setParents(Parent[] parents) {
		this.setParents(parents);
	}


	@Override
	public String toString() {
		return this.getName() + " from " + this.getClassroom().getName();
	}

	@Override
	public String getQualification() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have qualifications yet.");
	}
	

	@Override
	public void setQualification(String qualification) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have qualifications yet.");
	}
	

	@Override
	public String getSalary() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Student doesn't have salary.");
	}
	

	@Override
	public void setSalary(String salary)throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students doesn't have salary.");
	}
	

	@Override
	public String getLocation() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students doesn't have locations.");
	}
	

	@Override
	public void setLocation(String location) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have locations, instead set their parent's location");
	}
	

	@Override
	public String[] getPhone()throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students doesn't have phones.");
	}
	

	@Override
	public void setPhone(String[] phone) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students doesn't have phones, instead set their parent's phone.");
	}
	

	@Override
	public String[] getEmail() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have emails");
	}
	

	@Override
	public void setEmail(String[] email) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have emails, instead set their Parent's email.");
	}
}

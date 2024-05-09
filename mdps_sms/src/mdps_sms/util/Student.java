/**
 *
 */
package mdps_sms.util;

import java.util.Date;
import java.util.HashMap;

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
	private String codeNumber = "none";
	private SchoolClass classroom = new SchoolClass();
	Parent[] parents = new Parent[2];
	private String parentName = "";
	private long fees = 0;
	private long feesPaid = 0;
	private long feesBalance = fees - feesPaid;
	private HashMap<String, Long> feesPaidEntry = new HashMap<>(); //history of payments.


	//no-arg constructor
	public Student() {super();}

	/**
	 * creates an object with the specified value arg set to their respective properties.
	 *
	 * @param name
	 * @param gender
	 * @param classroom
	 * @param parents
	 * @param description
	 */
	public Student(String name, String codeNumber,String gender, SchoolClass classroom, Parent[] parents, String description){
		super();
		this.setCodeNumber(codeNumber);
		this.setName(name);
		this.setGender(gender);
		this.setRole("Student");
		this.setParents(parents);
		this.setClassroom(classroom);
		this.setDescription(description);
		this.setDateRegistered(new Date());
		this.parentName = getParents()[0].getName();
		feesPaidEntry = new HashMap<>();
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
		//add student to new class
		this.classroom = classroom;
		setFees(classroom.getFees());
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

	public String getParentName() {
		this.parentName = getParents()[0].getName();
		return parentName;
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
		this.parents = parents;
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public String toString() {
		return this.getCodeNumber() + "\t" + this.getName();
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public String getQualification() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have qualifications yet.");
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public void setQualification(String qualification) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have qualifications yet.");
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public String getSalary() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Student doesn't have salary.");
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public void setSalary(String salary)throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students doesn't have salary.");
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public String getLocation() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students doesn't have locations.");
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public void setLocation(String location) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have locations, instead set their parent's location");
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public String getPhone(){
		return (parents[0].getPhone().isBlank()) ? "none" : parents[0].getPhone();
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public void setPhone(String[] phone) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students doesn't have phones, instead set their parent's phone.");
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public String getEmail(){
		return (parents[0].getEmail().isBlank()) ? "none" : parents[0].getEmail();
	}

	/**
	 * This method is disabled for this object, hence throws an exception when called.
	 * @exception UnsupportedOPerationExcepton
	 */
	@Override
	public void setEmail(String[] email) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Students don't have emails, instead set their Parent's email.");
	}

	/**
	 * @return the codeNumber
	 */
	public synchronized String getCodeNumber() {
		return codeNumber;
	}

	/**
	 * @param codeNumber the codeNumber to set
	 */
	public synchronized void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
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
	 * @return the feesPaid
	 */
	public synchronized long getFeesPaid() {
		return feesPaid;
	}

	/**
	 * @param feesPaid the feesPaid to set
	 */
	public synchronized void setFeesPaid(long feesPaid) {
		this.feesPaid += feesPaid;
	}

	/**
	 * @return the feesBalance
	 */
	public synchronized long getFeesBalance() {
		feesBalance = getFees() - getFeesPaid();
		return feesBalance;
	}

	/**
	 * @param feesBalance the feesBalance to set
	 */
	public synchronized void setFeesBalance(long feesBalance) {
		this.feesBalance = feesBalance;
	}

	/**
	 * @return the feesPaidEntry
	 */
	public HashMap<String, Long> getFeesPaidEntry() {
		return feesPaidEntry == null ? new HashMap<>() : feesPaidEntry;
	}

	/**
	 * @param feesPaidEntry the feesPaidEntry to set
	 */
	public void setFeesPaidEntry(HashMap<String, Long> feesPaidEntry) {
		this.feesPaidEntry = feesPaidEntry;
		feesPaid = 0;
		for(Long elem : feesPaidEntry.values()) feesPaid += elem;
	}
}
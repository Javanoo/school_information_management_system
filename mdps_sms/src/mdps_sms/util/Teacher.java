/**
 *
 */
package mdps_sms.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 */
public class Teacher extends Person {
	private static final long serialVersionUID = -168455056110625037L;
	private HashMap<SchoolClass, ArrayList<String>> class_subject = new HashMap<>();
	private String classes = "not allocated";

	public Teacher(){}

	public Teacher(String name, String gender, String location, String[] phone, String[] email, String role,
			String qualification, HashMap<SchoolClass, ArrayList<String>> class_subject, String salary, String accountAdmin, String accountNumber, String description){
		this.setName(name);
		this.setGender(gender);
		this.setRole("Teacher");
		this.setQualification(qualification);
		this.setClass_subject(class_subject);
		this.setLocation(location);
		this.setAccountAdmin(accountAdmin);
		this.setAccountNumber(accountNumber);
		this.setSalary(salary);
		this.setPhone(phone);
		this.setEmail(email);
		this.setSalary(salary);
		this.setDescription(description);
		this.setDateRegistered(new Date());
		updateClasses();
	}

	/**
	 * @return the class_subject
	 */
	public synchronized HashMap<SchoolClass, ArrayList<String>> getClass_subject() {
		return class_subject;
	}

	/**
	 * @param class_subject the class_subject to set
	 */
	public synchronized void setClass_subject(HashMap<SchoolClass, ArrayList<String>> class_subject) {
		this.class_subject = class_subject;
	}
	
	public void updateClasses() {
		if(class_subject.keySet() != null && class_subject.keySet().size() != 0) {
			classes = "";
			for(SchoolClass elem : class_subject.keySet()) {
				classes += elem.getName() + ", ";
			}
			classes.stripTrailing();
			classes = classes.substring(0, classes.lastIndexOf(','));
		}else classes = "not allocated";
	}

	/**
	 * @return the classes
	 */
	public String getClasses() {
		if(class_subject.keySet() != null && class_subject.keySet().size() != 0) {
			classes = "";
			for(SchoolClass elem : class_subject.keySet()) {
				classes += elem.getName() + ", ";
			}
			classes.stripTrailing();
			classes = classes.substring(0, classes.lastIndexOf(','));
		}
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	@Override
	public boolean equals(Object obj) {

		//if obj is instance of this class, check equality based on name reference.
		if(obj instanceof Teacher) return getName().equals(((Teacher)obj).getName());

		//else based on obj reference.
		return this == obj;
	}
}

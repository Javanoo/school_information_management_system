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
	private String classes = "none";

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
		if(class_subject.keySet() != null && class_subject.keySet().size() != 0) {
			classes = "";
			for(SchoolClass elem : class_subject.keySet()) {
				classes += elem.getName() + ", ";
			}
			classes.stripTrailing();
			classes = classes.substring(0, classes.lastIndexOf(','));
		}
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

	/**
	 * @return the classes
	 */
	public String getClasses() {
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(String classes) {
		this.classes = classes;
	}
}

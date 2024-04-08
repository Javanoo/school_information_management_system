package mdps_sms.util;

import java.util.Date;

public class Staff extends Person {

	private static final long serialVersionUID = -6909844068228496330L;

	public Staff() {}
	
	public Staff(String name, String gender, String location, String[] phone, String[] email, String role, 
			String qualification, String salary, String description){
		setName(name);
		setGender(gender);
		setLocation(location);
		setPhone(phone);
		setEmail(email);
		setRole(role);
		setQualification(qualification);
		setSalary(salary);
		setDescription(description);
		setDateRegistered(new Date());
	}
}

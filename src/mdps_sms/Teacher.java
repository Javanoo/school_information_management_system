/**
 * 
 */
package mdps_sms;

import java.util.LinkedList;

/**
 * 
 */
public class Teacher extends AbstractPerson {
	private static final long serialVersionUID = -168455056110625037L;
	private LinkedList<String> subjects = new LinkedList<>();
	private LinkedList<SchoolClass> sClass = new LinkedList<>();
	
	Teacher(){
		super();
		setRole(TEACHER_ROLE);
	}
		
	Teacher(Contact contact){
		super(contact);
		setRole(TEACHER_ROLE);
	}
	
	Teacher(Contact contact, SchoolClass sClass){
		super(contact);
		setRole(TEACHER_ROLE);
		this.sClass.add(sClass);
	}
	
	Teacher(Contact contact, SchoolClass sClass, String subject){
		super(contact);
		setRole(TEACHER_ROLE);
		subjects.add(subject);
		this.sClass.add(sClass);
	}
}

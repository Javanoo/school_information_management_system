 package mdps_sms.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import mdps_sms.Main;

public class SchoolCalendar implements Comparable<SchoolCalendar>, Serializable {
	private static final long serialVersionUID = 4373794517277104955L;
	private TreeSet<DayEntry> dates = new TreeSet<>();
	private TreeSet<SchoolCalendar.ExamEntry> examDates = new TreeSet<>();
	private String name = "School Calendar";

	public SchoolCalendar(){}

	public SchoolCalendar(LocalDate startDate, LocalDate endDate){

		Date start = java.sql.Date.valueOf(startDate);
        Date end = java.sql.Date.valueOf(endDate);

        LocalDate currentDate = startDate;
		 while (!currentDate.isAfter(endDate)) {
	            dates.add(new DayEntry(java.sql.Date.valueOf(currentDate)));
	            currentDate = currentDate.plusDays(1); // Move to the next day
	     }
		 
		 updateExamDates();
	}
	
	public void updateExamDates() {
		Iterator<DayEntry> iter = dates.iterator();
		
		examDates.clear();
		while(iter.hasNext()) {
			SchoolCalendar.DayEntry day = iter.next();
			if(day.getStatus().equals("Exams day"))
				examDates.add(new ExamEntry(day.getDate()));
		}
	};

	/**
	 * @return the dates
	 */
	public synchronized TreeSet<DayEntry> getDates() {
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public synchronized void setDates(TreeSet<DayEntry> dates) {
		this.dates = dates;
	}

	/**
	 * @return the name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(SchoolCalendar o) {
		// TODO Auto-generated method stub
		return getName().compareTo(o.getName());
	}

	////////////////////////////////////////////////////////////////////////////////
	public class DayEntry implements Comparable<DayEntry>, Serializable{
		private static final long serialVersionUID = -6454966562984699387L;
		private Date date = new Date();
		private String dayType = "School Day";
		private TreeSet<String> events = new TreeSet<>();
		private String event = "class lessons.";
		private String description = "Nothing unusual, just a normal Day.";
		private String dateRepresentation = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE\ndd\nMMM");

		public DayEntry(){}

		public DayEntry(Date day){
			this.date = day;
			dateRepresentation = dateFormat.format(day);
			if(day.getDay() == 0 || day.getDay() == 6) {
				dayType = "";
				event = "";
				description = "";
				dateRepresentation =  day.getDay() == 0 ? "SUNDAY" : "SATURDAY";
			}
		}

		@Override
		public String toString() {
			return dateRepresentation + "     " + event;
		}

		/**
		 * @return the date
		 */
		public synchronized Date getDate() {
			return date;
		}

		/**
		 * @return the date
		 */
		public synchronized String getDateRep() {
			return dateRepresentation;
		}

		/**
		 * @return the status
		 */
		public synchronized String getStatus() {
			return dayType;
		}

		/**
		 * @param status the status to set
		 */
		public synchronized void setStatus(String status) {
			this.dayType = status;
		}

		/**
		 * @return the events
		 */
		public synchronized TreeSet<String> getEvents() {
			return events;
		}

		/**
		 * @param events the events to set
		 */
		public synchronized void setEvents(TreeSet<String> events) {
			setEvent(events.size() == 0 ? "no events" : events.first());
			this.events = events;
		}

		/**
		 * @return the event
		 */
		public synchronized String getEvent() {
			return event;
		}

		/**
		 * @param event the event to set
		 */
		public synchronized void setEvent(String event) {
			this.event = event;
		}

		/**
		 * @return the dayType
		 */
		public synchronized String getDayType() {
			return dayType;
		}

		/**
		 * @param dayType the dayType to set
		 */
		public synchronized void setDayType(String dayType) {
			this.dayType = dayType;
		}

		/**
		 * @return the description
		 */
		public synchronized String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public synchronized void setDescription(String description) {
			this.description = description;
		}

		/**
		 * @return the dateRepresentation
		 */
		public synchronized String getDateRepresentation() {
			return dateRepresentation;
		}

		@Override
		public int compareTo(DayEntry o) {
			// TODO Auto-generated method stub
			return this.date.compareTo(o.date);
		}
	}
	
	public class ExamEntry implements Comparable<ExamEntry>, Serializable{
		private static final long serialVersionUID = -6454966562984699387L;
		private Date date = new Date();
		private String supervisor = " ";
		private String  time = "no subject(s) set";
		private String otherTime = "no subject(s) set";
		private String dateRepresentation = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE\ndd\nMMM");

		public ExamEntry(){}

		public ExamEntry(Date day){
			this.date = day;
			dateRepresentation = dateFormat.format(day);
		}

		@Override
		public String toString() {
			return dateRepresentation;
		}

		/**
		 * @return the date
		 */
		public synchronized Date getDate() {
			return date;
		}

		/**
		 * @return the date
		 */
		public synchronized String getDateRep() {
			return dateRepresentation;
		}

		/**
		 * @return the status
		 */
		public synchronized String getSupervisor() {
			return supervisor;
		}

		/**
		 * @param supervisor the status to set
		 */
		public synchronized void setSupervisor(String supervisor) {
			this.supervisor = supervisor;
		}

		/**
		 * @return the events
		 */
		public synchronized String getTime() {
			return time;
		}

		/**
		 * @param events the events to set
		 */
		public synchronized void setTime(String time) {
			this.time = (time.length() == 0 ? "no subject(s) set" : time);
		}

		/**
		 * @return the dayType
		 */
		public synchronized String getOtherTime() {
			return otherTime;
		}

		/**
		 * @param dayType the dayType to set
		 */
		public synchronized void setOtherTime(String time) {
			this.otherTime = (time.length() == 0 ? "no subject(s) set" : time);
		}

		/**
		 * @return the dateRepresentation
		 */
		public synchronized String getDateRepresentation() {
			return dateRepresentation;
		}

		public int compareTo(SchoolCalendar.ExamEntry o) {
			// TODO Auto-generated method stub
			return this.date.compareTo(o.date);
		}
	}

	public TreeSet<SchoolCalendar.ExamEntry> getExamDates() {
		return examDates;
	}

	public void setExamDates(TreeSet<SchoolCalendar.ExamEntry> examDates) {
		this.examDates = examDates;
	}
}
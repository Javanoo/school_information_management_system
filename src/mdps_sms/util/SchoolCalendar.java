package mdps_sms.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

public class SchoolCalendar implements Comparable<SchoolCalendar>, Serializable {
	private static final long serialVersionUID = 4373794517277104955L;
	private TreeSet<DayEntry> dates = new TreeSet<>();
	private String name = "School Calendar";
	
	public SchoolCalendar(){}
	
	public SchoolCalendar(int months){
		Calendar today = Calendar.getInstance();
		
		Calendar endDay = Calendar.getInstance();
		endDay.add(Calendar.MONTH, months);
		
		while(today.before(endDay)) {
			dates.add(new DayEntry(today.getTime()));
			today.add(Calendar.DATE, 1);
		}
	}
	
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
	public class DayEntry implements Comparable<DayEntry>{
		private Date date = new Date();
		private String dayType = "School Day";
		private TreeSet<String> events = new TreeSet<String>();
		private String event = "class lessons.";
		private String description = "Nothing unusual, just a normal Day.";
		private String dateRepresentation = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM");
		
		public DayEntry(){}
		
		public DayEntry(Date day){
			this.date = day;
			dateRepresentation = dateFormat.format(day);
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
}
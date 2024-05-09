package mdps_sms.util;

import java.io.FileOutputStream;
import java.io.*;
import java.util.TreeSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPrinter {
	
	public static String path = "";
	public static Document document = new Document();
	public static Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
	
	
	public PdfPrinter() throws Exception{
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File("me")));
			Chunk chunk = new Chunk("Hey", font);
			
			//open document
			document.open();
			document.newPage();
			
			
			
			
			
			
			document.add(new Paragraph("Hello please"));
			document.close();
		
		}catch(FileNotFoundException | DocumentException e) {System.out.print(e.getMessage());}
		
	}
	
	//calendar
	public static void printCalendar(SchoolCalendar calendar) {
		System.out.println("Calendar");
	}
	
	//classroom
	public static void printClassroom(SchoolClass classes) {
		System.out.println("Class");
	}
	
	
	//exams and results
	public static void printTimeTable(TreeSet<SchoolCalendar.ExamEntry> dates) {
		System.out.println("exams");
	}
	public static void printResult(Student student) {
		System.out.println("result");
	}
	
	public static void printFeesReport(SchoolClass...classes) {
		System.out.println("fees structure");
	}
	
	//teachers
	public static void printTeacher(Teacher teacher) {
		System.out.println("Teacher");
	}
	
	//students
	public static void printStudent(Student student) {
		System.out.println("student");
	}
	
	//staff personnel
	public static void printStaff(Staff member) {
		System.out.println("staff");
	}
	
	//fleet members
	public static void printFleet(Fleet member) {
		System.out.println("fleet");
	}
	
}

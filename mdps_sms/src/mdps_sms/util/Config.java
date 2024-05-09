package mdps_sms.util;

import java.io.Serializable;

public class Config implements Comparable<Config>, Serializable{

	private static final long serialVersionUID = 5551998945345414301L;
	public String uniqID = (Math.random() * 1000000l) + "";
	public String font = "Inter";
	public int fontSize = 14;
	public String theme = "#232323";
	public int buffer = 1;
	public int backupPeriod = 7;
	public String backupPath = "School.bck";
	
	public int getBackupPeriod() {
		return backupPeriod;
	}

	public void setBackupPeriod(int backupPeriod) {
		this.backupPeriod = backupPeriod;
	}

	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	public String getUniqID() {
		return uniqID;
	}

	public void setUniqID(String uniqID) {
		this.uniqID = uniqID;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Config(){}
	
	@Override
	public int compareTo(Config o) {
		// TODO Auto-generated method stub
		return uniqID.compareTo(o.uniqID);
	}

	public int getBuffer() {
		return buffer;
	}

	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}
	
	
}

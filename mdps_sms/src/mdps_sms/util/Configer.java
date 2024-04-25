package mdps_sms.util;

import java.io.Serializable;

public class Configer implements Serializable{
	private static final long serialVersionUID = 8424705905846740874L;
	Administrator admin = new Administrator();
	String font = "Inter";
	int fontSize = 1;
	String theme = "black on white";
	String accentColor = "grey";
	long bufferSize = 512;
	int numberOfthreads = 4;
	String recoveryMail = "";
	String encrytpionStandard = "";

	public Configer(String name, String email, String newPassword, String font, int fontSize, String theme,
			String accent, long bufferSize, int numberOfthreads, String mail,
			String standard) {
		admin.setName(name);
		admin.setPassword(newPassword);
		admin.setEmail(new String[]{email});

		this.font = font;
		this.fontSize = fontSize;
		this.theme = theme;
		this.accentColor = accent;
		this.bufferSize = bufferSize;
		this.numberOfthreads = numberOfthreads;
		this.recoveryMail = mail;
		this.encrytpionStandard = standard;
	}

	/**
	 * @return the admin
	 */
	public Administrator getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	/**
	 * @return the font
	 */
	public String getFont() {
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public void setFont(String font) {
		this.font = font;
	}

	/**
	 * @return the fontSize
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the accentColor
	 */
	public String getAccentColor() {
		return accentColor;
	}

	/**
	 * @param accentColor the accentColor to set
	 */
	public void setAccentColor(String accentColor) {
		this.accentColor = accentColor;
	}

	/**
	 * @return the bufferSize
	 */
	public long getBufferSize() {
		return bufferSize;
	}

	/**
	 * @param bufferSize the bufferSize to set
	 */
	public void setBufferSize(long bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * @return the numberOfthreads
	 */
	public int getNumberOfthreads() {
		return numberOfthreads;
	}

	/**
	 * @param numberOfthreads the numberOfthreads to set
	 */
	public void setNumberOfthreads(int numberOfthreads) {
		this.numberOfthreads = numberOfthreads;
	}

	/**
	 * @return the recoveryMail
	 */
	public String getRecoveryMail() {
		return recoveryMail;
	}

	/**
	 * @param recoveryMail the recoveryMail to set
	 */
	public void setRecoveryMail(String recoveryMail) {
		this.recoveryMail = recoveryMail;
	}

	/**
	 * @return the encrytpionStandard
	 */
	public String getEncrytpionStandard() {
		return encrytpionStandard;
	}

	/**
	 * @param encrytpionStandard the encrytpionStandard to set
	 */
	public void setEncrytpionStandard(String encrytpionStandard) {
		this.encrytpionStandard = encrytpionStandard;
	}
}

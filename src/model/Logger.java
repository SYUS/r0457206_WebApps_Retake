package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author SYUS
 *
 */
public class Logger {
	private static final String FILEPATH = "";
	private static final String FILENAME_APPEND = "_r0457206_WebApps_Retake";
	private static final String LOG_INFO_TEXT = " [LOGGER:INFO] ";
	private static final String LOG_WARNING_TEXT = " [LOGGER:WARNING] ";
	private static final String LOG_FATAL_TEXT = " [LOGGER:FATAL] ";
	
	private BufferedWriter writer;
	private String timeLog;
	private String timeLogSimple;
	private File logFile;
	
	public Logger() {
		refreshLogTime();
		refreshLogTimeSimple();
		setLogFile(new File(getTimeLog() + FILENAME_APPEND));
		try {
			//Create temporary file
			setLogFile(new File(getTimeLog() + FILENAME_APPEND));
			System.out.println(LOG_INFO_TEXT + "Logfile path: " + getLogFile().getCanonicalPath());
			
			setWriter(new BufferedWriter(new FileWriter(getLogFile())));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public String getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(String timeLog) {
		this.timeLog = timeLog;
	}

	public static String getFilepath() {
		return FILEPATH;
	}

	public static String getFilenameAppend() {
		return FILENAME_APPEND;
	}
	
	public File getLogFile() {
		return logFile;
	}

	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}
	
	public String getTimeLogSimple() {
		return timeLogSimple;
	}

	public void setTimeLogSimple(String timeLogSimple) {
		this.timeLogSimple = timeLogSimple;
	}

	private void refreshLogTime() {
		setTimeLog(new SimpleDateFormat("HH:mm:ss_dd/MM/yyyy").format(Calendar.getInstance().getTime()));
	}
	
	private void refreshLogTimeSimple() {
		setTimeLogSimple(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
	}
	
	public void logInfo(String message){
		refreshLogTimeSimple();
		String output = getTimeLog() + LOG_INFO_TEXT + message;
		
		System.out.println(output);
		writeToLog(output);
	}
	
	public void logWarning(String message){
		refreshLogTimeSimple();
		String output = getTimeLog() + LOG_WARNING_TEXT + message;
		
		System.out.println(output);
		writeToLog(output);
	}

	public void logException(String message){
		refreshLogTimeSimple();
		String output = getTimeLog() + LOG_FATAL_TEXT + message;
		
		System.out.println(output);
		writeToLog(output);
	}
	
	private void writeToLog(String message) {
		try {
			getWriter().write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package common;
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStream;  
import java.io.OutputStreamWriter;  
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
public class JavaShellUtil {  

private static final String basePath = "/tmp/";

private static final String executeShellLogFile = basePath + "executeShell.log";  
   
private boolean executeShell(String shellCommand) throws IOException {  
	boolean success = false;  
	StringBuffer stringBuffer = new StringBuffer();  
	BufferedReader bufferedReader = null;  
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");  
	try {
		stringBuffer.append(dateFormat.format(new Date())).append("Prepare Shell Command!").append(shellCommand).append(" \r\n");  
		Process pid = null;  
		String[] cmd = {"/bin/sh","-c",shellCommand};  
		pid = Runtime.getRuntime().exec(cmd);
		if (pid != null) {  
			stringBuffer.append("PID：").append(pid.toString()).append("\r\n");  
			bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);  
			pid.waitFor();  
		} else {  
			stringBuffer.append("No pid\r\n");  
		}  
		stringBuffer.append(dateFormat.format(new Date())).append("Shell Command Excecute Finish\r\n Result：\r\n");  
		String line = null;  
		while (bufferedReader != null && (line = bufferedReader.readLine()) != null) {  
			stringBuffer.append(line).append("\r\n");  
		}  
	} catch (Exception ioe) {  
		stringBuffer.append("Excecute Shell Command Error：\r\n").append(ioe.getMessage()).append("\r\n");  
	} finally {  
		if (bufferedReader != null) {  
			OutputStreamWriter outputStreamWriter = null;  
			try {  
				bufferedReader.close();  
				OutputStream outputStream = new FileOutputStream(executeShellLogFile);  
				outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");  
				outputStreamWriter.write(stringBuffer.toString());  
				System.out.println(stringBuffer.toString());
			} catch (Exception e) {  
				e.printStackTrace();  
			} finally {  
				outputStreamWriter.close();  
			}  
		}  
		success = true;  
	}  
	return success;  
	}
	public static boolean execute(String shellCommand) throws Exception {  
		JavaShellUtil shellutil = new JavaShellUtil();
		try{
			return shellutil.executeShell(shellCommand);
		}catch(Exception e){
			throw(e);
		}	
	}
	public static void main(String args[]) throws IOException{
		JavaShellUtil t = new JavaShellUtil();
		String commond = "date";
		try{
			System.out.println(t.executeShell(commond));
		}catch(Exception e){
			throw(e);
		}
	}
}
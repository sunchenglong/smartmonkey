package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListDevices {
	public static int MAX_DEVICE = 100;
	public String[] deviceId = new String[MAX_DEVICE];
	public String[] deviceName = new String[MAX_DEVICE];;
	public int devicenum;
	public ListDevices(){
		String[] cmd = {"/Users/suncl/android/android-sdk-macosx/platform-tools/adb","devices"};
		String[] result = null;
		try {
			Process pid = Runtime.getRuntime().exec(cmd);
			BufferedReader reader = new BufferedReader(new InputStreamReader(pid.getInputStream()));
			String line = null;
			int devicenum = 0;
			while ((line = reader.readLine()) != null){
				if(devicenum!=0 && !line.isEmpty()){
					deviceId[devicenum] = line.split("\t")[0];
					deviceName[devicenum] = line.split("\t")[1];
					System.out.println(devicenum+": "+line);
				}
				devicenum ++;
			}
			this.devicenum = devicenum;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getdeviceId(){
		String result = null;
		int id = new Scanner(System.in).nextInt();
		if(id > 0 && id <= devicenum){
			result = deviceId[id];
		}
		return result;
	}
	
	/**
	 * 正则表达式测试
	 */
	public static void  testRegex(){
		String str="1234.123";
		Pattern pattern = Pattern.compile("(\\d+\\.\\d+)");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
            System.out.println(matcher.group(0));
        }
	}
	public static void main(String argv[]){
		ListDevices ls = new  ListDevices();
		String result = ls.getdeviceId();
		System.out.println(result);
	}
}

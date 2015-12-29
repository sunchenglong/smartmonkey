package com.monkeyrunner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.android.chimpchat.adb.AdbBackend;
import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.monkeyrunner.*;

import templatematch.MatchInterface;
import templatematch.MatchResult;
public class testMain {
	private static String basePath = "testPic/jd/";
	private static String settings = basePath +"settings";
	private static AdbChimpDevice device;
	private static AdbBackend adb;
	public static void main(String[] args) {
		System.out.println("start!");
		if (adb==null){ 
			adb = new AdbBackend(); 
		    device = (AdbChimpDevice) adb.waitForConnection(800,"MB309MG27945");
		} 
		File file = new File(settings);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				String operate = line.split("\t")[0]; 
				String targetfile = line.split("\t")[1];
				String srcfile = "SRC_"+targetfile;
				int startx = Integer.parseInt(line.split("\t")[2]);
				int starty = Integer.parseInt(line.split("\t")[3]);
				int endx = Integer.parseInt(line.split("\t")[4]);
				int endy = Integer.parseInt(line.split("\t")[5]);
				int scalex = Integer.parseInt(line.split("\t")[6]);
				int scaley = Integer.parseInt(line.split("\t")[7]);
				device.takeSnapshot().writeToFile(basePath+srcfile,"png");
				MatchInterface tool = new MatchInterface
						(basePath+srcfile,basePath+targetfile,startx,starty,endx,endy,scalex,scaley);
				MatchResult result = tool.getMatchResult();
				System.out.println(result.startx+result.width/2);
				System.out.println(result.starty+result.height/2);
				if(operate.equals("click"));{
					device.touch(result.startx+result.width/2,result.starty+result.height/2,com.android.chimpchat.core.TouchPressType.DOWN_AND_UP);
					try{
					    Thread thread = Thread.currentThread();
					    thread.sleep(2000);//暂停1.5秒后程序继续执行
					}catch (InterruptedException e) {
					    // TODO Auto-generated catch block
					    e.printStackTrace();
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		adb.shutdown();
		System.out.println("Finished!");
	}
}
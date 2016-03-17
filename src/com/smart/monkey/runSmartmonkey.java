package com.smart.monkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.android.chimpchat.adb.AdbBackend;
import com.android.chimpchat.adb.AdbChimpDevice;
import com.templatematch.MatchInterface;
import com.templatematch.MatchResult;
/**
 * smart monkey 入口类
 * smart monkey
 *
 */
public class runSmartmonkey {
	private static AdbChimpDevice device;
	private static AdbBackend adb;
	public static void main(String[] args) {
		if(args.length == 2){
			String basePath = args[0];
			String deviceId = args[1];
			System.out.println("Run: "+basePath+"/settings.");
			System.out.println("DeviceId: "+deviceId);
			run(basePath,deviceId);
		}else{
			System.out.println("Need two arguments - basePath  - deviceCode");
		}
		
	}
	/*
	 * 测试方法
	 */
	public void run(String deviceId,int maxstep,int knum,int maxtime){
		//run("test","test");
		System.out.println("Start Smart Monkey!");
	}
	
	public static void run(String basePath,String deviceId) {
		String settings = basePath +"settings";
		System.out.println("start!");
		if (adb==null){ 
			adb = new AdbBackend(); 
		    device = (AdbChimpDevice) adb.waitForConnection(8000,deviceId);
		} 
		File file = new File(settings);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				/*
				 * 获取脚本信息
				 */
				String operate = line.split("\t")[0]; 
				String targetfile = line.split("\t")[1];
				String srcfile = "SRC_"+targetfile;
				int startx = Integer.parseInt(line.split("\t")[2]);
				int starty = Integer.parseInt(line.split("\t")[3]);
				int endx = Integer.parseInt(line.split("\t")[4]);
				int endy = Integer.parseInt(line.split("\t")[5]);
				int scalex = Integer.parseInt(line.split("\t")[6]);
				int scaley = Integer.parseInt(line.split("\t")[7]);
				/*
				 * 读取原始图像
				 */
				device.takeSnapshot().writeToFile(basePath+srcfile,"png");
				/*
				 * 执行图像匹配算法
				 */
				MatchInterface tool = new MatchInterface
						(basePath+srcfile,basePath+targetfile,startx,starty,endx,endy,scalex,scaley);
				MatchResult result = tool.getMatchResult();
				System.out.println(result.startx+result.width/2);
				System.out.println(result.starty+result.height/2);
				if(operate.equals("click")){
					/*
					 * 执行命令
					 */
					device.touch(result.startx+result.width/2,result.starty+result.height/2,com.android.chimpchat.core.TouchPressType.DOWN_AND_UP);
					/*
					 * 等待片刻，继续进行
					 */
					try{
					    Thread thread = Thread.currentThread();
					    thread.sleep(2000);
					}catch (InterruptedException e) {
					    e.printStackTrace();
					}
				}
				else if(operate.equals("drag")){
					device.drag(result.startx,result.starty,
							result.startx+result.width,result.starty+result.height,2,3);
					try{
					    Thread thread = Thread.currentThread();
					    thread.sleep(2000);
					}catch (InterruptedException e) {
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

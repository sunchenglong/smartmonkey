package com.smart.monkey;

import java.util.Scanner;

import com.saliency.SaliencyUtils;

import common.ListDevices;
/**
 * 
 * 简单控制台入口
 *
 */
public class runMain {
	public static void run(){
		runMain _run = new runMain();
		_run.printInfo();
		_run.getCommand();
	}
	public void printInfo(){
		System.out.println("========================================================");
		System.out.println("===============Welcome Smart Monkey!====================");
		System.out.println("Choose a command ...");
		System.out.println("1. List devices");
		System.out.println("2. Image Saliency detection");
		System.out.println("3. Image match Test");	
		System.out.println("4. Smart Monkey run");
		System.out.println("5. Image Script run");
		System.out.println("0. Exit!");
	}
	public void getCommand(){
		try {
			int id = new Scanner(System.in).nextInt();
			System.out.println("###################################################");
			System.out.println("Your input:  "+id);
			if(id == 1)
				new  ListDevices();
			else if(id == 0) {
				System.out.println("======================Thank you!========================");
				System.out.println("========================================================");
				System.exit(-1);
			}else if(id == 2){
				imageSaliencyDetection();
			}else if(id == 3){
				imageMatch();
			}else if(id == 4){
				smartMonkey();
			}else if(id == 5){
				imageScript();
			}
			else{
				System.out.println("Please input the right id!");
			}
			System.out.println("###################################################");
		}catch(Exception e){
			System.out.println("Please input the right id!");
		}
	}
	public static void imageSaliencyDetection(){
		System.out.println("Please input: inputImagePath resultImagePath	knum method[kmeans or random]");
		try{
			Scanner scan = new Scanner(System.in);
			String sourcePath = scan.next();	
			String resultPath = scan.next();
			int k_num = scan.nextInt();
			String method = scan.next();
			new SaliencyUtils(sourcePath,resultPath,k_num,method);
		}catch(Exception e){
			System.out.println("Please input the right Parameters!");
		}
	}
	public static void imageMatch(){
		System.out.println("Please input: inputImagePath	");
		try{
			Scanner scan = new Scanner(System.in);
			String sourcePath = scan.next();	
			String resultPath = scan.next();
			int k_num = scan.nextInt();
			String method = scan.next();
			new SaliencyUtils(sourcePath,resultPath,k_num,method);
		}catch(Exception e){
			System.out.println("Please input the right Parameters!");
		}
	}
	public static void smartMonkey(){
		System.out.println("Please input: inputImagePath	");
		try{
			System.out.println("Please Choose deviceid:");
			ListDevices device = new  ListDevices();
			String deviceId = device.getdeviceId();
			System.out.println("Please input: maxstep knum maxtime[ms]");
			Scanner scan = new Scanner(System.in);
			int maxstep = scan.nextInt();
			int k_num = scan.nextInt();
			int maxtime = scan.nextInt();
			new runSmartmonkey().run(deviceId, maxstep, k_num, maxtime);
		}catch(Exception e){
			
		}
	}
	public static void imageScript(){
		try{
			System.out.println("Please Choose deviceid:");
			ListDevices device = new  ListDevices();
			String deviceId = device.getdeviceId();
			System.out.println("Please input: ScriptPath");
			String basePath = new Scanner(System.in).next();
			new runImageScript().run(basePath, deviceId);;
		}catch(Exception e){
			System.out.println("Please input the right Parameters!");
		}
	}
	public static void main(String argv[]){
		while(true)
			run();
	}
}

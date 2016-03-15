package com.monkeyrunner;

import java.util.Scanner;

import common.ListDevices;

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
		System.out.println("2. List devices");
		System.out.println("0. Exit!");
	}
	public void getCommand(){
		try {
			int id = new Scanner(System.in).nextInt();
			System.out.println("###################################################");
			System.out.print("Your input:  "+id);
			if(id == 1)
				new  ListDevices();
			else if(id == 0) {
				System.out.println("======================Thank you!========================");
				System.out.println("========================================================");
				System.exit(-1);
			}
			else{
				System.out.println("Please input the right id!");
			}
			System.out.println("###################################################");
		}catch(Exception e){
			System.out.println("Please input the right id!");
		}
	}
	public static void main(String argv[]){
		while(true)
			run();
	}
}

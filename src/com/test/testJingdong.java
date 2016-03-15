package com.test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.android.chimpchat.adb.AdbBackend;
import com.android.chimpchat.adb.AdbChimpDevice;
import com.templatematch.MatchInterface;
import com.templatematch.MatchResult;
/**
 * 针对京东这个应用的测试程序
 * 单独一个操作的测试
 */
public class testJingdong {
	private static AdbChimpDevice device;
	private static AdbBackend adb;
	public static void main(String[] args) {
		System.out.println("start!");
		 // TODO Auto-generated method stub
		if (adb==null){ 
			adb = new AdbBackend(); 
		    device = (AdbChimpDevice) adb.waitForConnection(800,"MB309MG27945");
		} 
		device.takeSnapshot().writeToFile("testPic/jd/source1.png","png");
		MatchInterface tool = new MatchInterface("testPic/jd/source1.png","testPic/jd/1.png",374,403,464,521,480,800);
		MatchResult result = tool.getMatchResult();
		System.out.println(result.startx+result.width/2);
		System.out.println(result.starty+result.height/2);
		device.touch(result.startx+result.width/2,result.starty+result.height/2,com.android.chimpchat.core.TouchPressType.DOWN_AND_UP); 
		System.out.println("Finished!");
		adb.shutdown();
	}
}
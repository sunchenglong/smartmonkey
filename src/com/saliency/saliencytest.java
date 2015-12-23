package com.saliency;
import org.opencv.core.*;
import org.opencv.highgui.*;
import org.opencv.core.Core;

public class saliencytest {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public saliencytest(String imgpath){
		Mat img = Highgui.imread(imgpath);
		Mat result = new Mat();
//Core.dft(img, result);
		Highgui.imwrite("sa-result.jpg", result);
	}
	public static void main(String args[]){
		new saliencytest("result.jpg");
	}
}

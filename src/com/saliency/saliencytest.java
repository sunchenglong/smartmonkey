package com.saliency;
import org.opencv.core.*;
import org.opencv.highgui.*;
import org.opencv.core.Core;
import org.opencv.imgproc.*;
public class saliencytest {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public saliencytest(String imgpath){
		Mat img = Highgui.imread(imgpath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
	    Mat result = img;
	    //Size resize= new Size(200,400);
        //Core.dft(img, result);
	    //Imgproc.resize(img, result, resize);
		Highgui.imwrite("sa-result.jpg", result);
	}
	public static void main(String args[]){
		new saliencytest("result.jpg");
	}
}

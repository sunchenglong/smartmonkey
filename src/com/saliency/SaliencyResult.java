package com.saliency;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class SaliencyResult {
	private Mat sourceimg;
	private int k_num;
	private int result[];
	public SaliencyResult(){}
	//Set the Input Parameters
	public void setSource(String sourcepath){sourceimg = Highgui.imread(sourcepath);}
	public void setK_num(int _k_num){this.k_num = _k_num;}
	public void setResult(int _result[]){this.result = _result;}
	//Get the Input Parameters	
	public int getK_num(){return k_num;}
	public Mat getSource(){return sourceimg;}
	public void writeResult(String Pathname){
		for(int i = 0;i < 2 * k_num;i=i+2){
			Point pt = new Point(this.result[i],this.result[i+1]);
			Scalar green = new Scalar(0,255,0);
			Core.circle(sourceimg, pt, 3, green);
		}
		Highgui.imwrite(Pathname, sourceimg);
	}
	public void printResult(){
		for(int i = 0;i < 2 * k_num;i=i+2){
			System.out.println("("+this.result[i]+","+this.result[i+1]+")");
		}
	}

}

package com.saliency;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class SaliencyResult {
	private Mat sourceimg;
	private Mat saliencymap;
	private int k_num;
	private int result[];
	public SaliencyResult(){}
	//Set the Input Parameters
	public void setSource(String sourcepath){this.sourceimg = Highgui.imread(sourcepath);}
	public void setK_num(int _k_num){this.k_num = _k_num;}
	public void setSaliency(Mat _saliencymap){this.saliencymap = _saliencymap;}
	public void setResult(int _result[]){
		result = new int[k_num*2];
		for(int i = 0; i < k_num*2; i++)
			this.result[i] = _result[i];
		}
	//Get the Input Parameters	
	public int getK_num(){return k_num;}
	public Mat getSource(){return sourceimg;}
	public void writeResult(String Pathname){
		for(int i = 0;i < 2 * k_num;i=i+2){
			Point pt = new Point(this.result[i+1],this.result[i]);
			Scalar green = new Scalar(0,255,0);
			Core.circle(sourceimg, pt, 6, green,10);
		}
		Highgui.imwrite(Pathname, sourceimg);
	}
	public void writeSaliency(String Pathname){
		Highgui.imwrite(Pathname, saliencymap);
	}
	public void printResult(){
		for(int i = 0;i < 2 * k_num;i=i+2){
			System.out.println("("+this.result[i]+","+this.result[i+1]+")");
		}
	}

}

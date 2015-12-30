package com.saliency;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class SaliencyResult {
	private Mat sourceimg;
	private int k_num;
	private int x[];
	private int y[];
	public SaliencyResult(){}
	//Set the Input Parameters
	public void setSource(String sourcepath){sourceimg = Highgui.imread(sourcepath);}
	public void setK_num(int _k_num){this.k_num = _k_num;}
	public void setResult(int _x[],int _y[]){this.x=_x;this.y=_y;}
	//Get the Input Parameters	
	public int getK_num(){return k_num;}
	public Mat getSource(){return sourceimg;}
	public void writeResult(SaliencyResult r,String Pathname){
		for(int i = 0;i < k_num;i++){
			Point pt = new Point(x[i],y[i]);
			Scalar green = new Scalar(0,255,0);
			Core.circle(sourceimg, pt, 3, green);
		}
		Highgui.imwrite(Pathname, sourceimg);
	}

}

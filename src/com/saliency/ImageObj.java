package com.saliency;
import org.opencv.core.*;
import org.opencv.highgui.*;
public class ImageObj {
	private String sourcepath;
	private Mat sourceimg;
	private int k_num;
	private int x[];
	private int y[];
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	//Set the Input Parameters
	public void setSourcePath(String sourcepath){this.sourcepath = sourcepath;}
	public void setSource(String _sourcepath){sourcepath=_sourcepath;this.sourceimg = Highgui.imread(_sourcepath);}
	public void setK_num(int k_num){this.k_num = k_num;}
	//Get the Input Parameters	
	public String getSourcePath(){return sourcepath;}
	public int getK_num(){return k_num;}
	public Mat getSource(){return sourceimg;}
	public void writeResult(String Pathname){
		for(int i = 0;i < k_num;i++){
			Point pt = new Point(x[i],y[i]);
			Scalar green = new Scalar(0,255,0);
			Core.circle(sourceimg, pt, 3, green);
		}
		Highgui.imwrite(Pathname, sourceimg);
	}
}

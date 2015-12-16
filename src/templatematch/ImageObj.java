package templatematch;
import org.opencv.core.*;
import org.opencv.highgui.*;
public class ImageObj {
	private Mat sourceimg;
	private Mat templateimg;
	private int ScreenshotStartx;
	private int ScreenshotStarty;
	private int ScreenshotEndx;
	private int ScreenshotEndy;
	private int ScaleWidth;
	private int ScaleHeight;
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	//Set the Input Parameters
	public void setSource(String sourcepath){sourceimg = Highgui.imread(sourcepath);}
	public void setTemplate(String templatepath){templateimg = Highgui.imread(templatepath);}
	public void setStartx(int ScreenshotStartx_){this.ScreenshotStartx = ScreenshotStartx_;}
	public void setStarty(int ScreenshotStarty_){this.ScreenshotStarty = ScreenshotStarty_;}
	public void setEndx(int ScreenshotEndx_){this.ScreenshotEndx = ScreenshotEndx_;}
	public void setEndy(int ScreenshotEndy_){this.ScreenshotEndy = ScreenshotEndy_;}
	public void setWidth(int ScaleWidth_){this.ScaleWidth = ScaleWidth_;}
	public void setHeight(int ScaleHeight_){this.ScaleHeight = ScaleHeight_;}
	//Get the Input Parameters	
	public int getStartx(){return ScreenshotStartx;}
	public int getStarty(){return ScreenshotStarty;}
	public int getEndx(){return ScreenshotEndx;}
	public int getEndy(){return ScreenshotEndy;}
	public int getWidth(){return ScaleWidth;}
	public int getHeight(){return ScaleHeight;}
	public Mat getSource(){return sourceimg;}
	public Mat getTemplate(){return templateimg;}
	public void writeResult(MatchResult r,String Pathname){
		Point pt1 = new Point(r.startx,r.starty);
		Point pt2 = new Point(r.startx + r.width,r.starty + r.height);
		Scalar green = new Scalar(0,255,0);
		Core.rectangle(sourceimg, pt1, pt2, green, 2 , 8 , 0);
		Highgui.imwrite(Pathname, sourceimg);
}
}

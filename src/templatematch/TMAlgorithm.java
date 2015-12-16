package templatematch;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.*;
public class TMAlgorithm extends MatchStrategy{
	public MatchResult matchalgorithmInterface(ImageObj imgobj)
	{	
		Mat templateimg = imgobj.getTemplate();
		Mat sourceimg = imgobj.getSource();
		Mat templateimg_resize = new Mat();
		Mat sourceimg_resize = new Mat();
		int sourcewidth = sourceimg.cols();
		int sourceheight = sourceimg.rows();
		double tmpscalex =  480/imgobj.getWidth();
		double tmpscaley =  800/imgobj.getHeight();
		double sourcescalex = (double) 480/sourcewidth;
		double sourcescaley = (double) 800/sourceheight;
		Size tmpsize = new Size(templateimg.cols()*tmpscalex,templateimg.rows()*tmpscaley);
		Imgproc.resize(templateimg, templateimg_resize, tmpsize);
		Size sourcesize = new Size(sourcewidth*sourcescalex,sourceheight*sourcescaley);
		Imgproc.resize(sourceimg, sourceimg_resize, sourcesize);
		int resultwidth =  sourcewidth - templateimg.cols() + 1;
		int resultheight = sourceheight - templateimg.rows() + 1;
		Mat resultimg = new Mat(resultwidth,resultheight,CvType.CV_32FC1);
		Imgproc.matchTemplate(sourceimg_resize, templateimg_resize, resultimg, 1);//match method = 1
		Core.normalize( resultimg, resultimg, 0, 1, Core.NORM_MINMAX, -1);
		Core.MinMaxLocResult m = Core.minMaxLoc(resultimg);
		MatchResult r = new MatchResult();
		r.startx = (int) (m.minLoc.x/sourcescalex);
		r.starty = (int) (m.minLoc.y/sourcescaley);
		r.width = (int) (templateimg_resize.cols()/sourcescalex);
		r.height = (int) (templateimg_resize.rows()/sourcescaley);
		r.successflag = 1;
		Point pt1 = new Point(r.startx,r.starty);
		Point pt2 = new Point(r.startx + r.width,r.starty + r.height);
		Scalar green = new Scalar(0,255,0);
		Core.rectangle(sourceimg, pt1, pt2, green, 2 , 8 , 0);
		Highgui.imwrite("result.jpg", sourceimg);
		return r;
	}
}

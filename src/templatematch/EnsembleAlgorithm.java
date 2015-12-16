package templatematch;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Size;
import org.opencv.features2d.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.calib3d.Calib3d;
import java.util.List;
import java.util.ArrayList;
import org.opencv.core.Rect;
public class EnsembleAlgorithm extends MatchStrategy{
	public double _Matching_Threshold_ = 0.5;
	public double  getMax(double A, double B, double C, double D)
	{
		return ((A)>(B)?(A):(B))>((C)>(D)?(C):(D))?((A)>(B)?(A):(B)):((C)>(D)?(C):(D));
	}
	public double  getMin(double A, double B, double C, double D)
	{
		return ((A)<(B)?(A):(B))<((C)<(D)?(C):(D))?((A)<(B)?(A):(B)):((C)<(D)?(C):(D));
	}
	public MatchResult TemplateMatch_(ImageObj imgobj)
	{
		Mat templateimg = imgobj.getTemplate();
		Mat sourceimg = imgobj.getSource();
		int startx_ = imgobj.getStartx();
		int starty_ = imgobj.getStarty();
		int endx_ = imgobj.getEndx();
		int endy_ = imgobj.getEndy();
		int width_ = templateimg.cols();
		int height_ = templateimg.rows();
		int Template_width_ = imgobj.getWidth();
		int Template_height_ = imgobj.getHeight();
		int Source_width_ = sourceimg.cols();
		int Source_height_ = sourceimg.rows();
		startx_ = startx_ - width_/2;
		starty_ = starty_ - height_/2;
		endx_ = endx_ + 3*width_/2;
		endy_ = endy_ + 3*height_/2;
		startx_ = startx_ < 0 ? 0 : startx_;
		starty_ = starty_ < 0 ? 0 : starty_;
		endx_ = endx_ <0 ? 0 : endx_;
		endy_ = endy_ < 0 ? 0 : endy_;
		startx_ = startx_ > Template_width_ ? Template_width_ : startx_;
		starty_ = starty_ > Template_height_ ? Template_height_ : starty_;
		endx_ = endx_ > Template_width_ ? Template_width_ : endx_;
		endy_ = endy_ > Template_height_ ? Template_height_ : endy_;
		Mat sourceimg_resize_tmp = new Mat();
		Size sourcesize = new Size(Template_width_,Template_height_);
		Imgproc.resize(sourceimg, sourceimg_resize_tmp, sourcesize);
		Rect source_roi = new Rect(startx_,starty_,endx_-startx_,endy_-starty_);
		Mat sourceimg_resize = new Mat(sourceimg_resize_tmp,source_roi);
		int resultwidth =  sourceimg_resize.cols() - templateimg.cols() + 1;
		int resultheight = sourceimg_resize.rows() - templateimg.rows() + 1;
		Mat resultimg = new Mat(resultwidth,resultheight,CvType.CV_32FC1);
		Imgproc.matchTemplate(sourceimg_resize, templateimg, resultimg, 1);
		Core.normalize( resultimg, resultimg, 0, 1, Core.NORM_MINMAX, -1);
		Core.MinMaxLocResult m = Core.minMaxLoc(resultimg);
		MatchResult r = new MatchResult();
		r.startx = (int) (m.minLoc.x + startx_)* Source_width_ / Template_width_;
		r.starty = (int) (m.minLoc.y + starty_) * Source_height_ / Template_height_;
		r.width = (int) width_ * Source_width_ / Template_width_;
		r.height = (int) height_* Source_height_ / Template_height_;
		Rect roi = new Rect((int)(m.minLoc.x), (int)(m.minLoc.y), width_, height_);
		Mat RoiSource = new Mat(sourceimg_resize,roi);
		Mat ValidResult = new Mat(1,1,CvType.CV_32FC1);
		Imgproc.matchTemplate(RoiSource, templateimg, ValidResult, 1);
		System.out.println(ValidResult.dump());
		double ValidValue = Core.minMaxLoc(ValidResult).minVal;
		if(ValidValue < _Matching_Threshold_) r.successflag = 1;
		else r.successflag = 0;
		r.ValidValue = ValidValue;
		return r;
	}
	public MatchResult SurfMatch_(ImageObj imgobj)
	{
		MatchResult r = new MatchResult();
		Mat templateimg = imgobj.getTemplate();
		Mat sourceimg = imgobj.getSource();
		FeatureDetector detector = FeatureDetector.create(FeatureDetector.SIFT);
		MatOfKeyPoint  keypoints_template = new MatOfKeyPoint();
		MatOfKeyPoint  keypoints_source = new MatOfKeyPoint();
		Mat descriptors_template = new Mat();
		Mat descriptors_source = new Mat();
		detector.detect(templateimg, keypoints_template);
		detector.detect(sourceimg, keypoints_source);
		DescriptorExtractor  extractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		extractor.compute(templateimg, keypoints_template, descriptors_template);
		extractor.compute(sourceimg, keypoints_source, descriptors_source);
		MatOfDMatch dmatch = new MatOfDMatch();
		DescriptorMatcher matcher =  DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
		matcher.match(descriptors_template,descriptors_source, dmatch);
		List <DMatch> matches =new ArrayList<DMatch>();
		matches = dmatch.toList();
		double max_dist = 0; 
		double min_dist = 100;
		for(int i = 0; i < descriptors_template.rows();i++)
		{
			double dist = matches.get(i).distance;
		    if( dist < min_dist ) min_dist = dist;
		    if( dist > max_dist ) max_dist = dist;
		}
		List <DMatch> good_matches =new ArrayList<DMatch>();
		for(int i = 0; i < descriptors_template.rows();i++)
		{
			if(matches.get(i).distance < 3*min_dist) good_matches.add(matches.get(i));
		}
		if(good_matches.size()<5){
			r.successflag = 0;
			return r;
		}
		List <Point> template_point = new ArrayList<Point>();
		List <Point> source_point = new ArrayList<Point>();
		List <KeyPoint> keypoints_template_list = new ArrayList<KeyPoint>();
		List <KeyPoint> keypoints_source_list = new ArrayList<KeyPoint>();
		keypoints_template_list = keypoints_template.toList();
		keypoints_source_list = keypoints_source.toList();
		for( int i = 0; i < good_matches.size(); i++ )
		{
			template_point.add(keypoints_template_list.get(good_matches.get(i).queryIdx).pt);
			source_point.add(keypoints_source_list.get(good_matches.get(i).trainIdx).pt);
		}
		MatOfPoint2f tmp = new MatOfPoint2f();
		MatOfPoint2f sou = new MatOfPoint2f();
		tmp.fromList(template_point);
		sou.fromList(source_point);
		Mat H = new Mat();
		H = Calib3d.findHomography(tmp, sou, Calib3d.RANSAC, 3);//C++�е�Ransac�㷨
		List <Point> template_corners =new ArrayList<Point>(4);
		List <Point> source_corners =new ArrayList<Point>(4);
		Point pos11 = new Point(0,0);
		template_corners.add(pos11);
		Point pos12 = new Point(templateimg.cols(),0);
		template_corners.add(pos12);
		Point pos21 = new Point(0,templateimg.rows());
		template_corners.add(pos21);
		Point pos22 = new Point(templateimg.cols(),templateimg.rows());
		template_corners.add(pos22);
		MatOfPoint2f template_corners_mat = new MatOfPoint2f();
		MatOfPoint2f source_corners_mat = new MatOfPoint2f();
		template_corners_mat.fromList(template_corners);
		source_corners_mat.fromList(source_corners);
		Core.perspectiveTransform(template_corners_mat, source_corners_mat, H);
		List<Point> corners_list = new ArrayList<Point>();
		corners_list = source_corners_mat.toList();
		r.startx = (int) getMin(corners_list.get(0).x,corners_list.get(1).x,corners_list.get(2).x,corners_list.get(3).x);
		r.starty = (int) getMin(corners_list.get(0).y,corners_list.get(1).y,corners_list.get(2).y,corners_list.get(3).y);
		int endx = (int) getMax(corners_list.get(0).x,corners_list.get(1).x,corners_list.get(2).x,corners_list.get(3).x);
		int endy = (int) getMax(corners_list.get(0).y,corners_list.get(1).y,corners_list.get(2).y,corners_list.get(3).y);
		r.width = endx - r.startx;
		r.height = endy - r.starty;
		Rect roi = new Rect(r.startx,r.starty,r.width,r.height);
		Mat RoiSource = new Mat(sourceimg,roi);
		Mat ValidResult = new Mat(1,1,CvType.CV_32FC1);
		Imgproc.matchTemplate(RoiSource, templateimg, ValidResult, 1);
		double ValidValue = Core.minMaxLoc(ValidResult).minVal;
		if(ValidValue < _Matching_Threshold_) r.successflag = 1;
		else r.successflag = 0;
		return r;
	}
	
	
	public MatchResult matchalgorithmInterface(ImageObj imgobj)
	{
		MatchResult r = new MatchResult();
		r = TemplateMatch_(imgobj);
		if (r.successflag == 1) return r;
		else {
			double ValidValue1 = r.ValidValue;
			r = SurfMatch_(imgobj);
		if(r.successflag == 1||Math.abs(r.ValidValue - ValidValue1)<=0.01)
		{
			r.successflag = 1;
			return r;
		}
		else
			return r;
		}
	}
	
	
}

package templatematch;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.MatOfPoint2f;
import org.opencv.features2d.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.calib3d.Calib3d;
import java.util.List;
import java.util.ArrayList;
import org.opencv.core.Rect;
public class SurfAlgorithm extends MatchStrategy{
	public double _Matching_Threshold_ = 0.25;
	public double  getMax(double A, double B, double C, double D)
	{
		return ((A)>(B)?(A):(B))>((C)>(D)?(C):(D))?((A)>(B)?(A):(B)):((C)>(D)?(C):(D));
	}
	public double  getMin(double A, double B, double C, double D)
	{
		return ((A)<(B)?(A):(B))<((C)<(D)?(C):(D))?((A)<(B)?(A):(B)):((C)<(D)?(C):(D));
	}
	public MatchResult matchalgorithmInterface(ImageObj imgobj)
	{
		Mat templateimg = imgobj.getTemplate();
		Mat sourceimg = imgobj.getSource();
		FeatureDetector detector = FeatureDetector.create(FeatureDetector.SURF);
		MatOfKeyPoint  keypoints_template = new MatOfKeyPoint();
		MatOfKeyPoint  keypoints_source = new MatOfKeyPoint();
		Mat descriptors_template = new Mat();
		Mat descriptors_source = new Mat();
		detector.detect(templateimg, keypoints_template);
		detector.detect(sourceimg, keypoints_source);
		DescriptorExtractor  extractor = DescriptorExtractor.create(DescriptorExtractor.SURF);
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
		if(good_matches.size()<5){}
		List <Point> template_point =new ArrayList<Point>();
		List <Point> source_point =new ArrayList<Point>();
		List <KeyPoint> keypoints_template_list =new ArrayList<KeyPoint>();
		List <KeyPoint> keypoints_source_list =new ArrayList<KeyPoint>();
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
		H = Calib3d.findHomography(tmp, sou, Calib3d.RANSAC, 3);
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
		MatchResult r = new MatchResult();
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
		System.out.println(ValidValue);
		return r;
	}
}

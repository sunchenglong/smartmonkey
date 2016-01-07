package com.saliency;
import java.util.List;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
public class sr extends SaliencyStrategy{
	private static int threshold = 180;
	public SaliencyResult saliencyalgorithmInterface(ImageObj imgobj, String method) {
		// TODO Auto-generated method stub
		float min=Float.MAX_VALUE;
	    float max=Float.MIN_VALUE;
		String imgpath = imgobj.getSourcePath();
		int k_num = imgobj.getK_num();
		SaliencyResult result = new SaliencyResult();
		Mat img = Highgui.imread(imgpath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		Mat saliencyMap = new Mat(img.size(), CvType.CV_16U);
		Mat Fourier  = new Mat(img.size(),CvType.CV_64FC2);
		Mat Inverse  = new Mat(img.size(), CvType.CV_64FC2);
		Mat ImageRe = new Mat(img.size(), CvType.CV_64FC1);
		Mat ImageIm = new Mat(img.size(), CvType.CV_64FC1);
		Mat LogAmplitude = new Mat(img.size(), CvType.CV_64FC1);
		Mat Sine = new Mat(img.size(), CvType.CV_64FC1);
		Mat Cosine = new Mat(img.size(), CvType.CV_64FC1);
		Mat Residual = new Mat(img.size(), CvType.CV_64FC1);
		Mat tmp1 = new Mat(img.size(), CvType.CV_64FC1);
		Mat tmp2 = new Mat(img.size(), CvType.CV_64FC1);
		Mat tmp3 = new Mat(img.size(), CvType.CV_64FC1);
		double scale = 1.0/255.0;
		img.convertTo(tmp1, CvType.CV_64FC1, scale, 0);
		img.convertTo(ImageRe, CvType.CV_64FC1, 1, 0);
		List<Mat> l = new ArrayList<Mat>(); 
		l.add(ImageRe);l.add(ImageIm);
		Core.merge(l, tmp2);
		Highgui.imwrite("tmp2.jpg", tmp2);
		Core.dft(tmp2, Fourier);
		List<Mat> l1 = new ArrayList<Mat>(); 
		Core.split(Fourier, l1);
		ImageRe=l1.get(0);
		ImageIm=l1.get(1);
		Core.pow(ImageRe, 2.0, tmp1);
		Core.pow(ImageIm, 2.0, tmp2);
		Core.add(ImageRe, ImageIm, tmp3);
		Core.pow(tmp3, 0.5, tmp3);
		Core.log(tmp3, LogAmplitude);
		Core.divide(ImageIm, tmp3, Sine);	    
		Core.divide(ImageRe, tmp3, Cosine);
		Imgproc.blur(tmp3,LogAmplitude,new Size(3,3));
		Core.subtract(LogAmplitude, tmp3, Residual);
		Core.exp(Residual, Residual);
		Core.multiply(Residual, Cosine, tmp1);
		Core.multiply(Residual, Sine, tmp2);
		List<Mat> l2 = new ArrayList<Mat>(); 
		l2.add(tmp1);l2.add(tmp2);
		Core.merge(l2,Fourier);
		Core.idft(Fourier, Inverse);
		List<Mat> l3 = new ArrayList<Mat>(); 
		Core.split(Inverse, l3);   
		tmp1=l3.get(0);
		tmp2=l3.get(1);
		Core.pow(tmp1, 2.0, tmp1);
		Core.pow(tmp2, 2.0, tmp2);
		Core.add(tmp1, tmp2, tmp3);
		Imgproc.blur(tmp3,tmp3,new Size(7,7));
		MinMaxLocResult	minmaxResult = Core.minMaxLoc(tmp3);
		double minNum = minmaxResult.minVal;
		double maxNum = minmaxResult.maxVal;
		scale = 255/(maxNum - minNum);
		double shift = -minNum * scale;
		tmp3.convertTo(tmp3, -1, scale, shift);
		tmp3.convertTo(tmp3, CvType.CV_8U);
		saliencyMap.convertTo(saliencyMap, CvType.CV_8U);
		Imgproc.threshold(tmp3, saliencyMap, threshold, 255, Imgproc.THRESH_BINARY);
	    new findMarkUtil();
	    int nums[] = null;
	    if(method == "kmeans"){
	    	nums = findMarkUtil.findMarkUtil_kmeans(saliencyMap,k_num,255,0,5);
	    }else if(method == "random"){
	    	nums = findMarkUtil.findMarkUtil_random(saliencyMap,k_num,255);
	    }
	    result.setK_num(k_num);
	    result.setSource(imgpath);
	    result.setResult(nums);
	    result.setSaliency(saliencyMap);
		return result;
	}

}

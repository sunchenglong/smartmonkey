package com.saliency;

import org.opencv.core.*;
import org.opencv.highgui.*;
import org.opencv.core.Core;
import org.opencv.imgproc.*;

public class saliencytest {
	private static int threshold = 30;
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public saliencytest(String imgpath) {
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		Mat img = Highgui.imread(imgpath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		Mat saliencyMap = new Mat();
		saliencyMap.create(img.rows(), img.cols(), CvType.CV_16U);
		int HistGram[] = new int[256];
		int Gray[] = new int[img.cols() * img.rows()];
		int Dist[] = new int[256];
		float DistMap[] = new float[img.rows() * img.cols()];
		for (int row = 0; row < img.rows(); row++) {
			int CurIndex = row * img.cols();
			for (int col = 0; col < img.cols(); col++) {
				HistGram[(int) (img.get(row, col)[0])]++;
				Gray[CurIndex] = (int) (img.get(row, col)[0]);
				CurIndex++;
			}
		}
		for (int Y = 0; Y < 256; Y++) {
			int Value = 0;
			for (int X = 0; X < 256; X++)
				Value += Math.abs(Y - X) * HistGram[X];
			Dist[Y] = Value;
		}
		for (int row = 0; row < img.rows(); row++) {
			int CurIndex = row * img.cols();
			for (int col = 0; col < img.cols(); col++) {
				DistMap[CurIndex] = Dist[Gray[CurIndex]];
				if (DistMap[CurIndex] < min)
					min = DistMap[CurIndex];
				if (DistMap[CurIndex] > max)
					max = DistMap[CurIndex];
				CurIndex++;
			}
		}
		for (int row = 0; row < img.rows(); row++) {
			int CurIndex = row * img.cols();
			for (int col = 0; col < img.cols(); col++) {
				saliencyMap.put(row, col, partTwo((DistMap[CurIndex] - min)
						/ (max - min) * 255));
				CurIndex++;
			}
			System.out.println("");
		}
		Highgui.imwrite("sa-result.jpg", saliencyMap);
	}

	private static int partTwo(float input) {
		if (input < threshold) {
			return 0;
		} else
			return 255;
	}

	public static void main(String args[]) {
		new saliencytest("result.jpg");
	}
}
package com.saliency;
import org.opencv.core.Mat;
import java.util.*;
public class findMarkUtil {
public static int[] findMarkUtil_random(Mat src, int k_num, int marker){
	Random r = new Random();
	int result[] = new int[k_num*2];
	int curl = 0;
	while(k_num>0){
		int row = r.nextInt((int) (src.rows()));
		int col = r.nextInt((int) (src.cols()));
		if((int) (src.get(row,col)[0]) == marker){
			result[curl] = row;
			result[curl+1] = col;
			curl += 2;
		}		
	}
	return result;
}
}

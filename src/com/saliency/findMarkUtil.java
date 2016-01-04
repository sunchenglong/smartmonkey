package com.saliency;
import org.opencv.core.Mat;
import java.util.*;
public class findMarkUtil {
public static int[] findMarkUtil_random(Mat src, int k_num, int marker){
	Random r = new Random();
	int rows = (int) src.rows();
	int cols = (int) src.cols();
	int row = 0;
	int col = 0;
	int result[] = new int[k_num*2];
	int curl = 0;
	while(k_num>0){
		row = r.nextInt(rows);
		col = r.nextInt(cols);
		if((int) (src.get(row,col)[0]) == marker){
			result[curl] = row;
			result[curl+1] = col;
			curl += 2;
			k_num --;
		}	
	}
	return result;
}


public static int[] findMarkUtil_kmeans(Mat src, int k_num, int marker
		, float e, int iter){
	int result[] = findMarkUtil_random(src, k_num, marker);
	long dist[] = new long[k_num];
	int mindist;
	int tmpcurl = 0;
	int sumdist = 0;
	int oldsumdist = 0;
	int lableMap[][] = new int[src.rows()][src.cols()];
	while(iter-- > 0){
		
	for (int row = 0;row < src.rows();row++){
		for(int col = 0;col < src.cols();col++){
			if((int) (src.get(row,col)[0]) == marker){
				mindist = -1;
				for(int cur = 0;cur < k_num;cur=cur*2){
					if(mindist < Math.abs(result[cur] - row) 
							+ Math.abs(result[cur+1] - col)){
					}else{
						tmpcurl = cur;
						mindist = Math.abs(result[cur] - row) 
								+ Math.abs(result[cur+1] - col);
					}
				}
				lableMap[row][col] = tmpcurl+1;
				dist[tmpcurl]+=mindist;
				sumdist += dist[tmpcurl];
			}	
		}
	}
	for(int cur = 0;cur < k_num;cur=cur*2){
		int count=0;
		int rownew=0,colnew=0;
		for (int row = 0;row < src.rows();row++){
			for(int col = 0;col < src.cols();col++){
				if(lableMap[row][col] == cur + 1){
					count += 1;
					rownew += row;
					colnew += col;
				}
			}
		}
		result[cur] = (int) (rownew / count);
		result[cur+1] = (int) (colnew / count);
	}
	if(Math.abs(sumdist-oldsumdist) < e)
		break;
	}
	return result;
	}
}

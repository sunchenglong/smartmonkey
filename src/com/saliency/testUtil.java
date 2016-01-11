package com.saliency;
import java.io.File;

public class testUtil {
	private static File dir = new File("testSaliency");
	private int knum;
	private String algorithm;
	private String method;
	public testUtil(int _knum,String _algorithm,String _method){
		knum = _knum;
		algorithm = _algorithm;
		method = _method;
	}
	public  void runAllFiles(File dir) throws Exception{
		  File[] fs = dir.listFiles();
		  for(int i=0; i<fs.length; i++){
		   System.out.println(fs[i].getAbsolutePath());
		   String input = fs[i].getAbsolutePath();
		   String output = "sa-" + fs[i].getAbsolutePath();
		   SaliencyUtils s = new SaliencyUtils(input,output,knum,algorithm,method);
			SaliencyResult result = s.getSaliencyResult();
			result.writeSaliency("saliencymap.jpg");
		   if(fs[i].isDirectory()){
	         try{
	        	 runAllFiles(fs[i]);
		    }catch(Exception e){}
		   }
	}
}
	public static void main(String args[]) throws Exception{
		test(dir);
	}
}

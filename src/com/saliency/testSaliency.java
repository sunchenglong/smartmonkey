package com.saliency;

public class testSaliency {
	public static void test1(){
		//SaliencyUtils s = new SaliencyUtils("result.jpg","sa-result.jpg",6,"kmeans");
		SaliencyUtils s = new SaliencyUtils("result.jpg","sa-result.jpg",6,"sr","kmeans");
		SaliencyResult result = s.getSaliencyResult();
		result.writeSaliency("saliencymap.jpg");
	}
	public static void test2(){
		SaliencyUtils s = new SaliencyUtils("result.jpg","sa-result.jpg",6,"lc","kmeans");
		SaliencyResult result = s.getSaliencyResult();
		result.writeSaliency("saliencymap.jpg");
	}
	public static void test3(){
		
	}
	public static void main(String args[]){
		//new saliencytest("result.jpg");
		test2();
	}
}


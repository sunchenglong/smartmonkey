package com.templatematch;
public class TestMatch {
	public static void test1()
	{
		Context context;
		context = new Context(new EnsembleAlgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource("D:\\itestin\\512.png");
		imgobj.setTemplate("D:\\itestin\\512a.png");
		imgobj.setStartx(376);
		imgobj.setStarty(172);
		imgobj.setEndx(595);
		imgobj.setEndy(269);
		imgobj.setWidth(540);
		imgobj.setHeight(960);
		MatchResult result = context.contextInterface(imgobj);
		result.dispResult();
		imgobj.writeResult(result,"result.jpg");
	}
	public static void test2()
	{
		MatchInterface tool = new MatchInterface("testPic/3.jpg","testPic/a.jpg",63,515,127,708,480,800);
		MatchResult result = tool.getMatchResult();
		System.out.println(result.startx);
		System.out.println(result.starty);
		System.out.println(result.width);
		System.out.println(result.height);
		System.out.println(result.successflag);
		result.dispResult();
		
	}
	public static void test3()
	{
		Context context;
		context = new Context(new EnsembleAlgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource("D:1.png");
		imgobj.setTemplate("D:a.png");
		imgobj.setStartx(354);
		imgobj.setStarty(24);
		imgobj.setEndx(515);
		imgobj.setEndy(87);
		imgobj.setWidth(540);
		imgobj.setHeight(960);
		MatchResult result = context.contextInterface(imgobj);
		result.dispResult();
		imgobj.writeResult(result,"result.jpg");
	}
	public static void test4()
	{
		MatchInterface tool = new MatchInterface("3.jpg","a.jpg",63,515,127,708,480,800);
		MatchResult result = tool.getMatchResult();
		System.out.println(result.startx);
		System.out.println(result.starty);
		System.out.println(result.width);
		System.out.println(result.height);
		System.out.println(result.successflag);
	}
	    public static void main(String[] args) {
	    	test2();
	}
}

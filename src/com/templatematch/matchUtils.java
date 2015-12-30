package com.templatematch;
public class matchUtils {
	private MatchResult result;
	public matchUtils(String SourcePath,String TemplatePath,int startx,int endx,int starty,int endy,int width,int height)
	{
		Context context;
		context = new Context(new EnsembleAlgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(SourcePath);
		imgobj.setTemplate(TemplatePath);
		imgobj.setStartx(startx);
		imgobj.setStarty(starty);
		imgobj.setEndx(endx);
		imgobj.setEndy(endy);
		imgobj.setWidth(width);
		imgobj.setHeight(height);
		result = context.contextInterface(imgobj);
		imgobj.writeResult(result,"result.jpg");
	}
	public MatchResult getMatchResult(){return result;}
}

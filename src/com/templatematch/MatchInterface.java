package com.templatematch;
public class MatchInterface {
	private MatchResult result;
	public MatchInterface(String SourcePath,String TemplatePath,int startx,int endx,int starty,int endy,int width,int height)
	{
		MatchContext context;
		context = new MatchContext(new EnsembleAlgorithm());
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

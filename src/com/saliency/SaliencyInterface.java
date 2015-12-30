package com.saliency;
public class SaliencyInterface {
	private SaliencyResult result;
	public SaliencyInterface(String SourcePath,String TemplatePath,int startx,int endx,int starty,int endy,int width,int height)
	{
		Context context;
		context = new Context(new lcalgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(SourcePath);
		imgobj.setStartx(startx);
		result = context.contextInterface(imgobj);
		imgobj.writeResult(result,"result.jpg");
	}
	public SaliencyResult getMatchResult(){return result;}
}

package com.saliency;
public class SaliencyUtils {
	private SaliencyResult result;
	public SaliencyUtils(String sourcePath,int k_num)
	{
		Context  context = new Context(new lcalgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);;
		result = context.contextInterface(imgobj);
	}
	public SaliencyUtils(String sourcePath,String resultPath,int k_num)
	{
		Context context = new Context(new lcalgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);;
		result = context.contextInterface(imgobj);
		imgobj.writeResult(resultPath);
	}
	public SaliencyResult getSaliencyResult(){
		return result;
		}
}

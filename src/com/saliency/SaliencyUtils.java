package com.saliency;
public class SaliencyUtils {
	private SaliencyResult result;
	public SaliencyUtils(String sourcePath,int k_num,String method)
	{
		Context  context = new Context(new lc());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);;
		result = context.contextInterface(imgobj,method);
	}
	public SaliencyUtils(String sourcePath,String resultPath,int k_num,String method)
	{
		Context context = new Context(new lc());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);;
		result = context.contextInterface(imgobj,method);
		result.writeResult(resultPath);
		result.printResult();
	}
	public SaliencyUtils(String sourcePath,int k_num,String algorithm,String method)
	{
		Context  context = null;
		if(algorithm.equals("lc"))
			context = new Context(new lc());
		else if(algorithm.equals("hc"))
			context = new Context(new hc());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);;
		result = context.contextInterface(imgobj,method);
	}
	public SaliencyResult getSaliencyResult(){
		return result;
		}
}

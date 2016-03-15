package com.saliency;

public class Context {
	private SaliencyStrategy saliencytrategy;
	public Context(SaliencyStrategy saliencytrategy)
	{
		this.saliencytrategy = saliencytrategy;
	}
	public SaliencyResult contextInterface(ImageObj imgobj,String method)
	{
		SaliencyResult saliencyresult = saliencytrategy.saliencyalgorithmInterface(imgobj,method);
		return saliencyresult;
	}
}

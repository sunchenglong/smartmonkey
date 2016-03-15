package com.saliency;

public class SaliencyContext {
	private SaliencyStrategy saliencytrategy;
	public SaliencyContext(SaliencyStrategy saliencytrategy)
	{
		this.saliencytrategy = saliencytrategy;
	}
	public SaliencyResult SaliencyContextInterface(ImageObj imgobj,String method)
	{
		SaliencyResult saliencyresult = saliencytrategy.saliencyalgorithmInterface(imgobj,method);
		return saliencyresult;
	}
}

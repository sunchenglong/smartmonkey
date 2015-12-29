package com.saliency;

public class Context {
	private SaliencyStrategy saliencytrategy;
	public Context(SaliencyStrategy saliencytrategy)
	{
		this.saliencytrategy = saliencytrategy;
	}
	public SaliencyResult contextInterface(ImageObj imgobj)
	{
		SaliencyResult saliencyresult = saliencytrategy.saliencyalgorithmInterface(imgobj);
		return saliencyresult;
	}
}

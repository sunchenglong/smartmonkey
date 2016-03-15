package com.templatematch;
/**
 * 
 * 
 *
 */
public class MatchContext {
	private MatchStrategy matchstrategy;
	public MatchContext(MatchStrategy matchstrategy)
	{
		this.matchstrategy = matchstrategy;
	}
	public MatchResult contextInterface(ImageObj imgobj)
	{
		MatchResult matchresult = matchstrategy.matchalgorithmInterface(imgobj);
		return matchresult;
	}
}

package templatematch;

public class Context {
	private MatchStrategy matchstrategy;
	public Context(MatchStrategy matchstrategy)
	{
		this.matchstrategy = matchstrategy;
	}
	public MatchResult contextInterface(ImageObj imgobj)
	{
		MatchResult matchresult = matchstrategy.matchalgorithmInterface(imgobj);
		return matchresult;
	}
}

package templatematch;
public class MatchResult {
	public int startx;
	public int starty;
	public int width;
	public int height;
	public int successflag;
	public double ValidValue;
	public MatchResult(){}
	public MatchResult(int startx_,int starty_,int width_,int height_,int successflag_,double ValidValue_)
	{
		this.startx = startx_;
		this.starty = starty_;
		this.width = width_;
		this.height = height_;
		this.successflag = successflag_;
		this.ValidValue = ValidValue_;
	}
	public void dispResult()
	{
		System.out.println(startx);
		System.out.println(starty);
		System.out.println(width);
		System.out.println(height);
		System.out.println(successflag);
		System.out.println(ValidValue);
	}
	public double getValidValue(){return ValidValue;}
}

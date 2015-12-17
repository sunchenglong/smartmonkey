package common;
public class pytool {
	private static final String MONKEYRUNNER = "$ANDROID_HOME/monkeyrunner";
	private static final String TAKESHOT = MONKEYRUNNER + " pysrc/takeshot.py ";
	
	public static void takeshot(String filename) throws Exception{
		try{
			JavaShellUtil.execute(TAKESHOT + filename);
		}catch(Exception e){
			throw e;
		}
	}
	
	public static void main(String args[]) throws Exception{
		takeshot("./test.png");
	}
}

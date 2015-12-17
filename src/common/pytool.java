package common;
public class pytool {
	private static final String MONKEYRUNNER = " $ANDROID_HOME/monkeyrunner ";
	private static final String TAKESHOT = " takeshot.py ";
	
	public static void takeshot(String filename) throws Exception{
		String pypath = new pytool().getPypath();
		try{
			JavaShellUtil.execute(MONKEYRUNNER + pypath + filename);
		}catch(Exception e){
			throw e;
		}
	}
	
	private String getPypath(){
		String path = this.getClass().getClassLoader().getResource(".").getPath();
		String pypath = path.split("bin/")[0]+"pysrc/";
		System.out.println(pypath);
		return pypath;
	}
	public static void main(String args[]) throws Exception{
		takeshot("/home/test.png");
		//pytool p = new pytool();
		//p.getPypath();
	}
}

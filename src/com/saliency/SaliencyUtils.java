package com.saliency;

public class SaliencyUtils {
	private SaliencyResult result;

	public SaliencyUtils(String sourcePath, int k_num, String method) {
		SaliencyContext context = new SaliencyContext(new LcAlgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);
		;
		result = context.SaliencyContextInterface(imgobj, method);
	}

	public SaliencyUtils(String sourcePath, String resultPath, int k_num,
			String method) {
		SaliencyContext context = new SaliencyContext(new LcAlgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);
		;
		result = context.SaliencyContextInterface(imgobj, method);
		result.writeResult(resultPath);
		result.printResult();
	}

	public SaliencyUtils(String sourcePath, String resultPath, int k_num,
			String algorithm, String method) {
		SaliencyContext context = null;
		if (algorithm.equals("lc"))
			context = new SaliencyContext(new LcAlgorithm());
		else if (algorithm.equals("sr"))
			context = new SaliencyContext(new SrAlgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);
		;
		result = context.SaliencyContextInterface(imgobj, method);
		result.writeResult(resultPath);
		result.printResult();
	}

	public SaliencyUtils(String sourcePath, int k_num, String algorithm,
			String method) {
		SaliencyContext context = null;
		if (algorithm.equals("lc"))
			context = new SaliencyContext(new LcAlgorithm());
		else if (algorithm.equals("sr"))
			context = new SaliencyContext(new SrAlgorithm());
		ImageObj imgobj = new ImageObj();
		imgobj.setSource(sourcePath);
		imgobj.setK_num(k_num);
		;
		result = context.SaliencyContextInterface(imgobj, method);
	}

	public SaliencyResult getSaliencyResult() {
		return result;
	}
}

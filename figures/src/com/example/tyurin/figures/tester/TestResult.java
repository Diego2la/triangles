package com.example.tyurin.figures.tester;

class TestInfo {
	
	public TestInfo(TestResult res, String name) {
		this.res   = res;
		this.name  = name;
	}
	public TestResult res;
	public String     name;

}

class TestResult {
	
	public boolean ok() {
		return res;
	}
	
	public String error() {
		return err;
	}
	
	protected TestResult(boolean res, String err)
	{
		this.res = res;
		if (err == null)
			this.err = DEFAULT_ERROR;
		else
			this.err = err;
	}
	
	private boolean res;
	private String  err;
	private final static String DEFAULT_ERROR = "Unknown error";
}

class TestOk extends TestResult	{

	public TestOk() {
		super(true, null);
	}

}

class TestFail extends TestResult {

	public TestFail() {
		super(false, null);
	}
	
	public TestFail(String err) {
		super(false, err);
	}
	
}



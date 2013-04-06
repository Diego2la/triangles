package com.example.tyurin.figurestest;

import java.util.Iterator;
import java.util.Vector;

abstract public class Tester {

	public Tester() {
		
		results = new Vector<TestInfo>();
	}
	
	public boolean test(boolean showLog) {

		runTests();
		if (showLog) this.showLog();
		return noErrors();		
	}
	
	public void addTest(TestInfo info) {
		
		results.add(info);
	}
	
	public void runTests() {}
	

	private void showLog() {
		
		System.out.println("\"" + toString() + "\"started");

		int oks   = 0;
		int fails = 0;
		
		int idx = 0;
		Iterator<TestInfo> it = results.iterator();
		while (it.hasNext())
		{
			TestInfo info = it.next();
			System.out.print("Test " + idx++ + " - \"" + info.name + "\" : ");
			if (info.res.ok())
			{
				System.out.print("ok");
				++oks;
			}
			else
			{
				System.out.print("fail error=\"" + info.res.error() + "\"");
				++fails;
			}
			System.out.println();
		}
		
		System.out.println("\"" + toString() + "\"finished");
		System.out.println("Result : successes=" + oks + "; fails=" + fails);
	}
	
	private boolean noErrors() {
		Iterator<TestInfo> it = results.iterator();
		while (it.hasNext())
		{
			if (!it.next().res.ok()) return false;
		}
		return true;		
	}
	
	private Vector<TestInfo> results;
	
}

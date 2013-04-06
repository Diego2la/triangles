package com.example.tyurin.figurestest;

public class TriangleTester extends PolygonTester {

	@Override
	public void runTests() {		

		addTest( new TestInfo(testConstructorWithNullVector()  , "testConstructorWithNullVector")  );
		addTest( new TestInfo(testConstructorWithNullPoint()   , "testConstructorWithNullPoint")   );
		addTest( new TestInfo(testConstructorWithTwoPoints()   , "testConstructorWithTwoPoints")   );
		addTest( new TestInfo(testConstructorWithThreePoints() , "testConstructorWithThreePoints") );
		addTest( new TestInfo(testPerimetrWithFourPoints()     , "testPerimetrWithFourPoints")     );
		addTest( new TestInfo(testPerimetrWithTwoLines()       , "testPerimetrWithTwoLines")       );
		addTest( new TestInfo(testCovexWithTreePoints()        , "testCovexWithTreePoints")        );
		addTest( new TestInfo(testCovexWithSomeBadFigure()     , "testCovexWithSomeBadFigure")     );
		
	}
		
	@Override
	public String toString() {
		return "TriangleTester";
	}
	
//	protected TestResult testConstructorWithNullVector() {
//		
//		Vector<Point> v = null;
//		try {
//			Polygon p = new Polygon(v);
//			p.perimeter();
//		} catch (NullCollectionException e) {
//			return new TestOk();
//		} catch (PolygonException e) {
//			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
//		}	
//		return new TestFail();
//	}
	
}

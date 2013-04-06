package com.example.tyurin.figures;

public class PolygonException extends Exception
{
	PolygonException(String error)
	{
		super();
		this.error = error;
	}
	
	@Override
	public String toString() {
		return "PolygonException( " + error + " )"; 
 	}
	
	protected String error;
	private static final long serialVersionUID = 1L;
}


class NullCollectionException extends PolygonException {
	NullCollectionException() {
		super("Collection is null");
	}
	private static final long serialVersionUID = 1L;
}

class NullPointException extends PolygonException {
	NullPointException() {
		super("At least one Point is null");
	}
	private static final long serialVersionUID = 1L;
}

class VerticesCountException extends PolygonException {
	VerticesCountException() {
		super("Wrong vertices count");
	}
	private static final long serialVersionUID = 1L;
}

class OpenFileException extends PolygonException {
	OpenFileException(String fileName) {
		super("Cannot open file \"" + fileName + "\"");
	}
	private static final long serialVersionUID = 1L;
}

class ReadFileException extends PolygonException {
	ReadFileException(String fileName) {
		super("Error while reding file \"" + fileName + "\"");
	}
	private static final long serialVersionUID = 1L;
}

class TriangleNotFoundException extends PolygonException {
	TriangleNotFoundException(String fileName, int number) {
		super("Triangle with number " + number + " not found in file \"" + fileName + "\"");
	}
	private static final long serialVersionUID = 1L;
}

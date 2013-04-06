package com.example.tyurin.figuresexception;

public class NullCollectionException extends PolygonException {
	public NullCollectionException() {
		super("Collection is null");
	}
	private static final long serialVersionUID = 1L;
}

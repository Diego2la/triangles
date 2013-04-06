package com.example.tyurin.figuresexception;

public class NullPointException extends PolygonException {
	public NullPointException() {
		super("At least one Point is null");
	}
	private static final long serialVersionUID = 1L;
}
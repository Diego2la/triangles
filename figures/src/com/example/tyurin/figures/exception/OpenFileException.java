package com.example.tyurin.figures.exception;

public class OpenFileException extends PolygonException {
	public OpenFileException(String fileName) {
		super("cannot open file \"" + fileName + "\"");
	}
	private static final long serialVersionUID = 1L;
}
package com.example.tyurin.figuresexception;

public class ReadFileException extends PolygonException {
	public ReadFileException(String fileName) {
		super("Error while reding file \"" + fileName + "\"");
	}
	private static final long serialVersionUID = 1L;
}
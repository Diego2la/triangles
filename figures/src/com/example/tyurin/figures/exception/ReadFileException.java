package com.example.tyurin.figures.exception;

public class ReadFileException extends PolygonException {
	public ReadFileException(String fileName) {
		super("Error while reading file \"" + fileName + "\"");
	}
	private static final long serialVersionUID = 1L;
}
package com.example.tyurin.figures.exception;

public class FileFormatException extends PolygonException {
	public FileFormatException(String fileName) {
		super("wrong file format" + fileName);
	}
	private static final long serialVersionUID = 1L;
}

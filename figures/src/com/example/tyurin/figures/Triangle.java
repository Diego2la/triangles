package com.example.tyurin.figures;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Vector;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.example.tyurin.figures.exception.NullArgumentException;
import com.example.tyurin.figures.exception.OpenFileException;
import com.example.tyurin.figures.exception.PolygonException;
import com.example.tyurin.figures.exception.ReadFileException;
import com.example.tyurin.figures.exception.TriangleNotFoundException;
import com.example.tyurin.figures.exception.VerticesCountException;


class TriangleLoader {
	
	public TriangleLoader(String fileName) throws NullArgumentException {
		if (fileName == null) 
			throw new NullArgumentException("fileName");
		this.fileName = fileName;
	}
	
	public Triangle loadTriangle(int number) 
			throws NullArgumentException, OpenFileException, ReadFileException, TriangleNotFoundException {
		
		Triangle t = null;
		
		try {
			
			File file = new File(fileName);
			if (file.exists() && file.canRead())
			{
				InputStream input = new FileInputStream(file);
				
				int size = 8 + 6 * 8; // int + 6 * double				
				byte[] arr = new byte[size];

				int readed = 0;
			    while ((readed = input.read(arr, 0, size)) == size)
				{
					ByteBuffer buf = ByteBuffer.allocate(size);  
				    buf.order(ByteOrder.LITTLE_ENDIAN);  
				    buf.put(arr);
				    
				    if (buf.getInt(0) == number)			
					{
						Vector<Point> vertices = new Vector<Point>();
						vertices.setSize(Triangle.TRIANGLE_VERTICES_COUNT);
						for (int i = 0 ; i < Triangle.TRIANGLE_VERTICES_COUNT; ++i)
						{
							Point p = new Point();
						    p.x = buf.getDouble( (i + 1) * 8);
						    p.y = buf.getDouble( (i + 2) * 8);
							vertices.set(i, p);
						}
						
						try
						{
							t = new Triangle(vertices);
						} catch (PolygonException e)
						{
							// error in logic
							e.printStackTrace();
						}
						break;
					}
				}	
			    
			    input.close();
			    if (t == null && readed != -1) throw new ReadFileException(fileName);
			}
			else throw new OpenFileException(fileName);
			
		} catch (IOException|BufferOverflowException|IndexOutOfBoundsException e) {
			throw new ReadFileException(fileName);
		}
		
		if (t == null)
			throw new TriangleNotFoundException(fileName, number);
			
		return t;
	}
	
	private String fileName;
	
}

public class Triangle extends Polygon {

	/**
	 * @param vertices Collection of Points
	 * @throws VerticesCountException If collection size not TRIANGLE_VERTICES_COUNT
	 * @throws NullPointException If input collection contains null elements
	 * @throws NullCollectionException If collection is null 
	 */
	public Triangle(AbstractCollection<Point> vertices) throws VerticesCountException, NullArgumentException {
		super(vertices);
		int size = vertices.size();
		if (size != TRIANGLE_VERTICES_COUNT)
			throw new VerticesCountException(size);
	}
	
	public Triangle(Triangle triangle) {
		super(triangle);
	}

	public Triangle(String fileName, int number) 
			throws NullArgumentException, OpenFileException, ReadFileException, TriangleNotFoundException {
		super(new TriangleLoader(fileName).loadTriangle(number));
	}
	
	/**
	 * Method use Heron's formula
	 * @return square of triangle
	 */
	public double square() {
				
		double p = perimeter() / 2;
		Iterator<Line> it = lineIterator();
		double res = p;
		for (int i = 0; i < TRIANGLE_VERTICES_COUNT; ++i)
			res *= (p - it.next().distance());
		return Math.sqrt(res);
	}
	
	final public static int TRIANGLE_VERTICES_COUNT = 3;
	
}


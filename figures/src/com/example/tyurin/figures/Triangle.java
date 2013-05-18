package com.example.tyurin.figures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Vector;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.example.tyurin.figures.exception.BadPointException;
import com.example.tyurin.figures.exception.EqualPointsException;
import com.example.tyurin.figures.exception.FileFormatException;
import com.example.tyurin.figures.exception.NullArgumentException;
import com.example.tyurin.figures.exception.OpenFileException;
import com.example.tyurin.figures.exception.TriangleNotFoundException;
import com.example.tyurin.figures.exception.TypeOverflowException;
import com.example.tyurin.figures.exception.VerticesCountException;


class TriangleLoader {
	
	public TriangleLoader(String fileName) throws NullArgumentException {
		if (fileName == null) 
			throw new NullArgumentException("fileName");
		this.fileName = fileName;
	}
		
	public Triangle loadTriangle(int number) 
			throws OpenFileException, FileFormatException, 
			TriangleNotFoundException, EqualPointsException {
	
		Triangle t = null;
		
		try {
			
			File file = new File(fileName);
			if (!file.exists())
				throw new OpenFileException(fileName);
			
			FileInputStream input = null;
			try {
				input = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new OpenFileException(fileName);
			}
			
			final int struct_size = 56;			
			byte[] arr = new byte[struct_size];

			if (input.available() % struct_size != 0) {
				input.close();
				throw new FileFormatException(fileName);
			}
			
			int readed = 0;
		    while ((readed = input.read(arr, 0, struct_size)) == struct_size)
			{
				ByteBuffer buf = ByteBuffer.allocate(struct_size);  
			    buf.order(ByteOrder.LITTLE_ENDIAN);  
			    buf.put(arr);
			    
			    if (buf.getInt(0) == number)			
				{
					Vector<Point> vertices = new Vector<Point>();
					vertices.setSize(Triangle.TRIANGLE_VERTICES_COUNT);
					for (int i = 0 ; i < Triangle.TRIANGLE_VERTICES_COUNT; ++i)
					{
						Point p = new Point();
					    p.x = buf.getDouble( (i*2 + 1) * 8);
					    p.y = buf.getDouble( (i*2 + 2) * 8);
						vertices.set(i, p);
					}
					
					try
					{
						t = new Triangle(vertices);
					} catch (BadPointException|NullArgumentException|VerticesCountException e) {
						// error in logic
						e.printStackTrace();
					}
					break;
				}
			}	
		    
		    input.close();
			if (t == null && readed != -1) throw new FileFormatException(fileName);
					
		} catch (IOException|IndexOutOfBoundsException e) {
			throw new FileFormatException(fileName);
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
	 * @throws VerticesCountException if collection size not TRIANGLE_VERTICES_COUNT
	 */
	public Triangle(AbstractCollection<Point> vertices) 
			throws NullArgumentException, BadPointException, 
			EqualPointsException, VerticesCountException {
		super(vertices);
		int size = vertices.size();
		if (size != TRIANGLE_VERTICES_COUNT)
			throw new VerticesCountException(size);
	}
	
	public Triangle(String fileName, int number) 
			throws NullArgumentException, OpenFileException, 
			FileFormatException, TriangleNotFoundException, EqualPointsException {
		super(new TriangleLoader(fileName).loadTriangle(number));
	}
	
	/**
	 * Method use Heron's formula
	 * @return square of triangle
	 */
	public double square() throws TypeOverflowException {
				
		double p = perimeter(); 
		p /= 2;
		double s = p;

		try {
			Iterator<Line> it = lineIterator();
			while(it.hasNext()) {
				s *= p - it.next().distance();
			}
		} catch (OverflowException e) {
			throw new TypeOverflowException();
		}
		if (Double.isInfinite(s) || Double.isNaN(s))
			throw new TypeOverflowException();
		
		return Math.sqrt(s);
		
	}
	
	protected Triangle(Triangle triangle) {
		super(triangle);
	}
	
	final public static int TRIANGLE_VERTICES_COUNT = 3;
	
}


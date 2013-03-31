package com.example.tyurin.figures;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractCollection;
import java.util.Vector;
import java.nio.ByteBuffer;


class TriangleLoader {
	
	public TriangleLoader(String fileName) {
		this.fileName = fileName;
	}
	
	Triangle loadTriangle(int number) throws IOException {
		
		Triangle t = null;
		
		File file = new File(fileName);
		if (file.exists())
		{
			if (file.canRead())
			{
				InputStream input = new FileInputStream(file);
				
				int size = 8 + 6 * 8;
				byte[] arr = new byte[size];
				while (input.read(arr, 0, size) == size)
				{
					int num = ByteBuffer.wrap(arr, 0, 4).getInt();
					int offset = 8;
					if (num == number)			
					{
						Vector<Point> vertices = new Vector<Point>(3);
						Point p[] = new Point[3];
						for (int i = 0 ; i < 3; ++i)
						{
							p[0].x = ByteBuffer.wrap(arr, offset + i * 8,       8).getDouble();
							p[0].y = ByteBuffer.wrap(arr, offset + (i + 1) * 8, 8).getDouble();
							vertices.set(i, p[i]);
						}
						t = new Triangle(vertices, num);
						break;						
					}
				}			
				input.close();
			}			
		}
		return t;
		
	}
	
	protected String fileName;
	
}

public class Triangle extends Polygon {

	Triangle(AbstractCollection<Point> collection, int number) {
		super(collection, number);
	}
	
	Triangle(Triangle triangle) {
		super(triangle);
	}

	Triangle(String fileName, int number) throws IOException {
		super(new TriangleLoader(fileName).loadTriangle(number));
	}
	
}

class Sqr
{
	public int calc(int x)
	{
		return x*x;
	}
	
}



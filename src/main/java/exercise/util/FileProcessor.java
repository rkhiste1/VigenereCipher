package exercise.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {


	private String inputfile;
	private String outputfile;
	private BufferedReader reader;
	private BufferedWriter writer;

	
	public FileProcessor(String inputfile) {
		
		try {
			reader = new BufferedReader(new FileReader(inputfile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public FileProcessor(String inputfile, String outputfile) {
		
		try {
			reader = new BufferedReader(new FileReader(inputfile));
			writer = new BufferedWriter(new FileWriter(outputfile));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * 
	 * @return a line from file
	 * @throws IOException on input error while reading file
	 * @see IOException
	 */
	public  String readLineFromFile() throws IOException
	{
		return reader.readLine();
	}
	
	/**
	 * 
	 * @return a character from file
	 * @throws IOException on input error while reading file
	 * @see IOException
	 */
	public  int readCharFromFile() throws IOException
	{
		return reader.read();
	}
	
	public void writeCharToFile(int ch) throws IOException
	{
		writer.write(ch);
		writer.flush();	
	}
	
	public void writeLineToFile(String line) throws IOException
	{

		writer.write(line);
		writer.newLine();
		writer.flush();
	}
	
	/**
	 * This method closes the File reader
	 */
	public void closeStream() {
		
		try {
			if(reader!=null)
				reader.close();	
			if(writer!=null)
				writer.close();	
		} catch (IOException e) {
			System.err.println("Error occurred while closing file!");
		} finally {
			
		}
	}

	@Override
	public String toString() {
		return "FileProcessor [inputfile=" + inputfile + ", reader=" + reader
				+ "]";
	}
}

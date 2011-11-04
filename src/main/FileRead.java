package main;

import java.io.*;
class FileRead 
{
	public String GetContents(String fileName) throws IOException {
		String out = null;

		FileInputStream fstream = new FileInputStream(fileName);
		  
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		  
		while ((strLine = br.readLine()) != null)   {
			out += strLine;
		}
		 
		in.close();
		return out;
	 }
}
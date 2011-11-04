package main;

import java.io.IOException;

public class Mumbler {

	public String generate(String fileName) throws IOException {
		GrammarEngine engine = new GrammarEngine();
		
		FileRead reader = new FileRead();
		
		engine.parse(reader.GetContents(fileName));
		String sentence = engine.generate();
		//System.out.println(sentence);
		return sentence;
	}
}

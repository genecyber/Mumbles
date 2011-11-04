package tests;
import static org.junit.Assert.*;

import java.io.IOException;

import main.Mumbler;

import org.junit.Test;

	public class MainTests {
		
		private Mumbler mumbler = new Mumbler();

		@Test
		public void mumblerGeneratesData() throws IOException {
			String sentence = mumbler.generate("Star-Trek-episode.g");
			System.out.print(sentence);
		    assertNotNull(sentence);
		}
		
		@Test
		public void mumblerContainsMultipleWords() throws IOException {
			assertTrue(mumbler.generate("Friday-13th-movie.g").split(" ").length > 1);
		}
	}

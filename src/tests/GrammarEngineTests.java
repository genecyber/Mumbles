package tests;

import static org.junit.Assert.*;
import main.GrammarEngine;
import main.model.GrammarTemplate;
import main.model.MessageType;

import org.junit.Test;

public class GrammarEngineTests {
	
	private GrammarEngine grammarEngine = new GrammarEngine();
	private GrammarTemplate grammarTemplate;
	
	@Test public void nothingToParseContainsMessage() {
		grammarTemplate = grammarEngine.parse("");
		assertTrue(grammarTemplate.messages.size() > 0);
	}
	
	@Test public void errorContainsErrorType() {
		grammarTemplate = grammarEngine.parse("");
		assertTrue(grammarTemplate.messages.get(0).messageType == MessageType.Error);
	}
	
	@Test public void errorContainsErrorMessage() {
		grammarTemplate = grammarEngine.parse("");
		assertTrue(grammarTemplate.messages.get(0).details == "Empty Node Found");
	}
	
	@Test public void parseTextInBetweenCurlyBraces() {
		grammarTemplate = grammarEngine.parse("{<random>Test}");
		assertEquals("Test",grammarTemplate.nodes.get("random").get(0).Fragment,"Test");
	}
	
	@Test public void parseIgnoresTextOutsideCurlyBraces() {
		grammarTemplate = grammarEngine.parse("--- () < teeeeeest >A{<foo>Test}");
		assertEquals(grammarTemplate.nodes.get("foo").get(0).Fragment,"Test");
	}
	
	@Test public void parseEmptyResultsContainsError() {
		grammarTemplate = grammarEngine.parse("{}");
		assertTrue(grammarTemplate.messages.get(0).messageType == MessageType.Error);
	}
	
	@Test public void parseCanHandleTwoNodes() {
		grammarTemplate = grammarEngine.parse("{<start>A} {<other>B;}");
		assertEquals(grammarTemplate.nodes.size(),2);
	}
	
	@Test public void parseCanLocateStartNode() {
		grammarTemplate = grammarEngine.parse("{<start>A} {<end>B}");
		assertTrue(grammarTemplate.nodes.containsKey("start"));
	}
	
	@Test public void parseCanHandleMultipleStartNodes() {
		grammarTemplate = grammarEngine.parse("{<start>A;B} {<end>B}");
		assertTrue(grammarTemplate.nodes.containsKey("start"));
	}
	
	@Test public void parseCanReadNodeWithSentence() {
		grammarTemplate = grammarEngine.parse("{<start>Some Sentence;}");
		assertEquals("Some Sentence",grammarTemplate.nodes.get("start").get(0).Fragment);
	}
	
	@Test public void parseSentenceDoesNotContainMessageKey() {
		grammarTemplate = grammarEngine.parse("{<aKey>A Sentence;}");
		assertEquals("A Sentence",grammarTemplate.nodes.get("aKey").get(0).Fragment);
	}
	
	@Test public void parseSentencesContainsMultipleSentences() {
		grammarTemplate = grammarEngine.parse("{<aKey>1 Sentence;2 Sentences}");
		assertEquals(2,grammarTemplate.nodes.get("aKey").size());
	}
	
	@Test public void parseMultipleNodesWithMultipleSentencesInSingleNode() {
		grammarTemplate = grammarEngine.parse("{<aKey>1 Sentence;2 Sentences} {<anotherKey>Another Sentence;SomethingElse;Another}");
		assertEquals(2,grammarTemplate.nodes.get("aKey").size());
		assertEquals(3,grammarTemplate.nodes.get("anotherKey").size());
		assertEquals("SomethingElse",grammarTemplate.nodes.get("anotherKey").get(1).Fragment);
	}
	
	@Test public void parseSentenceContainsOtherNodesKey() {
		grammarTemplate = grammarEngine.parse("{<aKey>1 <anotherKey> Sentence} {<anotherKey>Some Sentence}");
		assertEquals("1 <anotherKey> Sentence", grammarTemplate.nodes.get("aKey").get(0).Fragment);
	}
	
	@Test public void parseSentenceContainsMultipleNodeKeys() {
		grammarTemplate = grammarEngine.parse("{<aKey>1 <anotherKey> <SomeOtherKey> Sentence} {<anotherKey>Some Sentence}");
		assertEquals("1 <anotherKey> <SomeOtherKey> Sentence", grammarTemplate.nodes.get("aKey").get(0).Fragment);
	}
	
	
	
	//Generate Sentence
	
	@Test public void generateSentenceFindsStartTemplate() {
		grammarTemplate = grammarEngine.parse("{<start>1 <anotherKey> <SomeOtherKey> Sentence} {<anotherKey>Some Sentence} {<SomeOtherKey> Other}");
		assertNotNull(grammarEngine.generate());
	}
	
	@Test public void generateSentenceSingleKeyDecorationIsResolved() {
		grammarTemplate = grammarEngine.parse("{<start>1 <anotherKey> Sentence} {<anotherKey>Some Sentence}");
		assertEquals("1 Some Sentence Sentence",grammarEngine.generate());
	}
	
	@Test public void generateSentenceMultipleKeyDecorationsAreResolved() {
		grammarTemplate = grammarEngine.parse("{<start>1 <anotherKey> Left over stuff here.} {<anotherKey>Some Sentence<ending> <punctuation>} {<ending>the end} {<punctuation>.}");
		assertEquals("1 Some Sentencethe end . Left over stuff here.",grammarEngine.generate());
	}
	
	
	
	

}

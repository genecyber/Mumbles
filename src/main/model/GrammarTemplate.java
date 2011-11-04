package main.model;

import java.util.HashMap;

public class GrammarTemplate {
	
	public GrammarTemplate() {
		this.messages = new HashMap<Integer,Message>();
		this.nodes = new HashMap<String,HashMap<Integer,SentenceFragment>>();
	}
	
	public HashMap<Integer,Message> messages;
	public HashMap<String,HashMap<Integer,SentenceFragment>> nodes;
}

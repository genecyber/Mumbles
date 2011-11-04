package main;

import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.model.GrammarTemplate;
import main.model.Message;
import main.model.MessageType;
import main.model.SentenceFragment;

public class GrammarEngine {
	
	private GrammarTemplate grammarTemplate;
	
	public GrammarEngine() {
		grammarTemplate = new GrammarTemplate();
	}

	public GrammarTemplate parse(String toParse) {
		String toClean = toParse.split("\\{")[0];
		String[] pieces = toParse.replace(toClean, "").split("\\}");
		
		for(int n=0; n<pieces.length; n++)
		{
			if (toParse == "" || toParse == "{}") {
				return ErroredTemplate("Empty Node Found");
			}
			String nodeKey = captureNodeKey(pieces[n]);
			HashMap<Integer,SentenceFragment> nodeValues = captureSentences(pieces[n],nodeKey);			
			grammarTemplate.nodes.put(nodeKey, nodeValues);
			if (grammarTemplate.nodes.get(nodeKey).containsValue("")) {
				return ErroredTemplate("Empty Node");
			}
		}
		return grammarTemplate;
	}

	private String captureNodeKey(String nodeValue) {
		String key = "random";
		if (nodeValue.contains("<") && nodeValue.contains(">")) {
			key = nodeValue.split("<")[1].split(">")[0];
		}
		return key;
	}

	private GrammarTemplate ErroredTemplate(String details) {
		grammarTemplate.messages.put(0,createMessage(MessageType.Error,details));
		return grammarTemplate;
	}
	
	private HashMap<Integer,SentenceFragment> captureSentences(String toParse, String key) {
		HashMap<Integer,SentenceFragment> nodeValues = new HashMap<Integer,SentenceFragment>();
		SentenceFragment fragment = new SentenceFragment();
		toParse = toParse.replace("<"+key+">", "");
		if (toParse == "{}") {
			fragment.Fragment = "";
			nodeValues.put(0, fragment);
			ErroredTemplate("Empty Node");
			return nodeValues;
		}
		
		String allSentences = toParse.split("\\{")[1].split("\\}")[0];
		String sentences[] = allSentences.split(";");
		for(int n=0; n<sentences.length; n++) {
			fragment = new SentenceFragment();
			if (sentences[n] != "") {
				fragment.Fragment = sentences[n].replace("\t", "").replace("  ", "");
				nodeValues.put(n, fragment);
			}
		}
		
		return nodeValues;
	}

	public Message createMessage(MessageType messageType, String details) {
		Message message = new Message();
		message.messageType = messageType;
		message.details = details;
		return message;
	}

	public String generate() {
		
		int size = grammarTemplate.nodes.get("start").size();
		int item = new Random().nextInt(size); 
		String sentence = grammarTemplate.nodes.get("start").get(item).Fragment;
		sentence = matchReplaceKeys(sentence);
		return sentence;
	}

	private String matchReplaceKeys(String sentence) {
		Pattern fragmentPattern = Pattern.compile("(\\<([^\\>]+)\\>)");
		Matcher matcher = fragmentPattern.matcher(sentence);
		while (matcher.find()) {
			String key = matcher.group().replace("<", "").replace(">", "");
			HashMap<Integer,SentenceFragment> fragment = grammarTemplate.nodes.get(key);
			int size = fragment.size();
			int item = new Random().nextInt(size); 
			sentence = sentence.replace(matcher.group(), fragment.get(item).Fragment);
		}
		//recurse
		if (sentence.contains("<") && sentence.contains(">"))
			sentence = matchReplaceKeys(sentence);
		return sentence;
	}

}

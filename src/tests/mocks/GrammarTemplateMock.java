package tests.mocks;

import main.model.GrammarTemplate;

public class GrammarTemplateMock extends GrammarTemplate {
	
	public GrammarTemplate parse(String string) {
		GrammarTemplate grammarTemplate = new GrammarTemplate();
		return grammarTemplate;
	}
}